package com.example.myapplication3

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.myapplication3.databinding.FragmentBlank2Binding


class BlankFragment2 : Fragment() {
    private var items = mutableListOf<modelM>()
    private lateinit var itemsAdapter: ItemsAdapter
    private lateinit var username: String

    fun getMahbf() {
        val apiService = ApiService(requireContext())
        apiService.getMangaData({ list ->
            // Обновляем список данных и уведомляем адаптер
            items.clear()
            items.addAll(list)
        }, { error ->
            // Обработка ошибки
            Log.d("MyLog", error)
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBlank2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn_popul_ : Button = view.findViewById(R.id.btn_popul)
        val btn_rand_ : Button = view.findViewById(R.id.btn_rand)

        val btn_collection_ : Button = view.findViewById(R.id.btn_collection)
        val btn_filtr_ : Button = view.findViewById(R.id.btn_filtr)

        username = arguments?.getString(ARG_USERNAME) ?: "Unknown User"

        btn_popul_.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.place_hs, BlankFragment.newInstance(username))
                ?.addToBackStack(null)
                ?.commit()

        }
        btn_rand_.setOnClickListener {
            getMahbf()
            if (items.isNotEmpty()) {
                // Выбираем случайный элемент из списка
                val randomIndex = (0 until items.size).random()
                val selectedItem = items[randomIndex]
                val name_manga = when {
                    selectedItem.name_en != "null" -> selectedItem.name_en
                    selectedItem.name_en_jp != "null" -> selectedItem.name_en_jp
                    selectedItem.name_ja_jp != "null" -> selectedItem.name_ja_jp
                    selectedItem.name_en_us != "null" -> selectedItem.name_en_us
                    else -> "No Title"
                }
                val ganres = selectedItem.ganre
                var ir_ganrs = ""
                for (i in 0 until ganres.count()) {
                    val name_ganr = selectedItem.ganre[i].name
                    if (i == 0){
                        ir_ganrs = name_ganr
                    }
                    else{
                        ir_ganrs= ir_ganrs + ", " + name_ganr
                    }

                }
                // Создаем экземпляр BlankFragment_manga и передаем выбранный элемент
                val fragment = BlankFragment_manga.newInstance(
                    name_manga,  // Замените на нужные поля вашего класса модели
                    selectedItem.synopsis,
                    selectedItem.image,
                    ir_ganrs,
                    selectedItem.id,
                    username
            )

                // Переходим к BlankFragment_manga
                val activity = context as FragmentActivity
                val fragmentManager: FragmentManager = activity.supportFragmentManager
                fragmentManager.beginTransaction()
                    .replace(R.id.place_hs, fragment) // ID контейнера фрагмента
                    .addToBackStack(null)
                    .commit()
            }
        }
//            val fragment = BlankFragment_manga.newInstance(,,)
//
//            val activity = context as FragmentActivity
//            val fragmentManager: FragmentManager = activity.supportFragmentManager
//            fragmentManager.beginTransaction()
//                .replace(R.id.place_hs, fragment) // ID контейнера фрагмента
//                .addToBackStack(null)
//                .commit()
//        }


    }


    companion object {

        private const val ARG_USERNAME = "username"
        @JvmStatic
        fun newInstance(username:String?) =
            BlankFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)
                }
            }
    }
}

