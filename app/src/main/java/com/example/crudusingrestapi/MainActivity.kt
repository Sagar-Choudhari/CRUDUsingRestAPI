package com.example.crudusingrestapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.crudusingrestapi.model.User
import com.example.crudusingrestapi.remote.APIUtils
import com.example.crudusingrestapi.remote.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    var btnAddUser: Button? = null
    var btnGetUsersList: Button? = null
    var listView: ListView? = null

    var userService: UserService? = null
    var list: kotlin.collections.List<User> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Retrofit 2 CRUD Demo"

        btnAddUser = btnAddUser as Button
        btnGetUsersList = findViewById<View>(R.id.btnGetUsersList) as Button
        listView = findViewById<View>(R.id.listView) as ListView
        userService = APIUtils.userService

        btnGetUsersList!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //get users list
                getUsersList()
            }
        })

        btnAddUser!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, UserActivity::class.java)
                intent.putExtra("user_name", "")
                startActivity(intent)
            }
        })
    }

    fun getUsersList() {
        val call: Call<List<User?>?>? = userService?.getUsers
        call?.enqueue(object : Callback<List<User?>?> {
            override fun onResponse(call: Call<List<User?>?>?, response: Response<List<User?>?>) {
                if (response.isSuccessful()) {
                    list = response.body() as List<User>
                    listView!!.adapter = UserAdapter(R.layout.list_user, this@MainActivity, list)
                }
            }

            override fun onFailure(call: Call<List<User?>?>?, t: Throwable) {
                Log.e("ERROR: ", t.message!!)
            }
        })
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<List<User?>?>) {

}
