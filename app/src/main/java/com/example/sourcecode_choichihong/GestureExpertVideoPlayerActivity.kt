package com.example.sourcecode_choichihong

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.exoplayer2.util.Log


class GestureExpertVideoPlayerActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {

        val REQUEST_CODE = 1

        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), REQUEST_CODE
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expert_video)

        val domain = "http://10.0.2.2:45457"
        var gestureName = intent.extras?.getString("gestureName")
        var gestureVideoPath = intent.extras?.getString("gestureVideoPath")
        val url = domain + gestureVideoPath
        val buttonPractice = findViewById<Button>(R.id.buttonPractice)
        val playerView = findViewById<com.google.android.exoplayer2.ui.PlayerView>(R.id.idExoPlayerView)
        var player: SimpleExoPlayer? = null

        player = SimpleExoPlayer.Builder(this).build()
        playerView.setPlayer(player)

        val mediaItem: MediaItem = MediaItem.fromUri(url)
        player!!.setMediaItem(mediaItem);
        player!!.prepare();
        playerView.getVideoSurfaceView()!!.setRotation(0F);
        player!!.play();

        var mglobalVal = globalVal()

        buttonPractice?.setOnClickListener {


            var numOfPractice = mglobalVal.numOfPractice + 1

            var intentUpload = Intent(this, PracticeGestureActivity::class.java)
            intentUpload.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            intentUpload.putExtra("gestureName", gestureName)
            intentUpload.putExtra("gestureVideoPath", gestureVideoPath)
            intentUpload.putExtra("numOfPractice", numOfPractice)
            MainActivity.Statified.getContext()?.startActivity(intentUpload)

        }



        /**
        buttonPractice?.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
                takeVideoIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takeVideoIntent, 0)
                }
            }
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("gestureName", gestureName)
            intent.putExtra("android.intent.extra.durationLimit", 50000)
            MainActivity.Statified.getContext()?.startActivity(intent)
        **/
    }

}
