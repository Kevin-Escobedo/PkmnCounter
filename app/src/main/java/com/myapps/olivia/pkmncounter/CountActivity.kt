package com.myapps.olivia.pkmncounter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class CountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        val addButton = findViewById<Button>(R.id.addButton)
        val backButton = findViewById<Button>(R.id.backButton)
        val versionName = findViewById<TextView>(R.id.versionName)
        val methodName = findViewById<TextView>(R.id.methodName)
        val pokemonName = findViewById<TextView>(R.id.pokemonName)
        val displayedOccurences = findViewById<TextView>(R.id.occurences);

        addButton.isEnabled=true
        backButton.isEnabled=true

        val chosenVersion = intent.getSerializableExtra("chosenVersion")
        val chosenMethod = intent.getSerializableExtra("chosenMethod")
        val chromaCharm = intent.getBooleanExtra("chromaCharm", false)
        val pokemonNumber = intent.getIntExtra("pokemonNumber", 1)
        val chosenPokemonName = intent.getStringExtra("userPokemonNameInput")
        var occurrences = intent.getIntExtra("occurrences",0)

        setContentView(R.layout.activity_count)


        versionName.text = chosenVersion.toString()
        methodName.text = chosenMethod.toString()
        pokemonName.text = chosenPokemonName.toString()
        displayedOccurences.text = occurrences.toString()


        addButton.setOnClickListener{ view ->
            println("YOU CLIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIICKED")
            Toast.makeText(this@CountActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            onContentChanged()
        }



        /*

        Reset
        -------
        jusqu'a 5G : 1/8192 CC:1/2731
        à partir de 6G : 1/4096 CC : 1/1365

        Navidex:
        ----------
        1/4096

        Pokéradar
        ---------
        0 : 1/8192
        apd 10:1/5664
        apd 20: 1/3947
        apd 30: 1/2129
        apd 40: 1/200

       CC/3

       Horde
       -------

       5/4096  (4096/5)
       CC: 15/4095 (4096/15)

       Safari des amis
       ---------------

       5/4096
       7/4096 CC

       Pêche
       --------

       1/4096 CC;1366
       apd2 : 1/1366 cc: 1/820
       apd3 : 1/820 cc: 1/586
       apd4: 1/586 cc 456
       apd5: 456 cc 373
       apd6: 373 cc 316
       ap7: 316 cc 274
       apd8 274 cc 241
       ap9 241 cc 216
       ap10 216 cc 196
       apd11 196 cc 179
       ad12 179 cc 164
       ap13 164 cc 152
       ap14 152 cc 142
       apd15 142 cc 133
       apd16 133 cc 125
       ad17 125 cc 118
       apd18 118 cc 111
       ad19 111 cc 106
       apd22 106 cc 100
       apd21 100 cc 96

       Masuda
       --------

      4G diamant perle platine : 1/1638 pas CC
      5G noir blanc: 1/1365 pas CC
      Noir 2/blanc 2: 1/1365 CC: 1/1024
      6G+ X/Y, ... 1/683 CC 1/512
    */
    }


}

