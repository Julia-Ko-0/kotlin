package com.example.myapplication3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BlankFragment : Fragment() {

    private lateinit var itemsAdapter: ItemsAdapter
    private var items = mutableListOf<modelM>()
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMahbf()
        username = arguments?.getString(ARG_USERNAME) ?: "Unknown User"

        val itemsList = view.findViewById<RecyclerView>(R.id.itemsList)
        itemsAdapter = ItemsAdapter(items, requireContext(),username)

        itemsList.layoutManager = LinearLayoutManager(requireContext())
        itemsList.adapter = itemsAdapter
    }

    private fun getMahbf() {
        val apiService = ApiService(requireContext())
        apiService.getMangaData({ list ->
            // Обновляем список данных и уведомляем адаптер
            items.clear()
            items.addAll(list)
            itemsAdapter.notifyDataSetChanged()
//            Log.d("MyLog5", items.toString())
        }, { error ->
            // Обработка ошибкиq
            Log.d("MyLog", error)
        })
    }

    companion object {
        private const val ARG_USERNAME = "username"
        @JvmStatic
        fun newInstance(username: String) =
            BlankFragment().apply {
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
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.android.volley.Request
//import com.android.volley.toolbox.StringRequest
//import com.android.volley.toolbox.Volley
//import org.json.JSONObject
//
//class BlankFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_blank, container, false)
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        getmahbf()
//
//        val itemsList = view.findViewById<RecyclerView>(R.id.itemsList)
//        val items = arrayListOf<modelM>()
//
//        itemsList.layoutManager = LinearLayoutManager(context)
//        itemsList.adapter = ItemsAdapter(items, requireContext())
//
//    }
//    private fun getmahbf(){
//        val apiService = ApiService(requireContext())
//        apiService.getMangaData({ list ->
//            // Handle success, e.g., update UI with list
//            Log.d("MyLog2", list.toString())
//        }, { error ->
//            // Handle error
//            Log.d("MyLog", error)
//        })
//
//    }
//
//
//    companion object {
//
//        @JvmStatic
//        fun newInstance() = BlankFragment()
//    }
//}
//class BlankFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_blank, container, false)
//    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        getmahbf()
//
//    }
//    private fun getmahbf(){
//        var limit = 20
//    var page = 20
////        val url = "https://kitsu.io/api/edge/manga?page[limit]="+ limit +"&page[offset]=" + page
//    val url = "https://kitsu.io/api/edge/manga"
//
////      val stringRequest = StringRquest(Request.Method.GET)
//        val queue = Volley.newRequestQueue(context)
//        val request = StringRequest(Request.Method.GET,url,{
//            result ->  parsMangJ(result)
////                result ->  Log.d("MyLog",result)
//        },{
//            error -> Log.d("MyLog","Error:${error}")
//        })
//        queue.add(request)
//    }
//
//    private fun parsMangJ(result:String){
//        val mainOb = JSONObject(result)
//        prsMang2(mainOb)
////       val list =  prsMang2(mainOb)
////        Log.d("MyLog", list[0].toString())
//       }
//
//    private  fun prsMang2(obj: JSONObject):List<modelM>{
//        val list = ArrayList<modelM>()
//
//        val mainOb2 = obj.getJSONArray("data")
//        Log.d("MyLog",mainOb2[0].toString())
//        Log.d("MyLog",mainOb2[1].toString())
//
//        for(i in 0 until mainOb2.length()) {
//            val mainOb3 = mainOb2[i] as JSONObject
//
//            val en = if (mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                    .isNull("en")
//            ) "null" else mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                .getString("en")
//            val en_jp = if (mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                    .isNull("en_jp")
//            ) "null" else mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                .getString("en_jp")
//            val ja_jp = if (mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                    .isNull("ja_jp")
//            ) "null" else mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                .getString("ja_jp")
//            val en_us = if (mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                    .isNull("en_us")
//            ) "null" else mainOb3.getJSONObject("attributes").getJSONObject("titles")
//                .getString("en_us")
//            Log.d(
//                "MyLog",
//                mainOb3.getJSONObject("attributes").getJSONObject("titles").toString()
//            )
////
////
//            val item = modelM(
//                mainOb3.getString("id"),
//                en,
//                en_jp,
//                ja_jp,
//                en_us,
//                mainOb3.getJSONObject("attributes").getString("synopsis"),
//                mainOb3.getJSONObject("attributes").getString("ageRating"),
//                mainOb3.getJSONObject("attributes").getString("ageRatingGuide")
//
//            )
//            list.add(item)
//
//        }
//        Log.d("MyLog2", list.toString())
//        return list
//
//    }
//    companion object {
//
//        @JvmStatic
//        fun newInstance() = BlankFragment()
//    }
//}