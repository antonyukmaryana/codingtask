package com.example.codingtask

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingtask.data.Article
import com.example.codingtask.data.ArticleViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnItemSelectedListener{
    val model: ArticleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)

        model.filteredArticles.observe(this, Observer<List<Article>> { articles ->
            recyclerView.adapter = CustomAdapter(articles)
        })
        model.labels.observe(this, Observer { labels ->
            spinner.adapter = ArrayAdapter(this, R.layout.spinner_item, labels.toTypedArray())
            spinner.onItemSelectedListener = this
        })
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val label: String = parent?.getItemAtPosition(position).toString()
        model.selectLabel(label)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}