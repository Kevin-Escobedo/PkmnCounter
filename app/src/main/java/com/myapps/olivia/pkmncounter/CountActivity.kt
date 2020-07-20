package com.myapps.olivia.pkmncounter

import android.content.Intent
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

        val mainActivity = Intent(this@CountActivity, MainActivity::class.java)

        val chosenVersion = intent.getSerializableExtra("chosenVersion")
        val chosenMethod = intent.getSerializableExtra("chosenMethod")
        val chromaCharm = intent.getBooleanExtra("chromaCharm", false)
        val pokemonNumber = intent.getIntExtra("pokemonNumber", 1)
        val chosenPokemonName = intent.getStringExtra("userPokemonNameInput")
        val addButton = findViewById<Button>(R.id.addButton)
        val backButton = findViewById<Button>(R.id.backButton)
        var occurrences = intent.getIntExtra("occurrences",0)


        setContentView(R.layout.activity_count)

        val versionName = findViewById(R.id.versionName) as TextView
        versionName.text = chosenVersion.toString()

        val methodName = findViewById<TextView>(R.id.methodName)
        methodName.text = chosenMethod.toString()

        val pokemonName = findViewById<TextView>(R.id.pokemonName)
        pokemonName.text = chosenPokemonName.toString()

        val displayedOccurences = findViewById<TextView>(R.id.occurrences);
        displayedOccurences.text = occurrences.toString()


  //      addButton.isEnabled=true;

//        addButton.setOnClickListener {
//            occurrences += 1;
//            onContentChanged()
//        }
    }

}

