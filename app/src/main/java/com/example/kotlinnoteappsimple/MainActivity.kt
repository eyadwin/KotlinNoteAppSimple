package com.example.kotlinnoteappsimple

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var listNotes = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadQueryAll()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.addNote -> {
                    var intent = Intent(this, NoteActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //to show the newly added note after we click Add button and move back to the previous screen
    override fun onResume() {
        super.onResume()
        loadQueryAll()
    }
    fun loadQueryAll() {

        var dbManager = NoteDbManager(this)
        val cursor = dbManager.queryAll()

        listNotes.clear()
        if (cursor.moveToFirst()) {

            do {
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                val title = cursor.getString(cursor.getColumnIndex("Title"))
                val content = cursor.getString(cursor.getColumnIndex("Content"))

                listNotes.add(content)

            } while (cursor.moveToNext())
        }

        var notesAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_expandable_list_item_1 ,
            listNotes)
        lvNotes.adapter = notesAdapter
    }
}
