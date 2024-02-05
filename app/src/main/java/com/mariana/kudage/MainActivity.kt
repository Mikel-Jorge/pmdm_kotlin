package com.mariana.kudage

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.mariana.kudage.activities.FormularioActivity
import com.mariana.kudage.activities.ListadoUsuariosActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
    }

    fun goToFormulario(view: View) {
        val intent = Intent(this, FormularioActivity::class.java)
        startActivity(intent)
    }

    fun goToListadoUsuarios(view: View) {
        val intent = Intent(this, ListadoUsuariosActivity::class.java)
        startActivity(intent)
    }

    fun notificacion(view: View) {
        var builder = NotificationCompat.Builder(this, "com.mariana.kudage")
            .setSmallIcon(R.drawable.icono_ejemplo)
            .setContentTitle("Titulo Notificación")
            .setContentText("Texto de ejemplo de notificación corta")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 123)
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, builder.build())
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Nombre canal ejemplo"
            val descriptionText = "Descripción app"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("com.mariana.kudage", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}