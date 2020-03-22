package com.syuheifujita.android_todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class TodoAdapter(data: OrderedRealmCollection<TodoModel>) :
    RealmRecyclerViewAdapter<TodoModel, TodoAdapter.ViewHolder>(data, true) {

    private var listener: ((Long?) -> Unit)? = null
    fun setOnItemClickListener(listener: (Long?) -> Unit) {
        this.listener = listener
    }

    init {
        setHasStableIds(true)
    }

    class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
        val title: TextView = cell.findViewById(R.id.textview)
    }

    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        val todoModel: TodoModel? = getItem(position)
        holder.title.text = todoModel?.title
        holder.itemView.setOnClickListener {
            listener?.invoke(todoModel?.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val infrater = LayoutInflater.from(parent.context)
        val view = infrater.inflate(R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }
}
