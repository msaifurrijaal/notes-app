package com.example.latihanroomdatabase1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.latihanroomdatabase1.database.NoteViewModel
import com.example.latihanroomdatabase1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.buttonCreate.setOnClickListener {
            startActivity(Intent(this@MainActivity, EditActivity::class.java))
        }


    }

    override fun onStart() {
        super.onStart()
        noteViewModel.notes.observe(this, {
            val notes = it
            Toast.makeText(this, "${notes[0].title}", Toast.LENGTH_SHORT).show()
        })
    }
}