package fr.acos.externalstoragewithkoltin

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import java.io.File
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickEcrire(view: View)
    {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 14540)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 14540) {
            if (grantResults.size == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val dossier = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS)
                val file = File(dossier, "unFichier.txt")
                file.outputStream().use {
                    it.write("Je voyage".toByteArray())
                }
            }
        }
    }

    fun onClickLire(view: View)
    {
        val dossier = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS)
        val recuperation = File(dossier, "unFichier.txt").bufferedReader().readText();
        Toast.makeText(this,recuperation, Toast.LENGTH_LONG).show()
    }
}
