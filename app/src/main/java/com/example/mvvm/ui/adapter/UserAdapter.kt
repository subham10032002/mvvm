package com.example.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.models.User
import com.example.mvvm.ui.viewModel.GithubViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.ArrayList

class UserAdapter(val data: List<User>) : RecyclerView.Adapter<UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: User) = with(itemView) {
        usernameTv.text = item.login
        Picasso.get().load(item.avatarUrl).into(userImgView)

    }
}
