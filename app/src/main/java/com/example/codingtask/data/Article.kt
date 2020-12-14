package com.example.codingtask.data

import kotlinx.serialization.Serializable

@Serializable
data class Article(val headline: String = "", val label: String = "", val url: String = "", val tease:String? = null) {

}