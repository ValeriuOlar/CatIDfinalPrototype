package com.example.catid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ml.common.modeldownload.FirebaseLocalModel
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager
import com.google.firebase.ml.common.modeldownload.FirebaseRemoteModel

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val conditions = FirebaseModelDownloadConditions.Builder()
            .requireWifi()
            .build()
        val remoteModel = FirebaseRemoteModel.Builder("my_remote_model")
            .enableModelUpdates(true)
            .setInitialDownloadConditions(conditions)
            .setUpdatesDownloadConditions(conditions)
            .build()
        FirebaseModelManager.getInstance().registerRemoteModel(remoteModel)

        val localModel = FirebaseLocalModel.Builder("my_local_model")
            .setAssetFilePath("manifest.json")
            .build()
        FirebaseModelManager.getInstance().registerLocalModel(localModel)
    }
}
