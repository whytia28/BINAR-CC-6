package com.example.binarchapter6.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.binarchapter6.R
import com.example.binarchapter6.areaMain.PemainVsCpu
import com.example.binarchapter6.areaMain.PemainVsPemain
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity(), View.OnClickListener  {

    companion object {
        var namaPemain: String? = ""
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("suitGame", Context.MODE_PRIVATE)

        namaPemain = sharedPreferences.getString("name", "Pemain 1")
        tv_pemain.text = getString(
            R.string.vs_pemain,
            namaPemain
        )
        tv_cpu.text = getString(
            R.string.vs_cpu,
            namaPemain
        )

        pemainvspemain.setOnClickListener(this)
        pemainvscpu.setOnClickListener(this)
        iv_profile.setOnClickListener(this)
        btn_exit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.pemainvspemain -> {
                val moveIntent = Intent(this@MainActivity, PemainVsPemain::class.java)
                startActivity(moveIntent)
            }
            R.id.pemainvscpu -> {
                val moveIntent = Intent(this@MainActivity, PemainVsCpu::class.java)
                startActivity(moveIntent)
            }
            R.id.iv_profile -> {
                val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_exit -> {
                finish()
            }
        }
    }
}
