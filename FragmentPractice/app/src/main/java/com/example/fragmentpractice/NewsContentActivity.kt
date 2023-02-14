package com.example.fragmentpractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentpractice.databinding.ActivityNewsContentBinding

class NewsContentActivity : AppCompatActivity() {
    companion object {
        fun actionStart(context: Context, title: String, content: String){
            val intent = Intent(context, NewsContentActivity::class.java).apply{
                putExtra("news_title", title)
                putExtra("news_content", content)
            }
            context.startActivity(intent)
        }
    }
    private lateinit var binding: ActivityNewsContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //view binding
        binding = ActivityNewsContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //load params
        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")

        if(title != null && content != null){

            val frag: NewsContentFragment? =
                supportFragmentManager.findFragmentById(R.id.newsContentFrag) as NewsContentFragment?

            if (frag != null) {
                frag.refresh(title, content)
            }
        }
    }
}