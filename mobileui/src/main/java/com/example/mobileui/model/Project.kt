package com.example.mobileui.model

import com.example.mobileui.ui.Item

class Project(
    val id: String,
    val name: String,
    val fullName: String,
    val starCount: String,
    val dateCreated: String,
    val ownerName: String,
    val ownerAvatar: String,
    val isBookmarked: Boolean
): Item