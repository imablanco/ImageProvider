package com.ablanco.imageprovidersample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.ablanco.imageprovider.ImageProvider
import com.ablanco.imageprovider.ImageSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val imageProvider : ImageProvider by lazy {
        ImageProvider(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btFromCamera.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED){
                imageProvider.getImage(ImageSource.CAMERA)
            } else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 42424)
            }
        }

        btFromGallery.setOnClickListener {
            imageProvider.getImage(ImageSource.GALLERY)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        imageProvider.getImage(ImageSource.CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        ivPhoto.setImageBitmap(imageProvider.onActivityResult(requestCode, resultCode, data))
    }
}
