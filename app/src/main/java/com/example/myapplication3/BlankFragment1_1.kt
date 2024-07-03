package com.example.myapplication3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BlankFragment1_1 : Fragment() {

    private lateinit var itemsAdapter2: ItemsAdapter2
    private var items = ArrayList<userMangas>()
//    private var items1 = mutableListOf<modelM>()
    private var items_manga = mutableListOf<modelM>()
    private lateinit var username: String

//    fun getMahbf() {
//        val apiService = ApiService(requireContext())
//        apiService.getMangaData({ list ->
//            // Обновляем список данных и уведомляем адаптер
//            items1.clear()
//            items1.addAll(list)
//        }, { error ->
//            // Обработка ошибки
//            Log.d("MyLog", error)
//        })
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank1_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = arguments?.getString(ARG_USERNAME) ?: "Unknown User"

        val itemsList = view.findViewById<RecyclerView>(R.id.itemList_Izbr)
//        Log.d("MyLog6",items.toString())
//        Log.d("MyLog6",items_manga.toString())
//        // Получаем список манг избранного пользователя из локальной БД
//        items_manga.clear()
//        items_manga.addAll(getMangLike())
//        Log.d("MyLog7",items.toString())
//        Log.d("MyLog7",items_manga.toString())

        itemsAdapter2 = ItemsAdapter2(getMangLike(), requireContext(), username)
        itemsList.layoutManager = LinearLayoutManager(requireContext())
        itemsList.adapter = itemsAdapter2

    }


    // Метод для получения списка манг, добавленных в избранное пользователем
    private fun getMangLike(): ArrayList<userMangas> {

        Log.d("MyLog8",items.toString())
        val db = DBHelper(requireContext(), null)
        val userMangasList = db.getUserMangaList(username)
        Log.d("MyLog8",userMangasList.toString())
        val mangas = mutableListOf<modelM>()
        for (i in 0 until userMangasList.count()) {
                if(userMangasList[i].status == "Избранное" ){
                    val it = userMangas(
                        username,
                        userMangasList[i].id,
                        userMangasList[i].status,
                        userMangasList[i].name_en,
                        userMangasList[i].synopsis,
                        userMangasList[i].ganre_,
                        userMangasList[i].ageRating,
                        userMangasList[i].image,
                        "",
                        "",
                        ""
                    )
                    items.addAll(listOf(it))

            }

        }

        Log.d("MyLog8",items.toString())
        db.close()
        return items
    }

    companion object {
        private const val ARG_USERNAME = "username"

        @JvmStatic
        fun newInstance(username: String) =
            BlankFragment1_1().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)
                }
            }
    }
}

//package com.example.myapplication3
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class BlankFragment1_1 : Fragment() {
//
//    private lateinit var itemsAdapter: ItemsAdapter
//    private var items = mutableListOf<modelM>()
//    private var items_manga = mutableListOf<modelM>()
//    private lateinit var username: String
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_blank1_1, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        getMahbf()
//        username = arguments?.getString(ARG_USERNAME) ?: "Unknown User"
//
//        val itemsList = view.findViewById<RecyclerView>(R.id.itemList_Izbr)
//
//
//        val otv = getMangLike()
//        Log.d("MyLog1", otv.toString())
//        for (i in 0 until otv.count()){
//
//
//            for (l in 0 until items.count()){
//
//                if (items[l].id == otv[i].id_manga){
//                    Log.d("MyLog6", items[l].toString())
//                    items_manga.addAll(listOf(items[l]))
//
//                }
//            }
//        }
//        Log.d("MyLog6", items_manga.toString())
//        itemsAdapter = ItemsAdapter(items_manga, requireContext(),username)
//
//        itemsList.layoutManager = LinearLayoutManager(requireContext())
//        itemsList.adapter = itemsAdapter
//        itemsAdapter.notifyDataSetChanged()
//    }
//
//    private fun getMahbf() {
//        val apiService = ApiService(requireContext())
//        apiService.getMangaData({ list ->
//            // Обновляем список данных и уведомляем адаптер
//            items.clear()
//            items.addAll(list)
//            itemsAdapter.notifyDataSetChanged()
////            Log.d("MyLog5", items.toString())
//        }, { error ->
//            // Обработка ошибкиq
//            Log.d("MyLog", error)
//        })
//    }
//
////
//////    private lateinit var itemsAdapter: ItemsAdapter
////    private lateinit var itemsAdapter: ItemsAdapter
////    private var items = mutableListOf<modelM>()
////    private var items_manga = mutableListOf<modelM>()
////    private lateinit var username: String
////
////    override fun onCreateView(
////        inflater: LayoutInflater, container: ViewGroup?,
////        savedInstanceState: Bundle?
////    ): View? {
////        return inflater.inflate(R.layout.fragment_blank1_1, container, false)
////    }
////    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        super.onViewCreated(view, savedInstanceState)
////
////        val otv = getMangLike()
////        Log.d("MyLog1", otv.toString())
//////        username = arguments?.getString(ARG_USERNAME) ?: "Unknown User"
//////        val itemList_Izb = view.findViewById<RecyclerView>(R.id.itemList_Izbr)
//////        Log.d("MyLog1", items.toString())
//////        for (i in 0 until otv.count()){
//////            for (l in 0 until items.count()){
//////                if (items[l].id == otv[i].id_manga){
//////                    items_manga.addAll(listOf(items[l]))
//////
//////                }
//////            }
//////        }
////        Log.d("MyLog1", items_manga.toString())
//////        itemsAdapter = ItemsAdapter(items_manga, requireContext(),username)
//////        itemList_Izb.layoutManager = LinearLayoutManager(requireContext())
//////        itemList_Izb.adapter = itemsAdapter
//////        itemsAdapter.notifyDataSetChanged()
////
////
//////        username = arguments?.getString(BlankFragment.ARG_USERNAME) ?: "Unknown User"
//////
//////        val itemsList = view.findViewById<RecyclerView>(R.id.itemsList)
//////        itemsAdapter = ItemsAdapter(items, requireContext(),username)
//////
//////        itemsList.layoutManager = LinearLayoutManager(requireContext())
//////        itemsList.adapter = itemsAdapter
////    }
////    private fun getMahbf() {
////        val apiService = ApiService(requireContext())
////        apiService.getMangaData({ list ->
////            // Обновляем список данных и уведомляем адаптер
////            items.clear()
////            items.addAll(list)
//////            itemsAdapter.notifyDataSetChanged()
//////            Log.d("MyLog5", items.toString())
////        }, { error ->
////            // Обработка ошибкиq
////            Log.d("MyLog", error)
////        })
////    }
//
//    private fun getMangLike(): List<userMangas> {
//        val db = DBHelper(requireContext(), null)
//
//        val otv = db.getUserManga(username.toString(),"Избранное")
//        return otv
//
//    }
//
//    companion object {
//        private const val ARG_USERNAME = "username"
//        @JvmStatic
//        fun newInstance(username: String) =
//            BlankFragment1_1().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_USERNAME, username)
//                }
//            }
//    }
////        @JvmStatic
////        fun newInstance() = BlankFragment1_1()
////    }
//}