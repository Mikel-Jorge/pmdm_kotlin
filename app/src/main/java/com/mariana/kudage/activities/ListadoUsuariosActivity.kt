package com.mariana.kudage.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mariana.kudage.R
import com.mariana.kudage.adapters.UsuarioAdapter
import com.mariana.kudage.model.database.LocalDatabase
import com.mariana.kudage.model.entity.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListadoUsuariosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_usuarios)

        recyclerView = findViewById(R.id.listado_recyclerView)
        rellenarRecyclerView()
    }

    fun rellenarRecyclerView() {
        var localDatabase: LocalDatabase = LocalDatabase.getInstance(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        // recyclerView.layoutManager = GridLayoutManager(this, 3)

        GlobalScope.launch(Dispatchers.IO) {
            val usuarios: List<Usuario> = localDatabase.userDao().getAll()
            recyclerView.adapter = UsuarioAdapter(usuarios)
        }
    }

    fun volver(view: View) {
        finish()
    }
}