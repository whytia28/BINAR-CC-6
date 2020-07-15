package com.example.gamesuit.areaMain



import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gamesuit.MainActivity
import com.example.gamesuit.R
import com.example.gamesuit.logic.Controler
import kotlinx.android.synthetic.main.activity_pemain_vs_pemain.*
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*

class PemainVsPemain : AppCompatActivity() {

    private var pilihanSatu: String = ""
    private var pilihanDua: String = ""
    private var nama = MainActivity.namaPemain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemain_vs_pemain)

        pemain1.text = nama

        batu1.setOnClickListener {
            pilihanSatu = "batu"
            showResult()
            batu1.foreground = resources.getDrawable(R.drawable.overlay, null)
        }
        kertas1.setOnClickListener {
            pilihanSatu = "kertas"
            showResult()
            kertas1.foreground = resources.getDrawable(R.drawable.overlay, null)
        }
        gunting1.setOnClickListener {
            pilihanSatu = "gunting"
            showResult()
            gunting1.foreground = resources.getDrawable(R.drawable.overlay, null)
        }
        batu2.setOnClickListener {
            pilihanDua = "batu"
            showResult()
            batu2.foreground = resources.getDrawable(R.drawable.overlay, null)
        }
        kertas2.setOnClickListener {
            pilihanDua = "kertas"
            showResult()
            kertas2.foreground = resources.getDrawable(R.drawable.overlay, null)
        }
        gunting2.setOnClickListener {
            pilihanDua = "gunting"
            showResult()
            gunting2.foreground = resources.getDrawable(R.drawable.overlay, null)
        }
    }

    private fun showResult() {
        val pemenang: String
        if (pilihanSatu != "" && pilihanDua != "") {
            val control = Controler()
            val hasilMain = control.caraMain(pilihanSatu, pilihanDua)
            pemenang = when (hasilMain) {
                "pemain 1 menang" -> {
                    getString(R.string.selamat_kamu_menang, nama)
                }
                "pemain 2 menang" -> {
                    getString(R.string.selamat_pemain_2_menang)
                }
                else -> {
                    getString(R.string.hasil_draw)
                }
            }
            val builder = AlertDialog.Builder(this)
            val dialog = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
            builder.setView(dialog)
            builder.setCustomTitle(hasil)
            dialog.selamat.text = pemenang
            val dialogMessage = builder.create()
            dialogMessage.show()
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
