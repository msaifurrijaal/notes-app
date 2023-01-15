package com.example.latihanroomdatabase1.database.room

import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM note")
    fun getAllNotes() : List<Note>

}