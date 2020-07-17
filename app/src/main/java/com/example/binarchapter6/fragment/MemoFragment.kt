package com.example.binarchapter6.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.binarchapter6.R
import com.example.binarchapter6.activity.ProfileActivity
import com.example.binarchapter6.database.Memo
import com.example.binarchapter6.database.MemoDatabase
import kotlinx.android.synthetic.main.fragment_memo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class MemoFragment : DialogFragment(), View.OnClickListener {

    private var memoDb: MemoDatabase? = null

    fun newInstance(title: String): MemoFragment {
        val args = Bundle()
        args.putString("title", title)
        val fragment = MemoFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("title", "Title")
        dialog?.setTitle(title)
        edt_add_memo.requestFocus()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.MATCH_PARENT)

        btn_add_memo.setOnClickListener(this)
        btn_cancel.setOnClickListener(this)

        memoDb = this.activity?.let { MemoDatabase.getInstance(it) }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_add_memo -> {
                val date = getCurrentDate()
                val objectMemo = Memo(null, edt_add_memo.text.toString(), date)
                GlobalScope.launch {
                    val result = memoDb?.memoDao()?.insert(objectMemo)
                    activity?.runOnUiThread {
                        if (result != 0.toLong()) {
                            Toast.makeText(
                                view.context,
                                getString(R.string.add__memo_success),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                view.context,
                                getString(R.string.add_memo_failed),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        dialog?.dismiss()
                    }
                    (activity as ProfileActivity).fetchMemo()
                }
            }
            R.id.btn_cancel -> {
                dialog?.dismiss()
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
    }
}