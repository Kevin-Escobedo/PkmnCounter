package com.myapps.olivia.pkmncounter

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.myapps.olivia.pkmncounter.entities.Method
import com.myapps.olivia.pkmncounter.entities.Version
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var pokemonList: Array<String> = emptyArray()
        val countActivity = Intent(this@MainActivity, CountActivity::class.java)
        val occurrences = intent.getSerializableExtra("occurrences")


        val spinnerVersion = findViewById<Spinner>(R.id.version_spinner)
        val checkBoxChroma = findViewById<CheckBox>(R.id.chroma_charm)
        val spinnerMethod = findViewById<Spinner>(R.id.method_spinner)
        val pokemonName = findViewById<EditText>(R.id.pokemon_name)
        val secondActivityButton = findViewById<Button>(R.id.addButton)

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
                var chromaCharm = false;
               val chosenVersion = version_spinner.selectedItem as Version
                val methodAdapter = ArrayAdapter<Method>(applicationContext,
                        android.R.layout.simple_spinner_item, methodList(chosenVersion))
                methodAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
                spinnerMethod.adapter = methodAdapter

                method_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val chosenMethod = method_spinner.selectedItem as Method
                        countActivity.putExtra("chosenMethod",chosenMethod)
                    }
                }


                if (chosenVersion.number >= 5) {
                    checkBoxChroma.isEnabled = true
                    chromaCharm = true
                } else {
                    checkBoxChroma.isEnabled = false
                    checkBoxChroma.isChecked = false
                    chromaCharm = false
                }

                when(chosenVersion.number){
                    1 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,250)
                    2 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,385)
                    3 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,492)
                    4 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,648)
                    5 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,648)
                    6 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,720)
                    7 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,720)
                    8 ->  pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,806)
                    else -> pokemonList = resources.getStringArray(R.array.pokedex).copyOfRange(0,806) //by default
                }
                countActivity.putExtra("chosenVersion",chosenVersion)
                countActivity.putExtra("chromaCharm",chromaCharm)
            }
        }

        pokemonName.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val userPokemonNameInput = pokemonName.text.toString()

                if (pokemonList.contains(userPokemonNameInput)) {
                    val pkmnIndex = pokemonList.indexOf(userPokemonNameInput) + 1
                    val pokemonNumber = pkmnIndex
                    secondActivityButton.isEnabled = true
                    countActivity.putExtra("pokemonNumber",pokemonNumber)
                }
                else {
                secondActivityButton.isEnabled = false
                }
                countActivity.putExtra("userPokemonNameInput",userPokemonNameInput)

            }
        }

        countActivity.putExtra("occurrences",occurrences)

        secondActivityButton.setOnClickListener { startActivity(countActivity)}
    }

    fun versionList(): ArrayList<Version> {
        val versions = arrayListOf<Version>()
        val version1 = Version(1, "Gold/Silver/Crystal")
        val version2 = Version(2, "Ruby/Sapphire/Emerald")
        val version3 = Version(3, "Diamond/Pearl/Platinum/HG/SS")
        val version4 = Version(4, "Black/White")
        val version5 = Version(5, "Black/White 2")
        val version6 = Version(6, "X/Y")
        val version7 = Version(7, "Omega Rubis/Alpha Sapphire")
        val version8 = Version(8, "(Ultra)Sun/Moon")
        versions.addAll(listOf(
                version1, version2,
                version3, version4, version5, version6,version7, version8))
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

        methods.addAll(listOf(method1,method8))

        /*
        Version 1: or, argent, cristal
        2: rubis, saphire, émeraude
        3: diamant, perle, platine
        4: noir/blanc
        5: noir 2/blanc2
        6: X/Y
        7: rubis alpha
        8: soleil lune
         */

        when (version.number) {
            3 -> methods.add(method6)
            6 -> methods.addAll(listOf(method5, method6, method7))
            7 -> methods.addAll(listOf(method2,method3, method7))
            8 -> methods.add(method4)
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


