package com.mariana.kudage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mariana.kudage.activities.FormularioActivity
import com.mariana.kudage.activities.ListadoUsuariosActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToFormulario(view: View) {
        val intent = Intent(this, FormularioActivity::class.java)
        startActivity(intent)
    }

    fun goToListadoUsuarios(view: View) {
        val intent = Intent(this, ListadoUsuariosActivity::class.java)
        startActivity(intent)
    }
}