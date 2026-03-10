package com.gabriel.pokems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kotlin.random.Random

class PokemonPopupFragment : DialogFragment() {

    private var onResult: ((Boolean) -> Unit)? = null

    companion object {
        private const val ARG_NAME = "pokemon_name"

        fun newInstance(name: String, onResult: (Boolean) -> Unit): PokemonPopupFragment {
            val fragment = PokemonPopupFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_NAME, name)
            }
            fragment.onResult = onResult
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_pokemon_popup, container, false)
        val name = arguments?.getString(ARG_NAME) ?: "Unknown"

        val nameTextView = view.findViewById<TextView>(R.id.pokemon_name)
        val catchButton = view.findViewById<Button>(R.id.btn_catch)
        val coinImage = view.findViewById<ImageView>(R.id.coin_image)
        val pokemonImage = view.findViewById<ImageView>(R.id.pokemon_image)

        // Set the correct image based on the pokemon name
        val resId = when (name.lowercase()) {
            "pikachu" -> R.drawable.pikachu
            "charmander" -> R.drawable.charmander
            else -> android.R.drawable.btn_star_big_on
        }
        pokemonImage.setImageResource(resId)

        nameTextView.text = "A wild $name appeared!"

        catchButton.setOnClickListener {
            catchButton.isEnabled = false
            coinImage.visibility = View.VISIBLE
            
            // Coin flip animation
            coinImage.animate()
                .rotationYBy(1800f) // 5 full rotations
                .setDuration(1000)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .withEndAction {
                    val isCaught = Random.nextBoolean()
                    
                    if (isCaught) {
                        // Success image (e.g., a gold coin or pokeball)
                        coinImage.setImageResource(android.R.drawable.btn_star_big_on)
                        nameTextView.text = "Gotcha! $name was caught!"
                        pokemonImage.animate().alpha(0f).scaleX(0f).scaleY(0f).setDuration(500).start()
                    } else {
                        // Failure image (e.g., an 'X' or broken coin)
                        coinImage.setImageResource(android.R.drawable.ic_delete)
                        nameTextView.text = "Oh no! $name broke free!"
                    }
                    
                    view.postDelayed({
                        onResult?.invoke(isCaught)
                        dismiss()
                    }, 1500)
                }
                .start()
        }

        return view
    }
}