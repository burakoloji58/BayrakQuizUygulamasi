package com.example.bayrakquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bayrakquizuygulamasi.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var ulas : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        ulas = ActivityResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ulas.root)

        val dogruSayac = intent.getIntExtra("dogruSayisi",0)

        ulas.textViewSonuc.setText("$dogruSayac Doğru ${dogruSayac-5} Yanlış")

        ulas.textViewBasari.setText("%${(dogruSayac*100)/5} Başarı")

        ulas.buttonTekrarDene.setOnClickListener {

            startActivity(Intent(this@ResultActivity,QuizActivity::class.java))
            finish()
        }

    }
}