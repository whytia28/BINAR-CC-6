package com.example.binarchapter6.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.binarchapter6.R
import com.example.binarchapter6.activity.ProfileActivity
import com.example.binarchapter6.database.Memo
import com.example.binarchapter6.database.MemoDatabase
import kotlinx.android.synthetic.main.fragment_edit_memo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class EditMemoFragment : DialogFragment(), View.OnClickListener {

    private lateinit var objMemo: Memo
    private var memoDb: MemoDatabase? = null
    companion object {
        fun newInstance(title: String, objMemo: Memo): EditMemoFragment {
            val args = Bundle()
            args.putString("title", title)
            objMemo.id?.let { it1 -> args.putInt("id", it1) }
            args.putString("memo", objMemo.memo)
            args.putString("date", objMemo.date)
            val fragment = EditMemoFragment()
            fragment.arguments = args
            return fragment
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_memo, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            val args = arguments

            val id = args?.getInt("id", 0)
            val memo = args?.getString("memo", "memo")
            val date = args?.getString("date", "date")
             objMemo = Memo(id, memo, date)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("title", "Title")
        dialog?.setTitle(title)
        edt_add_memo.requestFocus()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.MATCH_PARENT)


        btn_update_memo.setOnClickListener(this)
        btn_delete_memo.setOnClickListener(this)
        btn_cancel.setOnClickListener(this)

        edt_add_memo.setText(objMemo.memo)
        edt_date.setText(objMemo.date)

        memoDb = this.activity?.let { MemoDatabase.getInstance(it) }

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_update_memo -> {
                objMemo.memo = edt_add_memo.text.toString()
                objMemo.date = edt_date.text.toString()

                GlobalScope.launch {
                    val result = memoDb?.memoDao()?.updateMemo(objMemo)
                    activity?.runOnUiThread {
                        if (result != null) {
                            Toast.makeText(
                                activity,
                                getString(R.string.update_success),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                activity,
                                getString(R.string.update_failed),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        dialog?.dismiss()
                    }
                    (activity as ProfileActivity).fetchMemo()
                }
            }

            R.id.btn_delete_memo -> {
                GlobalScope.launch {
                    val result = memoDb?.memoDao()?.deleteMemo(objMemo)
                    activity?.runOnUiThread {
                        if (result != null) {
                            Toast.makeText(
                                activity,
                                getString(R.string.delete_success),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                activity,
                                getString(R.string.delete_failed),
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

}