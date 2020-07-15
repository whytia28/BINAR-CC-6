package com.example.binarchapter6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.binarchapter6.R
import com.example.binarchapter6.database.Memo

class MemoAdapter(private val listMemo: List<Memo>) : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMemo.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMemo[position])
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(memo: Memo) {
            
        }
    }
}