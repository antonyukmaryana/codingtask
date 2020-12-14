package com.example.codingtask.data

import kotlinx.serialization.Serializable

@Serializable
data class APIResponse(val data:List<Module>)