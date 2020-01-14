package com.uty.pengganti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myStrings = arrayOf("Pilih","Admin","User")
        spinner.adapter =  ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@MainActivity, myStrings[p2], LENGTH_LONG).show()
            }
        }


        btn_login.setOnClickListener {
            if(txtUsername.text.toString() == "Adi" && txtPassword.text.toString() == "Adi"){
                startActivity(Intent(this, home::class.java))
            }else{
                Toast.makeText(this,"berhasil masuk", Toast.LENGTH_SHORT).show()
            }
            if(txtUsername.text.toString() == "adi" && txtPassword.text.toString() == "adi"){
                startActivity(Intent(this, home_user::class.java))
            }else {
                Toast.makeText(this, "berhasil masuk", Toast.LENGTH_SHORT).show()

            }
        }
    }
}