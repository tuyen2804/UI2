package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2

class PhotoLibraryActivity : AppCompatActivity() {

    public lateinit var txtAll: TextView
    private lateinit var txtBrowser: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_library)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        txtAll = findViewById(R.id.txtAll)
        txtBrowser = findViewById(R.id.txtBrowser)

        val adapter = ViewLibraryAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "All"
                1 -> tab.text = "Browser"
            }
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateTextViewVisibility(position)
            }
        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { updateTextViewVisibility(it.position) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


    }
    private fun updateTextViewVisibility(position: Int) {
        when (position) {
            0 -> {
                txtAll.visibility = View.VISIBLE
                txtBrowser.isGone = true
            }
            1 -> {
                txtAll.isGone = true
                txtBrowser.visibility = View.VISIBLE
            }
            else -> {

                txtAll.isGone = true
                txtBrowser.isGone = true
            }
        }
    }
}
