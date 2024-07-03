package com.example.myapplication3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class BlankFragment4 : Fragment() {

    companion object {
        private const val ARG_USERNAME = "username"

        fun newInstance(username: String?): BlankFragment4 {
            val fragment = BlankFragment4()
            val args = Bundle()
            args.putString(ARG_USERNAME, username)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank4, container, false)
        val textView = view.findViewById<TextView>(R.id.namePolz)
        val username = arguments?.getString(ARG_USERNAME)
        textView.text = username
        return view
    }
//    companion object {
//
//        @JvmStatic
//        fun newInstance(username: String?) =
//            BlankFragment4()
//    }
}

//
//class BlankFragment4 : Fragment() {
//    private lateinit var nameTextView: TextView
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
////        val binding = FragmentBlank4Binding.inflate(inflater)
//        val view = inflater.inflate(R.layout.fragment_blank4, container, false)
//        nameTextView = view.findViewById(R.id.namePolz)
//        return view
//    }
//    fun updateUserName(username: String) {
//        nameTextView.text = username
//    }
//    companion object {
//
//        @JvmStatic
//        fun newInstance(username: String?) =
//            BlankFragment4()
//    }
//}