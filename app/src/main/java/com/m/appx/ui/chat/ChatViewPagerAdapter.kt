package com.m.appx.ui.chat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.m.appx.ui.chat.recentchat.RecentChatFragment
import com.m.appx.ui.chat.userlist.UserListFragment

class ChatViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val titles: ArrayList<String>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return titles.size
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return RecentChatFragment()
            1 -> return UserListFragment()
        }
        return RecentChatFragment()
    }
}