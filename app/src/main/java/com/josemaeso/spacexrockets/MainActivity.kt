package com.josemaeso.spacexrockets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.josemaeso.spacexrockets.ui.list.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}