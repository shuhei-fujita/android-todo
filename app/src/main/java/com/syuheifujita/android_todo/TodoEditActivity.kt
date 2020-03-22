package com.syuheifujita.android_todo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_todo_edit.*

class TodoEditActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_edit)
        realm = Realm.getDefaultInstance()

        button_save.setOnClickListener { view: View ->
            realm.executeTransaction { db:Realm ->
                val maxId = db.where<TodoModel>().max("id")
                val nextId = (maxId?.toLong() ?: 0L) + 1
                val todoModel = db.createObject<TodoModel>(nextId)
                todoModel.title = edit_title.text.toString()
                todoModel.detail = edit_detail.text.toString()
            }

            Snackbar.make(view, "追加しました", Snackbar.LENGTH_SHORT)
                .setAction("戻る") { finish() }
                .setActionTextColor(Color.YELLOW)
                .show()
        }

        button_delete.setOnClickListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
