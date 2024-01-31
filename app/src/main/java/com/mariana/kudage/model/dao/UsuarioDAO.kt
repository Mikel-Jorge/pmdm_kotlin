package com.mariana.kudage.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mariana.kudage.model.entity.Usuario

@Dao
interface UsuarioDAO {
    @Query("SELECT * FROM usuario")
    fun getAll(): List<Usuario>

    @Query("SELECT * FROM usuario WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Usuario>

    @Query("SELECT * FROM usuario WHERE nombre LIKE :nombre AND " +
            "edad = :edad LIMIT 1")
    fun findByNombreYEdad(nombre: String, edad: Int): Usuario

    @Insert
    fun insertAll(vararg users: Usuario)

    @Delete
    fun delete(user: Usuario)
}