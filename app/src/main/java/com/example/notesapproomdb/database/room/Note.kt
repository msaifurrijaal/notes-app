package com.example.notesapproomdb.database.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notesTable")
data class Note (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "title") var noteTitle: String,
    @ColumnInfo(name = "description") var noteDescription: String,
    @ColumnInfo(name = "timestamp") var timeStamp: String,
        ) : Parcelable
