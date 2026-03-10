package com.gabriel.pokems.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.gabriel.pokems.R
import kotlin.random.Random

/**
 * A simple dialog fragment used to display a pokemon when the player enters a
 * trigger area.  The host activity provides a callback that will be invoked
 * when the user chooses to attempt a catch; the fragment executes a coin flip
 * and reports the result.
 */
class PokemonPopupFragment(private val onCatchResult: (Boolean) -> Unit) : DialogFragment() {
    private lateinit var pokemonName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonName = arguments?.getString(ARG_NAME) ?: "Unknown"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_popup, container, false)
        view.findViewById<TextView>(R.id.pokemon_name).text = pokemonName
        view.findViewById<Button>(R.id.btn_catch).setOnClickListener {
            val caught = Random.nextBoolean()
            onCatchResult(caught)
            dismiss()
        }
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // make it non-cancelable by tapping outside so the player must make a choice
        val d = super.onCreateDialog(savedInstanceState)
        d.setCanceledOnTouchOutside(false)
        return d
    }

    companion object {
        private const val ARG_NAME = "pokemon_name"

        fun newInstance(name: String, onCatch: (Boolean) -> Unit): PokemonPopupFragment {
            val frag = PokemonPopupFragment(onCatch)
            frag.arguments = Bundle().apply { putString(ARG_NAME, name) }
            return frag
        }
    }
}