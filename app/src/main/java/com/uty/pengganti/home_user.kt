package com.uty.pengganti

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home_user.*
import kotlinx.android.synthetic.main.activity_main.*

class home_user : AppCompatActivity() {


    internal var dbHelper = DatabaseHelper(this)

    fun showToast(text: String){
        Toast.makeText(this@home_user, text, Toast.LENGTH_LONG).show()
    }

    fun showDialog(title : String,Message : String){
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    fun clearEditTexts(){
        nameTxt1.setText("")
        passTxt1.setText("")
        typeTxt1.setText("")
        idTxt1.setText("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_user)

        handleInserts()
        handleUpdates()
        handleDeletes()
        handleViewing()
    }

    fun handleInserts() {
        insertBtn1.setOnClickListener {
            try {
                dbHelper.insertData(nameTxt1.text.toString(),passTxt1.text.toString(),
                    typeTxt1.text.toString())
                clearEditTexts()
            }catch (e: Exception){
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }


    fun handleUpdates() {
        updateBtn1.setOnClickListener {
            try {
                val isUpdate = dbHelper.updateData(idTxt1.text.toString(),
                    nameTxt1.text.toString(),
                    passTxt1.text.toString(), typeTxt1.text.toString())
                if (isUpdate == true)
                    showToast("Data Updated Successfully")
                else
                    showToast("Data Not Updated")
            }catch (e: Exception){
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }


    fun handleDeletes(){
        deleteBtn1.setOnClickListener {
            try {
                dbHelper.deleteData(idTxt1.text.toString())
                clearEditTexts()
            }catch (e: Exception){
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    fun handleViewing() {
        viewBtn1.setOnClickListener(
            View.OnClickListener {
                val res = dbHelper.allData
                if (res.count == 0) {
                    showDialog("Error", "No Data Found")
                    return@OnClickListener
                }

                val buffer = StringBuffer()
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n")
                    buffer.append("Username :" + res.getString(1) + "\n")
                    buffer.append("Password :" + res.getString(2) + "\n")
                    buffer.append("TYPE :" + res.getString(3) + "\n\n")
                }
                showDialog("Data Listing", buffer.toString())
            }
        )
    }
}