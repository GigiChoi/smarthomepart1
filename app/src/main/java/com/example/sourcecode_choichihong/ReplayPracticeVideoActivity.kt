package com.example.sourcecode_choichihong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import okhttp3.*



import java.io.File
import okhttp3.RequestBody

import okhttp3.MultipartBody

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReplayPracticeVideoActivity : AppCompatActivity() {

    interface ApiClient {

        @Multipart
        @POST("/")
        fun uploadFile(@Part file: MultipartBody.Part): retrofit2.Call<ResponseBody>

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_gesture)

        val buttonUpload = findViewById<Button>(R.id.buttonUpload)
        val buttonCancel = findViewById<Button>(R.id.buttonCancel)

        var gestureName = intent.extras?.getString("gestureName")
        var gestureVideoPath = intent.extras?.getString("gestureVideoPath")
        var numOfPractice = intent.extras?.getString("numOfPractice")
        var practiceVideoPath = intent.extras?.getString("practiceVideoPath")

        val playerView =
            findViewById<com.google.android.exoplayer2.ui.PlayerView>(R.id.idExoPlayerView)
        var player: SimpleExoPlayer? = null

        player = SimpleExoPlayer.Builder(this).build()
        playerView.setPlayer(player)


        val mediaItem: MediaItem = MediaItem.fromUri(practiceVideoPath.toString())
        player!!.setMediaItem(mediaItem);
        player!!.prepare();
        playerView.getVideoSurfaceView()!!.setRotation(0F);
        player!!.play();

        buttonUpload?.setOnClickListener {

            try {
                var practiceVideoFile: File = File(practiceVideoPath)
                val requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), practiceVideoFile)
                //val body = MultipartBody.Part.createFormData("file", practiceVideoFile.name, requestFile)
                val body = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", practiceVideoFile.name, requestFile)
                    .build()

                val client = OkHttpClient()

                var postUrl = "http://10.0.2.2:45457/"

                val request: Request = Request.Builder()
                    .url(postUrl)
                    .post(body)
                    .build()

                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    Log.d("File upload", "success")
                    Toast.makeText(this, "File uploaded successfully", Toast.LENGTH_LONG).show()
                } else {
                    Log.e("File upload", "failed")
                    Toast.makeText(this, "File uploading failed", Toast.LENGTH_LONG).show()
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                Log.e("File upload", "failed")
                Toast.makeText(this, "File uploading failed", Toast.LENGTH_LONG).show()
            }
        }

            //var practiceVideoFile = File(practiceVideoPath)
            //val postBody = practiceVideoFile.asRequestBody("video/*".toMediaTypeOrNull())

            buttonCancel?.setOnClickListener {
                var intentCancel = Intent(this, GestureExpertVideoPlayerActivity::class.java)
                intentCancel.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                intentCancel.putExtra("gestureName", gestureName)
                intentCancel.putExtra("gestureVideoPath", gestureVideoPath)
                MainActivity.Statified.getContext()?.startActivity(intentCancel)
            }
        }
}



