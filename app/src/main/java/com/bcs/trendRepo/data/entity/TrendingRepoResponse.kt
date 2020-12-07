package com.bcs.trendRepo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "trendRepoList"
)

data class TrendingRepoResponse(
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "author")
    var author: String,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "avatar")
    var avatar: String?,
    @ColumnInfo(name = "url")
    var url: String?,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "language")
    var language: String,
    @ColumnInfo(name = "languageColor")
    var languageColor: String,
    @ColumnInfo(name = "stars")
    var stars: Int?,
    @ColumnInfo(name = "forks")
    var forks: Int?,
    @ColumnInfo(name = "currentPeriodStars")
    var currentPeriodStars: Int?
)