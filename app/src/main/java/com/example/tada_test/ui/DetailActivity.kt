package com.example.tada_test.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tada_test.R
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    lateinit var url: String
    lateinit var title: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        initIntent()
        initView()
    }

    private fun initView() {
        setTitle("Detail")

        tv_title.text = title
        Glide.with(this)
            .load(url)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(iv_art_image)
    }

    private fun initIntent() {
        url = intent.getStringExtra("url").toString()
        title = intent.getStringExtra("title").toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

