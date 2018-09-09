package com.myapps.olivia.pkmncounter

import android.content.Intent
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.myapps.olivia.pkmncounter.entities.Method
import com.myapps.olivia.pkmncounter.entities.Version
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.EditText
import android.view.MotionEvent
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var pokemonList: Array<String> = emptyArray()
        var chosenVersion = Version()
        var chosenMethod = Method()
        var chromaCharm = false
        var pokemonNumber = 0
        var chosenPokemonName = ""

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerVersion = findViewById<Spinner>(R.id.version_spinner)
        val checkBoxChroma = findViewById<CheckBox>(R.id.chroma_charm)
        val spinnerMethod = findViewById<Spinner>(R.id.method_spinner)
        val pokemonName = findViewById<EditText>(R.id.pokemon_name)
        val secondActivityButton = findViewById<Button>(R.id.startButton)

        secondActivityButton.isEnabled = false
        checkBoxChroma.isChecked = false
        checkBoxChroma.isEnabled = false

        val versionAdapter = ArrayAdapter<Version>(this,
                android.R.layout.simple_spinner_item, versionList())
        versionAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)

        spinnerVersion.adapter = versionAdapter

        version_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                pokemonName.text.clear()
               chosenVersion = version_spinner.selectedItem as Version
                val methodAdapter = ArrayAdapter<Method>(applicationContext,
                        android.R.layout.simple_spinner_item, methodList(chosenVersion))
                methodAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
                spinnerMethod.adapter = methodAdapter
                chosenMethod = method_spinner.selectedItem as Method

                if (chosenVersion.number >= 6) {
                    checkBoxChroma.isEnabled = true
                    chromaCharm = true
                } else {
                    checkBoxChroma.isEnabled = false
                    checkBoxChroma.isChecked = false
                    chromaCharm = false
                }

                when(chosenVersion.number){
                    1 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,150)
                    2 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,250)
                    3 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,385)
                    4 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,492)
                    5 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,648)
                    6 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,720)
                    7 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,806)
                    else -> pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,806) //by default
                }
                onContentChanged()
            }
        }

        pokemonName.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val userPokemonNameInput = pokemonName.text.toString()

                if (pokemonList.contains(userPokemonNameInput)) {
                    val pkmnIndex = pokemonList.indexOf(userPokemonNameInput) + 1
                    Toast.makeText(application.baseContext, "Ton pokémon est le numéro $pkmnIndex", Toast.LENGTH_SHORT).show()
                    pokemonNumber = pkmnIndex
                    chosenPokemonName = userPokemonNameInput
                    secondActivityButton.isEnabled = true
                }
                else{
                    Toast.makeText(application.baseContext, "Ton pokémon n'a pas été trouvé... Vérifie l'orthographe et la génération ;) ", Toast.LENGTH_SHORT).show()
                    pokemonName.text.clear()
                    secondActivityButton.isEnabled = false
                }
                onContentChanged()
            }
        }

        intent.putExtra("chosenVersion",chosenVersion)
        intent.putExtra("chosenMethod",chosenMethod)
        intent.putExtra("chromaCharm",chromaCharm)
        intent.putExtra("pokemonNumber",pokemonNumber)
        intent.putExtra("pokemonName",chosenPokemonName)

        println("INFORMATION FROM MAIN $chosenMethod$chosenVersion$pokemonNumber$chosenPokemonName$chromaCharm")

        secondActivityButton.setOnClickListener { startActivity(intent)}
    }

    fun versionList(): ArrayList<Version> {
        val versions = arrayListOf<Version>()
        val version1 = Version(1, "Red/Blue/Yellow")
        val version2 = Version(2, "Gold/Silver/Crystal")
        val version3 = Version(3, "Ruby/Sapphire/Emerald")
        val version4 = Version(4, "Diamond/Pearl/Platinum")
        val version5 = Version(5, "Black/White 1 & 2")
        val version6 = Version(6, "X/Y/Omega Rubis/Alpha Sapphire")
        val version7 = Version(7, "(Ultra)Sun/Moon")
        versions.addAll(listOf(
                version1, version2, version3,
                version4, version5, version6, version7))
        return versions
    }

    fun methodList(version: Version): ArrayList<Method> {
        val methods = arrayListOf<Method>()
        val method1 = Method("Random/Soft-reset")
        val method2 = Method("Navidex")
        val method3 = Method("Hord")
        val method4 = Method("SOS")
        val method5 = Method("Friends Safari")
        val method6 = Method("Pokeradar")
        val method7 = Method("Fishing")
        val method8 = Method("Masuda")

        methods.add(method1)

        when (version.number) {
            4 -> methods.addAll(listOf(method6, method8))
            5 -> methods.add(method8)
            6 -> methods.addAll(listOf(method2, method3, method5, method6, method7, method8))
            7 -> methods.addAll(listOf(method4, method8))
        }
        return methods
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (view != null && view is EditText) {
                val r = Rect()
                view.getGlobalVisibleRect(r)
                val rawX = ev.rawX.toInt()
                val rawY = ev.rawY.toInt()
                if (!r.contains(rawX, rawY)) {
                    view.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}


