package com.myapps.olivia.pkmncounter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_count.*
import kotlinx.android.synthetic.main.activity_count.view.*


class CountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val chosenVersion = intent.getSerializableExtra("chosenVersion")
        val chosenMethod = intent.getSerializableExtra("chosenMethod")
        val chromaCharm = intent.getBooleanExtra("chromaCharm", false)
        val pokemonNumber = intent.getIntExtra("pokemonNumber", 1)
        val chosenPokemonName = intent.getStringExtra("chosenPokemonName")
        val addButton = findViewById<Button>(R.id.addButton)

        setContentView(R.layout.activity_count)

        val versionName = findViewById(R.id.versionName) as TextView
        versionName.text = chosenVersion.toString()

        val methodName = findViewById<TextView>(R.id.methodName)
        methodName.text = chosenMethod.toString()

        val pokemonName = findViewById<TextView>(R.id.pokemonName)
        pokemonName.text = chosenPokemonName

  /*      addButton.setOnClickListener {
            occurences += 1;
        }

        val displayedOccurences = findViewById<TextView>(R.id.occurrences);
        displayedOccurences.text = occurences.toString()*/

        onContentChanged()
    }

}

