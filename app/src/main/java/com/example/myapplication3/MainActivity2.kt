package com.example.myapplication3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import android.widget.Toast
import com.example.myapplication3.databinding.ActivityItems2Binding
import com.example.myapplication3.databinding.ActivityMain2Binding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity2:AppCompatActivity() {
    lateinit var binding: ActivityItems2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItems2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bnavL.setOnNavigationItemSelectedListener {
            Toast.makeText(this, ",,,,,,", Toast.LENGTH_LONG).show()
//            when(it.itemId){
//                R.id.menu_1 ->{
//
//
//                }
//                R.id.menu_2 ->{
//
//                }
//                R.id.menu_3->{
//
//                }
//                R.id.menu_4->{
////                    val intent = Intent(this, ItemsActivity2::class.java)
////                    startActivity(intent)
//                    Toast.makeText(this, ",,,,,,", Toast.LENGTH_LONG).show()
//                }
//            }
            true
        }

    }
}