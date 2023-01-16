package com.example.notesapproomdb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapproomdb.R
import com.example.notesapproomdb.database.NoteViewModel
import com.example.notesapproomdb.database.room.Note
import com.example.notesapproomdb.databinding.ActivityMainBinding

// extend interface noteClickListener & NoteClickDelete
class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<NoteViewModel>()
    lateinit var notesRV: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteRVAdapter = NoteRVAdapter(this,this,this)

        binding.notesRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteRVAdapter
        }

        viewModel.allNotes.observe(this, Observer {
            it?.let {
                noteRVAdapter.updateList(it)
            }
        })

        binding.idFAB.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddEditActivity::class.java))
            this.finish()
        }

    }

    override fun onNoteClick(note: Note) {
        // opening a new intent and passing a data to it.
        val intent = Intent(this@MainActivity, AddEditActivity::class.java)
        intent.putExtra(AddEditActivity.NOTE_TYPE, "Edit")
        intent.putExtra(AddEditActivity.NOTE, note)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.
        viewModel.deleteNote(note)
        // displaying a toast message
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }


}