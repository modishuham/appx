package com.m.appx.ui.chat

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.m.appx.R
import com.m.appx.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : BaseFragment() {

    private val tabsList: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabsList.apply {
            this.clear()
            this.add(getString(R.string.recentChat))
            this.add(getString(R.string.users))
        }

        view_pager_chats.adapter = ChatViewPagerAdapter(childFragmentManager, lifecycle, tabsList)
        TabLayoutMediator(tab_layout_chats, view_pager_chats,
            TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                tab.text = tabsList[position]
            }
        ).attach()
    }
}