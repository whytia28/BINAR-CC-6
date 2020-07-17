package com.example.binarchapter6.adapter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_memo.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.binarchapter6.R
import com.example.binarchapter6.activity.ProfileActivity
import com.example.binarchapter6.database.Memo
import com.example.binarchapter6.fragment.EditMemoFragment


class AdapterMemo(private val listMemo: List<Memo>) :
    RecyclerView.Adapter<AdapterMemo.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_memo, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMemo.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMemo[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(memo: Memo) {
            with(itemView) {
                et_memo_date.setText(memo.date)
                et_add_memo.setText(memo.memo)
                cv_item_memo.setOnClickListener {
                    val activity = itemView.context as ProfileActivity
                    val dialog = EditMemoFragment.newInstance("Edit Your Memo", memo)
                    dialog.show(activity.supportFragmentManager, "Dialog Edit Fragment")

                }
            }
        }
    }
}

private operator fun Bundle?.invoke(args: Bundle?) {


}

