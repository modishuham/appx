package com.m.appx.ui.chat.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.m.appx.R
import com.m.appx.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment : BaseFragment() {

    private lateinit var userListViewModel: UserListViewModel
    private var adapter: UserListAdapter = UserListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userListViewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        userListViewModel.getUserList().observe(this, Observer {
            adapter.addUsers(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_user_list.adapter = adapter
    }
}