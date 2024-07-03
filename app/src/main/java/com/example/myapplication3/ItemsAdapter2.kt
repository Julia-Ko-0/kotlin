
//package com.example.myapplication3
//
//import android.content.Context
package com.example.myapplication3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemsAdapter2(var items: ArrayList<userMangas>, var context: Context, private val username: String) : RecyclerView.Adapter<ItemsAdapter2.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.item_list_image)
        val title: TextView = view.findViewById(R.id.item_list_title)
        //        val desc: TextView = view.findViewById(R.id.item_list_ganre)
        val ganre: TextView = view.findViewById(R.id.item_list_ganre)
        val full_item : LinearLayout = view.findViewById(R.id.full_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_list, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        var opis = item.synopsis
        var id_mang = item.id
        val imege_mang_otr = item.image
        holder.title.text = when {
            item.name_en != "null" -> item.name_en
            item.name_en_jp != "null" -> item.name_en_jp
            item.name_ja_jp != "null" -> item.name_ja_jp
            item.name_en_us != "null" -> item.name_en_us
            else -> "No Title"
        }


        holder.ganre.text = item.ganre_

        // Используем Glide для загрузки изображения
        Glide.with(context)
            .load(item.image)
            .into(holder.image)
//        when{
//
//            opis != "null" && opis.length > 10 -> {
//                val op_ = opis.subSequence(1,100)
//                opis = "$op_..."
//            }
//            opis != "null" && opis.length <= 10 -> opis
//            else ->  opis =  "Нет описания"
////            else -> item.synopsis
//        }

        holder.full_item.setOnClickListener{


            val fragment = BlankFragment_manga.newInstance(holder.title.text.toString(),opis,imege_mang_otr.toString(),holder.ganre.text.toString(), id_mang,username)

            val activity = context as FragmentActivity
            val fragmentManager: FragmentManager = activity.supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.place_hs, fragment) // ID контейнера фрагмента
                .addToBackStack(null)
                .commit()


        }

    }
}


//import android.content.Intent
//import android.os.Build
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
////import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.annotation.RequiresApi
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//
//class ItemsAdapter(var items: List<modelM>, var context: Context) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {
//
//    class MyViewHolder (view: View): RecyclerView.ViewHolder(view) {
//        val image = view.findViewById<ImageView>(R.id.item_list_image)
//        val title = view.findViewById<TextView>(R.id.item_list_title)
//        val desc = view.findViewById<TextView>(R.id.item_list_desc)
////        val price = view.findViewById<TextView>(R.id.item_list_price)
////        val btn = view.findViewById<Button>(R.id.item_list_button)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_list, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return items.count()
//    }
//
////    @RequiresApi(Build.VERSION_CODES.Q)
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//    val item = items[position]
//
//    holder.title.text = when {
//        item.name_en != "null" -> item.name_en
//        item.name_en_jp != "null" -> item.name_en_jp
//        item.name_ja_jp != "null" -> item.name_ja_jp
//        item.name_en_us != "null" -> item.name_en_us
//        else -> "No Title"
//    }
//
//    holder.desc.text = item.synopsis
//
//    // Используем Glide для загрузки изображения
//    Glide.with(context)
//        .load(item.image)
//        .into(holder.image)
//
//    Log.d("ITEMSADAPTER", "Title: ${holder.title.text}")
//    Log.d("ITEMSADAPTER", "Description: ${holder.desc.text}")
////        if (position <= 20) {
////            if (items[position].name_en != "null") {
////                holder.title.text = items[position].name_en
////            } else if (items[position].name_en_jp != "null") {
////                holder.title.text = items[position].name_en_jp
////            } else if (items[position].name_ja_jp != "null") {
////                holder.title.text = items[position].name_ja_jp
////            } else if (items[position].name_en_us != "null") {
////                holder.title.text = items[position].name_en_us
////            } else {
////                holder.title.text = "tttt"
////            }
////
////            holder.desc.text = items[position].synopsis
////
////            Log.d("ITEMSADAPTER", "Title: ${holder.title.text}")
////            Log.d("ITEMSADAPTER", "Description: ${holder.desc.text}")
//
////            Glide.with(context)
////                .load(items[position].image)
////                .into(holder.image)
//
////        holder.price.text = items[position].price.toString() + "$"
//
////        val imageId = context.resources.getIdentifier(items[position].image, "drawable", context.opPackageName)
//
////        holder.image.setImageResource(imageId)
////
////        holder.btn.setOnClickListener{
////            val intent = Intent(context, ItemActivity2::class.java)
////
////            intent.putExtra("itemTitle", items[position].title)
////            intent.putExtra("itemText", items[position].text)
////
////
////            context.startActivity(intent)
////        }
////        }
//    }
//}