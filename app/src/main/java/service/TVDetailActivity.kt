package com.example.themoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import model.TV


class TVDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TvS = "extra_tvs"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        img_item_photo.clipToOutline = true

        val detailTvs = intent.getParcelableExtra<TV>(TVDetailActivity.EXTRA_TvS)

        if (detailTvs != null){
            val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
            Glide.with(this).load(IMAGE_BASE + detailTvs.poster).into(img_item_photo)
            tv_item_name.text = detailTvs.title
            tv_item_description.text = detailTvs.overview

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}