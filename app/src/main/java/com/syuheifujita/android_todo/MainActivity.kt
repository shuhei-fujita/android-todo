package com.syuheifujita.android_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.syuheifujita.android_todo.databinding.ActivityMainBinding
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getDefaultInstance()
        recyclerview.layoutManager = LinearLayoutManager(this)
        val todoModels = realm.where<TodoModel>().findAll()
        val adapter = TodoAdapter(todoModels)
        recyclerview.adapter = adapter

        fab.setOnClickListener {
            val intent = Intent(this, TodoEditActivity::class.java)
            startActivity(intent)
        }
        adapter.setOnItemClickListener { id ->
            val intent = Intent(this, TodoEditActivity::class.java)
                .putExtra("schedule_id", id)
            startActivity(intent)
        }

        addBorderLineRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun addBorderLineRecyclerView() {
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerview.addItemDecoration(itemDecoration)
    }
}
