package com.example.codingtask.data

import kotlinx.serialization.Serializable

@Serializable
data class Module(val items:List<Article>? = null, val type:String)