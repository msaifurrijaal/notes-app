package com.example.latihanroomdatabase1.database

import com.example.latihanroomdatabase1.database.room.Note
import com.example.latihanroomdatabase1.database.room.NoteDao

class NoteRepository(private val noteDao : NoteDao) {

    val allNotes = noteDao.getAllNotes()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}