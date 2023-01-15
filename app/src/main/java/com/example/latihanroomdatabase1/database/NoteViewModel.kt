package com.example.latihanroomdatabase1.database

import android.app.Application
import androidx.lifecycle.*
import com.example.latihanroomdatabase1.database.room.Note
import com.example.latihanroomdatabase1.database.room.NoteDB
import com.example.latihanroomdatabase1.database.room.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    // add repository
    private val repository : NoteRepository
    private val noteDao : NoteDao

    private var _notes : MutableLiveData<List<Note>>
    val notes : LiveData<List<Note>>
        get() = _notes

    // coroutine
    private var vmJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + vmJob)

    init {
        noteDao = NoteDB.getInstance(application).noteDao()
        repository = NoteRepository(noteDao)
        _notes = repository.allNotes as MutableLiveData<List<Note>>
    }

    fun addNote(title: String, note: String) {
        uiScope.launch {
            repository.addNote(Note(0, title, note))
        }
    }

    fun deleteNote(note: Note) {
        uiScope.launch {
            repository.deleteNote(note)
        }
    }

    fun updateNote(title: String, note: String) {
        uiScope.launch {
            repository.updateNote(Note(0, title, note))
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}