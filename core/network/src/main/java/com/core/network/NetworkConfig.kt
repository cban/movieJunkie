package com.core.network

data class NetworkConfig(
    val baseUrl: String,
    val enableLogging: Boolean,
    val headers: Map<String, String>
)
