package com.example.codingtask.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

object Repository {
    val json = Json {
        ignoreUnknownKeys = true
    }
    suspend fun loadData(): List<Article> = withContext(Dispatchers.IO) {
        val data = URL("https://s3.amazonaws.com/shrekendpoint/news.json").readText()
        val articles  = json.decodeFromString<APIResponse>(data).data.filter { it.type == "Section" }[0].items!!
        return@withContext articles
    }
}