package com.m.appx.ui.chat.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.m.appx.databinding.ItemUserListBinding
import com.m.appx.ui.authentication.User

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    private var userList: ArrayList<User> = ArrayList()

    class UserListViewHolder(itemView: ItemUserListBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding: ItemUserListBinding = itemView
        fun bind(user: User) {
            binding.user = user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun addUsers(users: ArrayList<User>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }
}