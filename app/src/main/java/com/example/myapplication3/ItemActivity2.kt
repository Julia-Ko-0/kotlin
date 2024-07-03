package com.example.myapplication3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
// import com.razorpay.checkout

class ItemActivity2<PaymentResultWithDataListener, ExternalWalletListener> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_blank_manga)

//        Checkout.preload(applicationContext);
//        val co = Checkout()
//        co.setKeyID("rzp_live_XXXXXXXXXXXXXX")

        val title = findViewById<TextView>(R.id.name_manga_blF)
        val text = findViewById<TextView>(R.id.name_opManga)

        title.text = intent.getStringExtra("itemTitle")
        text.text = intent.getStringExtra("itemText")

    }
}