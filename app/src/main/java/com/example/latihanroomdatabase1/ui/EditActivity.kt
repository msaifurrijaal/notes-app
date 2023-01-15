package com.example.latihanroomdatabase1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.latihanroomdatabase1.database.NoteViewModel
import com.example.latihanroomdatabase1.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private lateinit var noteViewModel: NoteViewModel
    private var title = binding.editTitle.text.toString()
    private var note = binding.editNote.text.toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        binding.buttonSave.setOnClickListener {
            noteViewModel.addNote(title, note)
            finish()
        }

    }
}