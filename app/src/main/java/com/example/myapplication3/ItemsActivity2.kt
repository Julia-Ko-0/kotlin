package com.example.myapplication3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.databinding.ActivityItems2Binding

class ItemsActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityItems2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items2)

        binding = ActivityItems2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USERNAME")
        val name_mang = intent.getStringExtra("NAMEMANG")
        val mang_opis = intent.getStringExtra("OPISMANG")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_hs, BlankFragment4.newInstance(username))
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_hs, BlankFragment.newInstance(username.toString()))
            .commit()

        binding.bnavL.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_1 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.place_hs, BlankFragment.newInstance(username.toString())).commit()
                }
                R.id.menu_2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.place_hs, BlankFragment2.newInstance(username)).commit()
                }
                R.id.menu_3 -> {
//                    Toast.makeText(this, "pppp", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction().replace(R.id.place_hs, BlankFragment3.newInstance(username.toString())).commit()
                }
                R.id.menu_4 -> {
//                    Toast.makeText(this, ",,,,,,", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction().replace(R.id.place_hs, BlankFragment4.newInstance(username)).commit()
                }
            }
            true
        }

    }
}


//
//class ItemsActivity2 : AppCompatActivity(){
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_items2)
////
////        val itemsList = findViewById<RecyclerView>(R.id.itemsList)
////        val items = arrayListOf<Item>()
////
////        items.add(Item(1, "lamp", "Лампа", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Ut consectetur ut velit id venenatis. Nunc dapibus tellus et euismod porta. Aenean nec dui ac dolor dictum tempor. Curabitur non erat.", 1200))
////        items.add(Item(2, "table", "Стол", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Ut consectetur ut velit id venenatis. Nunc dapibus tellus et euismod porta. Aenean nec dui ac dolor dictum tempor. Curabitur non erat.", 15000))
////        items.add(Item(3, "chair", "Кресло", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Ut consectetur ut velit id venenatis. Nunc dapibus tellus et euismod porta. Aenean nec dui ac dolor dictum tempor. Curabitur non erat.", 9100))
////
////        itemsList.layoutManager = LinearLayoutManager(this)
////        itemsList.adapter = ItemsAdapter(items, this)
////    }
//    lateinit var binding: ActivityItems2Binding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_items2)
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.place_hs,BlankFragment.newInstance())
//            .commit()
//
//        binding = ActivityItems2Binding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.bnavL.setOnNavigationItemSelectedListener {
//
//            when(it.itemId){
//                R.id.menu_1 ->{
//                    supportFragmentManager.beginTransaction().replace(R.id.place_hs,BlankFragment.newInstance()).commit()
//
//                }
//                R.id.menu_2 ->{
//                    supportFragmentManager.beginTransaction().replace(R.id.place_hs,BlankFragment2.newInstance()).commit()
//
//                }
//                R.id.menu_3->{
//////
//                    Toast.makeText(this, "pppp", Toast.LENGTH_LONG).show()
//                    supportFragmentManager.beginTransaction().replace(R.id.place_hs,BlankFragment3.newInstance()).commit()
//
//                }
//                R.id.menu_4->{
////                    val intent = Intent(this, ItemsActivity2::class.java)
////                    startActivity(intent)
//                    Toast.makeText(this, ",,,,,,", Toast.LENGTH_LONG).show()
//                 supportFragmentManager.beginTransaction().replace(R.id.place_hs,BlankFragment4.newInstance()).commit()
//                }
//            }
////            val itemsList = findViewById<RecyclerView>(R.id.itemsList)
////                    val items = arrayListOf<Item>()
////
////                    items.add(Item(1, "lamp", "Лампа", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Ut consectetur ut velit id venenatis. Nunc dapibus tellus et euismod porta. Aenean nec dui ac dolor dictum tempor. Curabitur non erat.", 1200))
////                    items.add(Item(2, "table", "Стол", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Ut consectetur ut velit id venenatis. Nunc dapibus tellus et euismod porta. Aenean nec dui ac dolor dictum tempor. Curabitur non erat.", 15000))
////                    items.add(Item(3, "chair", "Кресло", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Ut consectetur ut velit id venenatis. Nunc dapibus tellus et euismod porta. Aenean nec dui ac dolor dictum tempor. Curabitur non erat.", 9100))
////
////                    itemsList.layoutManager = LinearLayoutManager(this)
////                    itemsList.adapter = ItemsAdapter(items, this)
//            true
//        }
//
//
//    }
//
//
//}
//
