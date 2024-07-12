package com.example.bayrakquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bayrakquizuygulamasi.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var ulas : ActivityQuizBinding
    private lateinit var sorular : ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler : ArrayList<Bayraklar>
    private lateinit var dogruSoru : Bayraklar
    private lateinit var tumSecenekler : HashSet<Bayraklar>
    private lateinit var vt : VeritabaniYardimcisi

    private var dogruSayisi = 0
    private var yanlisSayisi = 0
    private var soruSayac = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        ulas = ActivityQuizBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ulas.root)

        vt = VeritabaniYardimcisi(this)

        sorular = bayraklardao().Rastgele5BayrakGetir(vt)

        soruYukle()

        ulas.buttonA.setOnClickListener {
            dogruKontrol(ulas.buttonA)
            soruSayacKontrol()
        }
        ulas.buttonB.setOnClickListener {
            dogruKontrol(ulas.buttonB)
            soruSayacKontrol()
        }
        ulas.buttonC.setOnClickListener {
            dogruKontrol(ulas.buttonC)
            soruSayacKontrol()
        }
        ulas.buttonD.setOnClickListener {
            dogruKontrol(ulas.buttonD)
            soruSayacKontrol()
        }

    }

    fun soruYukle(){
        ulas.textViewSoru.setText("${soruSayac+1}. SORU")

        dogruSoru = sorular.get(soruSayac)

        ulas.imageViewBayrak.setImageResource(resources.getIdentifier(dogruSoru.bayrak_resim,"drawable",packageName))

        yanlisSecenekler = bayraklardao().Rastgele3YalnısSecenekGetir(vt,dogruSoru.bayrak_id)

        tumSecenekler = HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler.get(0))
        tumSecenekler.add(yanlisSecenekler.get(1))
        tumSecenekler.add(yanlisSecenekler.get(2))

        ulas.buttonA.setText(tumSecenekler.elementAt(0).bayrak_ad)
        ulas.buttonB.setText(tumSecenekler.elementAt(1).bayrak_ad)
        ulas.buttonC.setText(tumSecenekler.elementAt(2).bayrak_ad)
        ulas.buttonD.setText(tumSecenekler.elementAt(3).bayrak_ad)

    }

    fun soruSayacKontrol(){
        soruSayac++

        if (soruSayac !=5)
        {
            soruYukle()
        }else{
            val intent = Intent(this@QuizActivity,ResultActivity::class.java)
            intent.putExtra("dogruSayisi",dogruSayisi)
            startActivity(intent)
            finish()
        }
    }

    fun dogruKontrol(button: Button){
        val butonYazi = button.text
        val dogruCevap = dogruSoru.bayrak_ad

        if (butonYazi == dogruCevap)
        {
            dogruSayisi++
        }
        else{
            yanlisSayisi++
        }

        ulas.textViewDogru.setText("DOĞRU: $dogruSayisi")
        ulas.textViewYanlS.setText("YANLIŞ: $yanlisSayisi")

    }





}