package com.m.appx.ui.serviceProfile

import android.R.attr.country
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.m.appx.R
import com.m.appx.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_create_service_profile.*


class ServiceProfileFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_service_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner_service.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }
        }

        var lauda = arrayOf("shubham", "modi", "mera")

        val aa = ArrayAdapter(context!!,
            android.R.layout.simple_spinner_item,
            lauda
        )
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_service.adapter = aa
    }
}