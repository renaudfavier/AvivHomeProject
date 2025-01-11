package com.example.avivhomeproject

import kotlinx.serialization.Serializable

@Serializable
data object PropertyListRoute

@Serializable
data class PropertyDetailRoute(val id: Int)
