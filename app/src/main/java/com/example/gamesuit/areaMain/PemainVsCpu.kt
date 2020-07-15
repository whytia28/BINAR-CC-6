package com.example.gamesuit.areaMain

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gamesuit.MainActivity
import com.example.gamesuit.R
import com.example.gamesuit.logic.Controler
import kotlinx.android.synthetic.main.activity_pemain_vs_cpu.*
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*


class PemainVsCpu : AppCompatActivity() {

    private var pilihanSatu: String = ""
    private var pilihanDua: String = ""
    private var nama = MainActivity.namaPemain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemain_vs_cpu)

        pemain1.text = nama

        batu1.setOnClickListener {
            pilihanSatu = Controler.pilihanGame[0]
            showResult()
            batu1.foreground = resources.getDrawable(R.drawable.overlay, null)
        }

        kertas1.setOnClickListener {
            pilihanSatu = Controler.pilihanGame[1]
            showResult()
            kertas1.foreground = resources.getDrawable(R.drawable.overlay, null)
        }

        gunting1.setOnClickListener {
            pilihanSatu = Controler.pilihanGame[2]
            showResult()
            gunting1.foreground = resources.getDrawable(R.drawable.overlay, null)
        }
    }

    private fun showResult() {
        val pemenang: String
        if (pilihanSatu != "") {
            val control = Controler()
            val hasilMain = control.caraMainCpu(pilihanSatu)
            pemenang = when (hasilMain) {
                "pemain 1 menang" -> {
                    getString(R.string.selamat_kamu_menang, nama)
                }
                "CPU 2 menang" -> {
                    getString(R.string.cpu_menang)
                }
                else -> {
                    getString(R.string.hasil_draw)
                }
            }
            when (Controler.pilihanCpu) {
                Controler.pilihanGame[0] -> {
                    batu2.foreground = resources.getDrawable(R.drawable.overlay, null)
                }

                Controler.pilihanGame[1] -> {
                    kertas2.foreground = resources.getDrawable(R.drawable.overlay, null)
                }

                Controler.pilihanGame[2] -> {
                    gunting2.foreground = resources.getDrawable(R.drawable.overlay, null)
                }
            }
            val builder = AlertDialog.Builder(this)
            val dialog = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
            builder.setView(dialog)
            builder.setCustomTitle(hasil)
            dialog.selamat.text = pemenang
            val dialogMessage = builder.create()
            val handler = Handler()
            handler.postDelayed({
                kotlin.run {
                    dialogMessage.show()
                }
            }, 1000)
            dialog.btn_exit.setOnClickListener {
                dialogMessage.dismiss()
                startNew()
            }
        }
    }

    private fun startNew() {
        pilihanSatu = ""
        pilihanDua = ""
        batu1.foreground = null
        batu2.foreground = null
        kertas1.foreground = null
        kertas2.foreground = null
        gunting1.foreground = null
        gunting2.foreground = null
    }
}