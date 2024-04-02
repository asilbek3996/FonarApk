package com.example.fonarapk

import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fonarapk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.flashOnBtn.setOnClickListener {
            onFlash()
        }

        binding.flashOffBtn.setOnClickListener {
            offFlash()
        }
    }
    private fun onFlash(){
        var cameraManager: CameraManager? = null
        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        try {
            var cameraId:String?=null
            cameraId = cameraManager.cameraIdList[0]
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager!!.setTorchMode(cameraId,true)
            }
        }catch (e:CameraAccessException){
            Toast.makeText(this,"Exception: "+e.message,Toast.LENGTH_SHORT).show()
        }

    }

    private fun offFlash(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val cameraManage = getSystemService(CAMERA_SERVICE) as CameraManager

            try {
                val cameraID = cameraManage.cameraIdList[0]
                cameraManage.setTorchMode(cameraID,false)
            }catch (e:CameraAccessException){

            }
        }
    }
}