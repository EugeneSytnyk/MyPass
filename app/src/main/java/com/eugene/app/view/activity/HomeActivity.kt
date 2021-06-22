package com.eugene.app.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eugene.app.MyApplication
import com.eugene.app.R
import com.eugene.app.viewmodel.PassListViewModel

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}