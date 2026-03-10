package com.gabriel.pokems

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.gabriel.pokems.model.MovementEvent
import com.gabriel.pokems.model.CatchEvent
import com.gabriel.pokems.model.TriggerEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameActivity : AppCompatActivity() {
    private lateinit var character: ImageView
    private lateinit var upButton: Button
    private lateinit var downButton: Button
    private lateinit var leftButton: Button
    private lateinit var rightButton: Button
    
    private lateinit var pokemonMarkers: List<ImageView>

    private lateinit var api: ApiService
    private var isPopupActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        character = findViewById(R.id.character)
        upButton = findViewById(R.id.btn_up)
        downButton = findViewById(R.id.btn_down)
        leftButton = findViewById(R.id.btn_left)
        rightButton = findViewById(R.id.btn_right)

        // Find the scattered pokemon on the map
        pokemonMarkers = listOf(
            findViewById(R.id.pokemon_1),
            findViewById(R.id.pokemon_2),
            findViewById(R.id.pokemon_3),
            findViewById(R.id.pokemon_4)
        )

        api = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val moveListener = View.OnClickListener { v ->
            val dx = when (v.id) {
                R.id.btn_left -> -40
                R.id.btn_right -> 40
                else -> 0
            }
            val dy = when (v.id) {
                R.id.btn_up -> -40
                R.id.btn_down -> 40
                else -> 0
            }
            moveCharacter(dx, dy)
        }

        upButton.setOnClickListener(moveListener)
        downButton.setOnClickListener(moveListener)
        leftButton.setOnClickListener(moveListener)
        rightButton.setOnClickListener(moveListener)
    }

    private fun moveCharacter(dx: Int, dy: Int) {
        character.translationX += dx
        character.translationY += dy
        
        sendMovementEvent()
        checkTriggers()
    }

    private fun sendMovementEvent() {
        val x = character.translationX.toInt()
        val y = character.translationY.toInt()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.sendMovement(MovementEvent(x, y))
            } catch (e: Exception) {}
        }
    }

    private fun checkTriggers() {
        if (isPopupActive) return

        val charRect = Rect()
        character.getHitRect(charRect)
        
        // Adjust Rect for translation
        charRect.offset(character.translationX.toInt(), character.translationY.toInt())

        for (marker in pokemonMarkers) {
            if (marker.visibility != View.VISIBLE) continue
            
            val markerRect = Rect()
            marker.getHitRect(markerRect)
            
            if (Rect.intersects(charRect, markerRect)) {
                isPopupActive = true
                val name = when (marker.id) {
                    R.id.pokemon_1 -> "Pikachu"
                    R.id.pokemon_2 -> "Charmander"
                    R.id.pokemon_3 -> "Squirtle"
                    R.id.pokemon_4 -> "Bulbasaur"
                    else -> "Unknown"
                }
                
                sendTriggerEvent(name)
                showPokemonPopup(name, marker)
                break
            }
        }
    }

    private fun showPokemonPopup(name: String, marker: ImageView) {
        val fragment = PokemonPopupFragment.newInstance(name) { caught ->
            sendCatchEvent(name, caught)
            if (caught) {
                marker.visibility = View.GONE // Remove from map if caught
            }
            // Allow triggers again after a delay
            character.postDelayed({ isPopupActive = false }, 2000)
        }
        fragment.show(supportFragmentManager, "pokemon_popup")
    }

    private fun sendTriggerEvent(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.sendTrigger(TriggerEvent(name, character.translationX.toInt(), character.translationY.toInt()))
            } catch (e: Exception) {}
        }
    }

    private fun sendCatchEvent(pokemon: String, caught: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                api.sendCatch(CatchEvent(pokemon, caught))
            } catch (e: Exception) {}
        }
    }
}