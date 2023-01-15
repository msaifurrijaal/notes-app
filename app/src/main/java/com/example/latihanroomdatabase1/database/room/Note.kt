package com.example.latihanroomdatabase1.database.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Note (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val title: String,
        val note: String
        ) : Parcelable