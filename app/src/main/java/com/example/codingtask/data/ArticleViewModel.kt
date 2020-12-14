package com.example.codingtask.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {

    private var articles: List<Article>? = null

    init {
        refreshArticles()
    }

    fun refreshArticles() {
        viewModelScope.launch(Dispatchers.Default) {
            articles = Repository.loadData()
            articles?.let {
                val labelsList =
                    it.map { it.label }.distinct().toMutableList().apply { add(0, "ALL") }
                labels.postValue(labelsList)
                filteredArticles.postValue(it)
            }
        }
    }


    val filteredArticles: MutableLiveData<List<Article>> = MutableLiveData()

    val labels: MutableLiveData<List<String>> = MutableLiveData()

    fun selectLabel(label: String) {
        if (label == "ALL") {
            filteredArticles.postValue(articles)
        } else {
            articles?.let {
                val filtered = it.filter { it.label == label }
                filteredArticles.postValue(filtered)
            }
        }

    }


}