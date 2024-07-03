package com.example.myapplication3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BlankFragment3 : Fragment() {
    private lateinit var username: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank3, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = arguments?.getString(ARG_USERNAME) ?: "Unknown User"
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.place_hs2, BlankFragment1_1.newInstance(username))
                .commit()
        }
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu2_1 -> {
                    // Handle home navigation
                    childFragmentManager.beginTransaction().replace(R.id.place_hs2, BlankFragment1_1.newInstance(username)).commit()

                    true
                }
                R.id.menu2_2 -> {
                    childFragmentManager.beginTransaction().replace(R.id.place_hs2, BlankFragment2_1.newInstance(username)).commit()

                    // Handle dashboard navigation
                    true
                }
                R.id.menu2_3 -> {
                    childFragmentManager.beginTransaction().replace(R.id.place_hs2, BlankFragment3_1.newInstance(username)).commit()

                    true
                }
                R.id.menu2_4 -> {
                    childFragmentManager.beginTransaction().replace(R.id.place_hs2, BlankFragment4_1.newInstance(username)).commit()

                    // Handle notifications navigation
                    true
                }
                R.id.menu2_5 -> {
                    childFragmentManager.beginTransaction().replace(R.id.place_hs2, BlankFragment5_1.newInstance(username)).commit()

                    // Handle notifications navigation
                    true
                }
                else -> false
            }
        }
    }




    companion object {
        private const val ARG_USERNAME = "username"
        @JvmStatic
        fun newInstance(username: String) =
            BlankFragment3().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)
                }
            }

//        @JvmStatic
//
//        fun newInstance() =
//            BlankFragment3()
    }
}