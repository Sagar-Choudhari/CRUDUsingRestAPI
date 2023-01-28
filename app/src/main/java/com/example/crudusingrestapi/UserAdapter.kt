package com.example.crudusingrestapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.crudusingrestapi.model.User



class UserAdapter(
    @LayoutRes resource: Int,
    private val contextt: Context,

    private val users: List<User>
) :
    ArrayAdapter<User?>(contextt, resource, users) {
    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.list_user, parent, false)
        val txtUserId = rowView.findViewById<View>(R.id.txtUserId) as TextView
        val txtUsername = rowView.findViewById<View>(R.id.txtUsername) as TextView
        txtUserId.text = String.format("#ID: %d", users[pos].id)
        txtUsername.text = String.format("USER NAME: %s", users[pos].name)
        rowView.setOnClickListener { //start Activity User Form
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra("user_id", users[pos].id.toString())
            intent.putExtra("user_name", users[pos].name)
            context.startActivity(intent)
        }
        return rowView
    }
}