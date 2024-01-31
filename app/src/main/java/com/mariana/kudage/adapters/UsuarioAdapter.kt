package com.mariana.kudage.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mariana.kudage.R
import com.mariana.kudage.model.entity.Usuario

class UsuarioAdapter(private val listaUsuarios: List<Usuario>) : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNombre: TextView = view.findViewById(R.id.it_usuario_tv_nombre)
        private val tvEdad: TextView = view.findViewById(R.id.it_usuario_tv_edad)
        private val tvFecha: TextView = view.findViewById(R.id.it_usuario_tv_fecha)

        fun bind(usuario: Usuario) {
            tvNombre.text = "Nombre: ${usuario.nombre}"
            tvEdad.text = "Edad: ${usuario.edad}"
            tvFecha.text = "Fecha: ${usuario.fecha}"
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_usuario, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val usuario: Usuario = listaUsuarios[position]
        viewHolder.bind(usuario)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = listaUsuarios.size
}