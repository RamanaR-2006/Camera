package com.example.camera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class Camera : AppCompatActivity() {
    private val REQUEST_IMAGE_CAPTURE = 1

    private lateinit var btnCapture: Button
    private lateinit var imagePreview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        btnCapture = findViewById(R.id.btnCapture)
        imagePreview = findViewById(R.id.imagePreview)

        btnCapture.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.let {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imagePreview.setImageBitmap(imageBitmap)

            if (imageBitmap == null) {
                Toast.makeText(this,"No image selected",Toast.LENGTH_LONG).show()
            }
        }
    }
}