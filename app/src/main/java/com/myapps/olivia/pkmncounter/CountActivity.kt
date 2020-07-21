package com.myapps.olivia.pkmncounter

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.myapps.olivia.pkmncounter.entities.Method
import com.myapps.olivia.pkmncounter.entities.Version

class CountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        /**
         * Initialisation of screen fields
         */
        val versionName = findViewById<TextView>(R.id.versionName)
        val methodName = findViewById<TextView>(R.id.methodName)
        val pokemonName = findViewById<TextView>(R.id.pokemonName)
        val displayedOccurences = findViewById<TextView>(R.id.occurences)
        val displayedRate = findViewById<TextView>(R.id.methodRate)

        /**
         * Initialisation of information put on screen
         */
        val chosenVersion = intent.getSerializableExtra("chosenVersion") as Version
        val chosenMethod = intent.getSerializableExtra("chosenMethod") as Method
        val chosenPokemonName = intent.getStringExtra("userPokemonNameInput")
        val chromaCharm = intent.getBooleanExtra("chromaCharm", false)
        val occurrences = intent.getIntExtra("occurrences",0)

        versionName.text = chosenVersion.name;
        methodName.text = chosenMethod.name;
        pokemonName.text = chosenPokemonName
        displayedOccurences.text = occurrences.toString()
        this.determineRate(chosenVersion.number, chromaCharm, chosenMethod.id, occurrences)
    }

    override fun onStart() {
        super.onStart()
        val backButton = findViewById<Button>(R.id.backButton)
        val addButton = findViewById<Button>(R.id.addButton)
        val displayedOccurences = findViewById<TextView>(R.id.occurences);
        var occurrences = intent.getIntExtra("occurrences",0)

        addButton.setOnClickListener{ view ->
            occurrences +=1
            displayedOccurences.text = occurrences.toString()
        }

        backButton.setOnClickListener{ view ->
            finish()
        }
    }

    /**
     * Determination of the rates according to the method and chroma charm
     *
     * Reset 1
     * -----
     * until version 5   : 1/8192 --- with CC 1/2731
     * from version 6 on : 1/4096 --- with CC 1/1365
     *
     * Navidex 2
     * -------
     * 1/4096
     *
     * Hord 3
     * -----
     * 5/4096 --- with CC 15/4096
     *
     * SOS 4
     * ----
     * 0-10 : 1/4096 --- with CC : 3/4096
     * 11-20: 5/4096 --- with CC : 7/4096
     * 21-30: 9/4096 --- with CC : 11/4096
     * 31+:   13/4096 --- with CC: 15/4096
     *
     * Safari Friends 5
     * --------------
     * 5/4096 --- with CC 7/4096
     *
     * Pokéradar 6
     * ---------
     * 0-10 : 1/8192
     * 11-20: 1/5664
     * 21-30: 1/3947
     * 33-40: 1/2129
     * 41+ :  1/200 --- with CC : 3/classical rate
     *
     *
     * Fishing 7
     * --------
     * from 0 :  1/4096 --- with cc 1/1366
     * from 2 :  1/1366 --- with cc 1/820
     * from 3 :  1/820  --- with cc 1/586
     * from 4 :  1/586  --- with cc 1/456
     * from 5 :  1/456  --- with cc 1/373
     * from 6 :  1/373  --- with cc 1/316
     * from 7 :  1/316  --- with cc 1/274
     * from 8 :  1/274  --- with cc 1/241
     * from 9 :  1/241  --- with cc 1/216
     * from 10 : 1/216  --- with cc 1/196
     * from 11 : 1/196  --- with cc 1/179
     * from 12 : 1/179  --- with cc 1/164
     * from 13 : 1/164  --- with cc 1/152
     * from 14 : 1/152  --- with cc 1/142
     * from 15 : 1/142  --- with cc 1/133
     * from 16 : 1/133  --- with cc 1/125
     * from 17 : 1/125  --- with cc 1/118
     * from 18 : 1/118  --- with cc 1/111
     * from 19 : 1/111  --- with cc 1/106
     * from 20 : 1/106  --- with cc 1/100
     * from 21 : 1/100  --- with cc 1/96
     *
     * Masuda 8
     * ------
     * versions 1-3: 1/1638 --- no CC
     * version 4:    1/1365 --- no CC
     * version 5:    1/1365 --- with CC 1/1024
     * versions 6+ : 1/683  --- with CC 1/512
     */
    fun determineRate(versionNumber: Int, chromaCharm: Boolean, methodId: Number, occurrences: Int){
        val displayedRate = findViewById<TextView>(R.id.methodRate)
        var rate: String =""
        when (methodId){
            //RESET
            1 ->
                if(versionNumber <= 5) {
                    if(chromaCharm) { rate="1/2731" }
                    else{rate="1/8192" }
                  }
                else{
                    if(chromaCharm) { rate="1/1365" }
                    else{ rate="1/4096" }
                }
            //NAVIDEX
            2 -> rate = "1/4096"
            //HORD
            3 ->
                if (chromaCharm){ rate = "15/4096" }
                else{ rate = "5/4096" }
            //SOS
            4 -> if (occurrences <= 10){
                    if(chromaCharm) { rate="3/4096" }
                    else{ rate="1/4096" }
                }
                else if (occurrences in 11..20){
                    if(chromaCharm) { rate="7/4096" }
                    else{ rate="5/4096" }
                }
                else if (occurrences in 21..30){
                    if(chromaCharm) { rate="11/4096" }
                    else{ rate="9/4096" }
                }
                else {
                    if(chromaCharm) { rate="15/4096" }
                    else{ rate="13/4096" }
                }
            //SAFARI FRIENDS
            5 ->
                if (chromaCharm){ rate = "7/4096" }
                else{ rate = "5/4096" }
            //POKERADAR
            6 -> if (occurrences <= 10){
                    if(chromaCharm) { rate="3/8192" }
                    else{ rate="1/8192" }
                }
                else if (occurrences in 11..20){
                    if(chromaCharm) { rate="3/5664" }
                    else{ rate="1/5664" }
                }
                else if (occurrences in 21..30){
                    if(chromaCharm) { rate="3/3947" }
                    else{ rate="1/3947" }
                }
                else if (occurrences in 31..40){
                    if(chromaCharm) { rate="3/2129" }
                    else{ rate="1/2129" }
                }
                else {
                    if(chromaCharm) { rate="3/200" }
                    else{ rate="1/200" }
                }
            //FISHING
            7 ->  if (occurrences <= 2){
                        if(chromaCharm) { rate="1/1366" }
                        else{ rate="1/4096" }
                }
                else if (occurrences in 2..3){
                    if(chromaCharm) { rate="1/820" }
                    else{ rate="1/1366" }
                }
                else if (occurrences in 3..4){
                    if(chromaCharm) { rate="1/596" }
                    else{ rate="1/820" }
                }
                else if (occurrences in 4..5){
                    if(chromaCharm) { rate="3/456" }
                    else{ rate="1/586" }
                }
                else if (occurrences in 5..6){
                    if(chromaCharm) { rate="3/373" }
                    else{ rate="1/456" }
                }
                else if (occurrences in 6..7){
                    if(chromaCharm) { rate="3/316" }
                    else{ rate="1/373" }
                }
                else if (occurrences in 7..8){
                    if(chromaCharm) { rate="3/274" }
                    else{ rate="1/316" }
                }
                else if (occurrences in 8..9){
                    if(chromaCharm) { rate="3/241" }
                    else{ rate="1/274" }
                }
                else if (occurrences in 9..10){
                    if(chromaCharm) { rate="3/216" }
                    else{ rate="1/241" }
                }
                else if (occurrences in 10..11){
                    if(chromaCharm) { rate="3/196" }
                    else{ rate="1/216" }
                }
                else if (occurrences in 11..12){
                    if(chromaCharm) { rate="3/179" }
                    else{ rate="1/196" }
                }
                else if (occurrences in 12..13){
                    if(chromaCharm) { rate="3/164" }
                    else{ rate="1/179" }
                }
                else if (occurrences in 13..14){
                    if(chromaCharm) { rate="3/152" }
                    else{ rate="1/164" }
                }
                else if (occurrences in 14..15){
                    if(chromaCharm) { rate="3/142" }
                    else{ rate="1/152" }
                }
                else if (occurrences in 15..16){
                    if(chromaCharm) { rate="3/133" }
                    else{ rate="1/142" }
                }
                else if (occurrences in 16..17){
                    if(chromaCharm) { rate="3/125" }
                    else{ rate="1/133" }
                }
                else if (occurrences in 17..18){
                    if(chromaCharm) { rate="3/118" }
                    else{ rate="1/125" }
                }
                else if (occurrences in 18..19){
                    if(chromaCharm) { rate="3/111" }
                    else{ rate="1/118" }
                }
                else if (occurrences in 19..20){
                    if(chromaCharm) { rate="3/106" }
                    else{ rate="1/111" }
                }
                else if (occurrences in 20..21){
                    if(chromaCharm) { rate="3/100" }
                    else{ rate="1/106" }
                }
                else{
                    if(chromaCharm) { rate="3/96" }
                    else{ rate="1/100" }
                }
            //MASUDA
            8 -> if (versionNumber <= 3){ rate="1/1638" }
                else if (versionNumber == 4){ rate="1/1365" }
                else if (versionNumber == 5){
                    if(chromaCharm) { rate="1/1024" }
                    else{ rate="1/1365" }
                }
                else {
                    if(chromaCharm) { rate="1/512" }
                    else{ rate="1/683" }
                }
        }
        displayedRate.text=rate
        onContentChanged()
    }


}

