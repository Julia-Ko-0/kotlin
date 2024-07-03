package com.example.myapplication3


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


class BlankFragment_manga : Fragment() {
    private lateinit var nameTextView: TextView
    private lateinit var nameTextView_opis: TextView
    private lateinit var desk_manga_text: TextView
    private lateinit var conditionalButton: Button
    private lateinit var conditionalButton_Not: Button
    private lateinit var username: String
    private var items_manga = mutableListOf<genre>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_blank_manga, container, false)
        val image: ImageView = view.findViewById(R.id.image_namga_title)
        conditionalButton = view.findViewById(R.id.button_reed)
        conditionalButton_Not = view.findViewById(R.id.button_Notreed)
        nameTextView = view.findViewById(R.id.name_manga_blF)
        nameTextView_opis = view.findViewById(R.id.name_opManga)
        desk_manga_text = view.findViewById(R.id.desk_manga)
//        image_manga = view.findViewById(R.id.image_namga_title)
        // Получаем аргументы
        val titleText = arguments?.getString(ARG_TITLE)
        nameTextView.text = titleText
        val title_desk_manga = arguments?.getString(DESK_MANGA)
        var titleText_opis = arguments?.getString(MANGA_OPIS)
        val titleId = arguments?.getString(ID_MANGA)
        username = arguments?.getString(ARG_USERNAME) ?: "Unknown User"

        desk_manga_text.text = title_desk_manga

        if (titleText_opis.toString() != "null") {
            if (titleText_opis.toString().length > 200) {
                nameTextView_opis.text  = "${titleText_opis.toString().subSequence(0, 200)}..."
                conditionalButton.visibility = View.VISIBLE
                conditionalButton_Not.visibility = View.GONE
                changeTextViewHeight(nameTextView_opis)
                conditionalButton.setOnClickListener {
                    nameTextView_opis.text = titleText_opis
                    changeTextViewHeight(nameTextView_opis)
                    conditionalButton.visibility = View.GONE
                    conditionalButton_Not.visibility = View.VISIBLE


                }
                conditionalButton_Not.setOnClickListener {
                    conditionalButton_Not.visibility = View.GONE
                    conditionalButton.visibility = View.VISIBLE
                    nameTextView_opis.text = "${titleText_opis.toString().subSequence(0, 200)}..."
                }
            } else {
                nameTextView_opis.text = titleText_opis
                conditionalButton.visibility = View.GONE
                conditionalButton_Not.visibility = View.GONE
            }
        } else {
            nameTextView_opis.text  = "Нет описания"
            conditionalButton.visibility = View.GONE
            conditionalButton_Not.visibility = View.GONE
        }

        val title_Img = arguments?.getString(IMG_MANGA)
        context?.let {
            Glide.with(it)
                .load(title_Img)
                .into(image)
        }
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        val db = DBHelper(requireContext(), null)
        val stat = db.getUserMangaListSt(username.toString(), titleId.toString())
        val adapter = spinner.adapter
        for (i in 0 until adapter.count) {
            if (adapter.getItem(i).toString() == stat) {
                spinner.setSelection(i)
                break
            }
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selected = spinner.selectedItem
                showToast(selected.toString())
                showToast(username.toString())
//                val db = DBHelper(requireContext(), null)

                val user = db.getUserMangaList(username.toString())

                val el = genre(
                    "1",
                    title_desk_manga.toString()
                )
                val desk = items_manga.add(el)
                Log.d("MyLog40", titleText_opis.toString())
                Log.d("MyLog40", selected.toString())
                Log.d("MyLog40", titleText.toString())
                Log.d("MyLog40", titleText_opis.toString())
                Log.d("MyLog40", title_desk_manga.toString())
                val mang_List = userMangas(
                    username.toString(),
                    titleId.toString(),
                    selected.toString(),
                    titleText.toString(),
                    titleText_opis.toString(),
                    title_desk_manga.toString(),
                    "",
                    title_Img.toString(),"","",""

                    )
                db.addUserManga(mang_List)
                val otv = db.getUserMangaList(username)
                Log.d("MyLog3", otv.toString())

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Не требуется действие при отсутствии выбора
            }
        }

        return view
    }
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun changeTextViewHeight(textView: TextView) {
        val layoutParams = textView.layoutParams

            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        textView.layoutParams = layoutParams
    }
    companion object {
        private const val ARG_TITLE = "title"
        private const val MANGA_OPIS = "title_op"
        private const val IMG_MANGA = "title_img"
        private const val DESK_MANGA = "title_desk"
        private const val ID_MANGA = "title_id"
        private const val ARG_USERNAME = "username"


        @JvmStatic
        fun newInstance(title: String?,title_op: String?,title_img: String?,title_desk:String?,title_id:String?,username: String)=
            BlankFragment_manga().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE,title)
                    putString(MANGA_OPIS,title_op)
                    putString(IMG_MANGA,title_img)
                    putString(DESK_MANGA,title_desk)
                    putString(ID_MANGA,title_id)
                    putString(ARG_USERNAME, username)

                }

            }

    }
}