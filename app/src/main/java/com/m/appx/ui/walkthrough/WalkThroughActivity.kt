package com.m.appx.ui.walkthrough

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.m.appx.R
import com.m.appx.ui.authentication.AuthenticationActivity
import kotlinx.android.synthetic.main.activity_walk_through.*


class WalkThroughActivity : AppCompatActivity() {

    private var selectedPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_through)

        vp_walk_through.adapter = WalkThroughAdapter()
        pageIndicatorView.count = 4

        vp_walk_through.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (4 > 1) {
                    val newProgress = (position + positionOffset) / (4 - 1)
                    ml.progress = newProgress
                }
            }

            override fun onPageSelected(position: Int) {
                selectedPosition = position
                when (position) {
                    0 -> tv_walkthrough_title.text =
                        "Create Your Service Profile And Get Ready For Provide Service"
                    1 -> tv_walkthrough_title.text = "Find Different Services In Your Location"
                    2 -> tv_walkthrough_title.text = "Find Service Provider Location"
                    3 -> tv_walkthrough_title.text = "Contanct And Chat With Service Provider"
                }
                pageIndicatorView.setSelected(position)
                super.onPageSelected(position)

            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

        tv_next.setOnClickListener {
            if (selectedPosition != 3)
                vp_walk_through.currentItem = selectedPosition + 1
        }

        tv_skip.setOnClickListener {
            startActivity(Intent(this, AuthenticationActivity::class.java))
            finish()
        }
        tv_start.setOnClickListener {
            startActivity(Intent(this, AuthenticationActivity::class.java))
            finish()
        }
    }
}
