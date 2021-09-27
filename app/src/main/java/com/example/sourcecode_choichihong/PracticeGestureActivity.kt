package com.example.sourcecode_choichihong

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Camera
import android.hardware.Camera.open
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.Log
import java.net.URI
import android.content.ContentValues
import android.database.Cursor
import androidx.loader.content.CursorLoader
import java.io.File as File1


class PracticeGestureActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        val REQUEST_CODE = 1

        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), REQUEST_CODE
        )

        super.onCreate(savedInstanceState)

        val intentRecordVideo = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        intentRecordVideo.putExtra("android.intent.extra.durationLimit", 5)
        startActivityForResult(intentRecordVideo, 101)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            var location = data!!.data
            Log.d("Recorded Video location:", location.toString())

            val data = arrayOf(MediaStore.Video.Media.DATA)
            val loader = CursorLoader(this, location!!, data, null, null, null)
            val cursor: Cursor? = loader.loadInBackground()
            val columnIndex: Int? = cursor?.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor?.moveToFirst()
            val practiceVideoPath = columnIndex?.let { cursor.getString(it) }

            var gestureName = intent.extras?.getString("gestureName")
            var gestureVideoPath = intent.extras?.getString("gestureVideoPath")
            var numOfPractice = intent.extras?.getString("numOfPractice")


            var intentUpload = Intent(this, ReplayPracticeVideoActivity::class.java)
            intentUpload.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            intentUpload.putExtra("gestureName", gestureName)
            intentUpload.putExtra("gestureVideoPath", gestureVideoPath)
            intentUpload.putExtra("practiceVideoPath", practiceVideoPath)
            intentUpload.putExtra("numOfPractice", numOfPractice)
            MainActivity.Statified.getContext()?.startActivity(intentUpload)

        }
    }

}

