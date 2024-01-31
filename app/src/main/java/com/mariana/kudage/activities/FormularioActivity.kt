package com.mariana.kudage.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import com.mariana.kudage.MainActivity
import com.mariana.kudage.R
import com.mariana.kudage.model.database.LocalDatabase
import com.mariana.kudage.model.entity.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FormularioActivity : AppCompatActivity() {

    private lateinit var etNombre:EditText
    private lateinit var etEdad:EditText
    private lateinit var dpFecha:DatePicker
    private lateinit var btGuardar:Button
    private var etNombreOK = false
    private var etEdadOK = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        getElements()
        addInputListeners()

        // Desactivamos el botón guardar al principio ya que hay campos vacios
        btGuardar.isEnabled = false
    }

    private fun getElements() {
        etNombre = findViewById(R.id.form_et_nombre)
        etEdad = findViewById(R.id.form_et_edad)
        dpFecha = findViewById(R.id.form_dp_fecha)
        btGuardar = findViewById(R.id.form_bt_guardar)
    }

    private fun addInputListeners() {
        etNombre.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var contenido = s.toString().trim()
                if (contenido.length < 4) {
                    etNombre.error = "El nombre debe tener al menos 4 caracteres"
                    etNombreOK = false
                    btGuardar.isEnabled = false
                } else {
                    etNombreOK = true
                    btGuardar.isEnabled = formOK()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        etEdad.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var contenido = s.toString().trim()
                if (contenido.isEmpty()) {
                    etEdad.error = "La edad es obligatoria"
                    etEdadOK = false
                    btGuardar.isEnabled = false
                } else {
                    etEdadOK = true
                    btGuardar.isEnabled = formOK()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // TODO("Not yet implemented")
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // TODO("Not yet implemented")
            }
        })
    }

    private fun formOK() : Boolean {
        return etNombreOK && etEdadOK
    }

    fun onClickVolver(view: View) {
        finish()
    }

    fun onClickGuardar(view: View) {
        println("GUARDANDO")

        var localDB = LocalDatabase.getInstance(this)
        var usuario = Usuario(0, etNombre.text.toString(),
            etEdad.text.toString().toInt(), "1233123123")


        GlobalScope.launch(Dispatchers.IO) {
            localDB.userDao().insertAll(usuario)
        }
    }
}