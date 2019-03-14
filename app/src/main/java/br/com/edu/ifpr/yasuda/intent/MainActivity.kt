package br.com.edu.ifpr.yasuda.intent

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_ligar.setOnClickListener {
            ligarMasPedirPermissaoPrimeiro()
        }

        bt_site.setOnClickListener {
            val uri = Uri.parse("http://google.com")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        bt_email.setOnClickListener {
            mandaremail()
        }

        bt_msg.setOnClickListener {
            mandamsg()
        }

        bt_location.setOnClickListener {
            abrirlocation()
        }
        bt_compartilhar.setOnClickListener {
            compartilhar()
        }

    }

    private fun ligarMasPedirPermissaoPrimeiro(){
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),1)
        }
        else{
            ligar()
        }
    }

    private fun ligar() {
        val uri = Uri.parse("tel: 91776067")
        val intent = Intent(Intent.ACTION_CALL, uri)
        startActivity(intent)
    }

    private fun mandaremail() {
        val emailIntent = Intent(Intent.ACTION_SEND)
        with(emailIntent) {
            putExtra(Intent.EXTRA_SUBJECT, "Título do email")
            putExtra(Intent.EXTRA_TEXT, "Olá")
            putExtra(Intent.EXTRA_EMAIL, "diego.stiehl@ifpr.edu.br")
            type = "message/rfc822"
        }
        startActivity(emailIntent)
    }

    private fun mandamsg(){
        val uri = Uri.parse("sms: 91776067")
        val smsIntent = Intent(Intent.ACTION_SENDTO, uri)
        smsIntent.putExtra("sms_body", "Perdi!")
        startActivity(smsIntent)
    }

    private fun abrirlocation(){
        val GEO_URI = "geo:-25.4089185,-49.3222402"
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(GEO_URI))
        startActivity(intent)
    }

    private fun compartilhar(){
        val shareIntent = Intent(Intent.ACTION_SEND)
        with(shareIntent) {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Compartilhar")
            putExtra(Intent.EXTRA_TEXT, "Perdi!")
        }
        startActivity(shareIntent)
    }
}
