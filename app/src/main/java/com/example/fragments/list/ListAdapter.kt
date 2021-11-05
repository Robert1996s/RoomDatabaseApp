package com.example.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Data.User
import com.example.myroomdatabaseexample.R

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val userName: TextView = itemView.findViewById(R.id.first_name_text)
        val userLastName: TextView = itemView.findViewById(R.id.last_name_text)
        val userAge: TextView = itemView.findViewById(R.id.age_text)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = userList[position]

        holder.userName.text  = item.firstName
        holder.userLastName.text = item.lastName
        holder.userAge.text =  item.age.toString()

    }


    fun setData(user: List<User>){
        this.userList =  user
        notifyDataSetChanged()
    }




}