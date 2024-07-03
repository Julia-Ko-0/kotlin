package com.example.myapplication3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.databinding.FragmentBlank3Binding

class Fragment_Blank4  : AppCompatActivity(){
    lateinit var binding: FragmentBlank3Binding
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items2)
        username = intent.getStringExtra("username") ?: "Unknown User"

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_hs,BlankFragment.newInstance(username))
            .commit()

        binding = FragmentBlank3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.menu_1 ->{
                    supportFragmentManager.beginTransaction().replace(R.id.place_hs,BlankFragment1_1.newInstance(username)).commit()

                }
                R.id.menu_2 ->{
                    supportFragmentManager.beginTransaction().replace(R.id.place_hs,BlankFragment2_1.newInstance(username)).commit()

                }
                R.id.menu_3->{
////
                    Toast.makeText(this, "pppp", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction().replace(R.id.place_hs,BlankFragment3_1.newInstance(username)).commit()

                }
                R.id.menu_4->{
//                    val intent = Intent(this, ItemsActivity2::class.java)
//                    startActivity(intent)
                    Toast.makeText(this, ",,,,,,", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction().replace(R.id.place_hs,BlankFragment4_1.newInstance(username)).commit()
                }
            }

            true
        }


    }
}