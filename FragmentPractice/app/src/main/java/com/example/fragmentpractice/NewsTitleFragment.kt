package com.example.fragmentpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentpractice.databinding.NewsContentFragBinding
import com.example.fragmentpractice.databinding.NewsTitleFragBinding

class NewsTitleFragment : Fragment() {
    private var isTwoPane = false

    //view binding
    private var _binding: NewsTitleFragBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewsTitleFragBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isTwoPane = activity?.findViewById<View>(R.id.newsContentLayout) != null

        //add data to recyclerView
        val layoutManager = LinearLayoutManager(activity)
        binding.newsTitleRecyclerView.layoutManager = layoutManager
        binding.newsTitleRecyclerView.adapter = NewsAdapter(getNews())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getNews() : List<News>{
        val newsList = ArrayList<News>()
        for (i in 1..50) {
            val news = News("This is news title $i", getRandomLengthString("This is news content $i. "))
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthString(str: String): String {
            val n = (1..20).random()
            val builder = StringBuilder()
            repeat(n) {
                builder.append(str)
            }
            return builder.toString()
        }

    //inner class can direct use isTwoPane

    inner class NewsAdapter(val newsList: List<News>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.news_item, parent, false)
            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener{
                val news = newsList[holder.absoluteAdapterPosition]
                if(isTwoPane){
                    //big screen use two fragments together
                    val fragment = activity?.supportFragmentManager?.findFragmentById(R.id.newsContentFrag) as NewsContentFragment
                    fragment.refresh(news.title, news.content)
                }else{
                    //small screen
                    NewsContentActivity.actionStart(parent.context, news.title, news.content)
                }
            }
            return holder

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
        }

        override fun getItemCount(): Int = newsList.size
    }

}