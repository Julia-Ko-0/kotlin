package com.example.myapplication3


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AuthActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth2)

        val userLogin = findViewById<EditText>(R.id.user_login_auth)
        val userPass = findViewById<EditText>(R.id.user_pass_auth)
        val button = findViewById<Button>(R.id.button_auth)
        val linkToReg = findViewById<TextView>(R.id.link_to_reg)

        linkToReg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if(login == "" || pass == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
            else {
                val db = DBHelper(this, null)
                val isAuth = db.getUser(login, pass)

                if(isAuth) {
//                    db.updateUserStatus(login, "1")
                    Toast.makeText(this, "Пользователь $login авторизован", Toast.LENGTH_LONG).show()
                    userLogin.text.clear()
                    userPass.text.clear()

                    val intent = Intent(this, ItemsActivity2::class.java).apply {
                        putExtra("USERNAME", login)
                    }
                    startActivity(intent)
                } else
                    Toast.makeText(this, "Пользователь $login не авторизован", Toast.LENGTH_LONG).show()

            }

        }
    }
}



//class AuthActivity2 : AppCompatActivity(){
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(layout.activity_auth2)
//
//        val userLogin = findViewById<EditText>(R.id.user_login_auth)
//        val userPass = findViewById<EditText>(R.id.user_pass_auth)
//        val button = findViewById<Button>(R.id.button_auth)
//        val linkToReg = findViewById<TextView>(R.id.link_to_reg)
//
//        linkToReg.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//
//        button.setOnClickListener {
//            val login = userLogin.text.toString().trim()
//            val pass = userPass.text.toString().trim()
//
//            if(login == "" || pass == "")
//                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
//            else {
//                val db = DBHelper(this, null)
//                val isAuth = db.getUser(login, pass)
//
//                if(isAuth) {
//                    Toast.makeText(this, "Пользователь $login авторизован", Toast.LENGTH_LONG).show()
//                    userLogin.text.clear()
//                    userPass.text.clear()
////
//                    val intent = Intent(this, ItemsActivity2::class.java).apply {
//                        putExtra("USERNAME", login)
//                    }
//                    startActivity(intent)
//                } else
//                    Toast.makeText(this, "Пользователь $login не авторизован", Toast.LENGTH_LONG).show()
//
//            }
//
//        }
//    }
//}