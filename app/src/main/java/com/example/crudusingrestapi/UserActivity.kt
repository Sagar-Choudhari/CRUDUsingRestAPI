package com.example.crudusingrestapi

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudusingrestapi.model.User
import com.example.crudusingrestapi.remote.APIUtils
import com.example.crudusingrestapi.remote.UserService
import retrofit2.Callback
import retrofit2.Call as Calll
import retrofit2.Response


class UserActivity : AppCompatActivity() {

    var userService: UserService? = null
    var edtUId: EditText? = null
    var edtUsername: EditText? = null
    var btnSave: Button? = null
    var btnDel: Button? = null
    var txtUId: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        title = "Users"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        txtUId = findViewById<View>(R.id.txtUId) as TextView
        edtUId = findViewById<View>(R.id.edtUId) as EditText
        edtUsername = findViewById<View>(R.id.edtUsername) as EditText
        btnSave = findViewById<View>(R.id.btnSave) as Button
        btnDel = findViewById<View>(R.id.btnDel) as Button

        userService = APIUtils.userService

        val extras = intent.extras
        val userId = extras!!.getString("user_id")
        val userName = extras!!.getString("user_name")

        edtUId!!.setText(userId)
        edtUsername!!.setText(userName)

        if (userId != null && userId.trim { it <= ' ' }.length > 0) {
            edtUId!!.isFocusable = false
        } else {
            txtUId!!.visibility = View.INVISIBLE
            edtUId!!.visibility = View.INVISIBLE
            btnDel!!.visibility = View.INVISIBLE
        }

        btnSave!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val u = User()
                u.name = edtUsername!!.text.toString()
                if (userId != null && userId.trim { it <= ' ' }.length > 0) {
                    //update user
                    updateUser(userId.toInt(), u)
                } else {
                    //add user
                    addUser(u)
                }
            }
        })

        btnDel!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                deleteUser(userId!!.toInt())
                val intent = Intent(this@UserActivity, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }


    fun addUser(u: User?) {
        val call: Calll<User?>? = userService!!.addUser(u)
        call?.enqueue(object : Callback<User?> {
            override fun onResponse(call: Calll<User?>?, response: Response<User?>) {
                if (response.isSuccessful()) {
                    Toast.makeText(
                        this@UserActivity,
                        "User created successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Calll<User?>?, t: Throwable) {
                Log.e("ERROR: ", t.message!!)
            }
        })
    }

    fun updateUser(id: Int, u: User?) {
        val call: Calll<User?>? = userService!!.updateUser(id, u)
        call?.enqueue(object : Callback<User?> {
            override fun onResponse(call: Calll<User?>?, response: Response<User?>) {
                if (response.isSuccessful()) {
                    Toast.makeText(
                        this@UserActivity,
                        "User updated successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Calll<User?>?, t: Throwable) {
                Log.e("ERROR: ", t.message!!)
            }
        })
    }

    fun deleteUser(id: Int) {
        val call: Calll<User?>? = userService!!.deleteUser(id)
        call?.enqueue(object : Callback<User?> {
            override fun onResponse(call: Calll<User?>?, response: Response<User?>) {
                if (response.isSuccessful()) {
                    Toast.makeText(
                        this@UserActivity,
                        "User deleted successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Calll<User?>?, t: Throwable) {
                Log.e("ERROR: ", t.message.toString())
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}