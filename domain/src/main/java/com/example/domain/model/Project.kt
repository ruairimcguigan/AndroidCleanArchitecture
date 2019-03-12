package com.example.domain.model

data class Project(
    val id: String,
    val name: String,
    val fullName: String,
    val startCount: String,
    val dateCreated: String,
    val ownerName: String,
    val ownerAvatar: String,
    val isBookmarked: Boolean)