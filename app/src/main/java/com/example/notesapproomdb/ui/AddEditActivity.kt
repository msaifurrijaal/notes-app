package com.example.notesapproomdb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.notesapproomdb.database.NoteViewModel
import com.example.notesapproomdb.database.room.Note
import com.example.notesapproomdb.databinding.ActivityAddEditBinding
import java.text.SimpleDateFormat
import java.util.*

class AddEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBinding
    private val viewModel by viewModels<NoteViewModel>()
    companion object {
        const val NOTE_TYPE = "note_type"
        const val NOTE = "note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteType = intent.getStringExtra(NOTE_TYPE)
        val note: Note? = intent.getParcelableExtra(NOTE)

        if (noteType.equals("Edit")) {
            binding.idBtn.text = "Update Note"
            binding.idEdtNoteName.setText(note?.noteTitle)
            binding.idEdtNoteDesc.setText(note?.noteDescription)
        } else {
            binding.idBtn.text = "Save Note"
        }


        binding.idBtn.setOnClickListener {
            val noteTitle = binding.idEdtNoteName.text.toString()
            val noteDesc = binding.idEdtNoteDesc.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    viewModel.updateNote(Note(note!!.id, noteTitle, noteDesc, currentDateAndTime))
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    viewModel.addNote(Note(0, noteTitle, noteDesc, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}