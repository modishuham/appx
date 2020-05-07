package com.m.appx.api

import androidx.lifecycle.Observer
import com.m.appx.ui.authentication.EmailPasswordAuthentication
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException

class RetrofitCallback<T> : retrofit2.Callback<T> {
    override fun onFailure(call: Call<T>, throwable: Throwable) {
        if (throwable is UnknownHostException
            || throwable is ConnectException
            || throwable is IOException
        ) {

        } else {

        }

    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if(response.isSuccessful && response.code()==0) {

        }

    }
}