package com.example.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cache.db.ProjectConstants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class CachedProjects(
    @PrimaryKey
    var id: String,
    val name: String,
    val fullName: String,
    val starCount: String,
    val dateCreated: String,
    val ownerName: String,
    val ownerAvatar: String,
    val isBookmarked: Boolean
)