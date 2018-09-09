package com.myapps.olivia.pkmncounter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide


class CountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val chosenVersion = intent.getSerializableExtra("chosenVersion")
        val chosenMethod = intent.getSerializableExtra("chosenMethod")
        val chromaCharm = intent.getBooleanExtra("chromaCharm", false)
        val pokemonNumber = intent.getIntExtra("pokemonNumber", 1)
        val chosenPokemonName = intent.getStringExtra("chosenPokemonName")

        println("INFORMATION PEOPLE! $chosenMethod$chosenVersion$chosenPokemonName$pokemonNumber")

        setContentView(R.layout.activity_count)
        val versionName = findViewById<TextView>(R.id.versionName)
        versionName.text = chosenVersion.toString()

        val methodName = findViewById<TextView>(R.id.methodName)
        methodName.text = chosenMethod.toString()

        val pokemonName = findViewById<TextView>(R.id.pokemonName)
        pokemonName.text = chosenPokemonName

        val pkmnImage = findViewById<ImageView>(R.id.pkmnImage)
        try {
            Glide.with(applicationContext)
                    .load("\"http://pokeapi.co/media/sprites/pokemon/$pokemonNumber.png")
                    .into(pkmnImage)
        } catch (e: Exception) {
            Toast.makeText(application.baseContext, "Oh non ... une erreur est survenue!", Toast.LENGTH_SHORT).show()
        }
        onContentChanged()
    }

}

