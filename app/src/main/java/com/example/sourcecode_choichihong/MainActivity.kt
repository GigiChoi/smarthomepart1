package com.example.sourcecode_choichihong

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.view.View
import android.widget.LinearLayout
import android.widget.LinearLayout.*
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    object Statified
    {
        var mContext: Context? = null
        fun getContext() : Context? {
            return Statified.mContext
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Statified.mContext = baseContext
        val rvGesture = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvGesture)
        val tvInstruction = findViewById<TextView>(R.id.tvInstruction)
        rvGesture?.visibility = View.INVISIBLE
        rvGesture.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)

        var gestureList = ArrayList<Gesture>()
        gestureList.add(Gesture("LightsOn", "/LightsOn/", "View"))
        gestureList.add(Gesture("LightsOff", "/LightsOff/", "View"))
        gestureList.add(Gesture("FanOn", "/FanOn/", "View"))
        gestureList.add(Gesture("FanOff", "/FanOff/", "View"))
        gestureList.add(Gesture("FanUp", "/FanUp/", "View"))
        gestureList.add(Gesture("FanDown", "/FanDown/","View"))
        gestureList.add(Gesture("SetThermo", "/SetThermo/", "View"))
        gestureList.add(Gesture("Num0", "/Num0/", "View"))
        gestureList.add(Gesture("Num1", "/Num1/", "View"))
        gestureList.add(Gesture("Num2", "/Num2/", "View"))
        gestureList.add(Gesture("Num3", "/Num3/", "View"))
        gestureList.add(Gesture("Num4", "/Num4/", "View"))
        gestureList.add(Gesture("Num5", "/Num5/", "View"))
        gestureList.add(Gesture("Num6", "/Num6/", "View"))
        gestureList.add(Gesture("Num7", "/Num7/", "View"))
        gestureList.add(Gesture("Num8", "/Num8/", "View"))
        gestureList.add(Gesture("Num9", "/Num9/", "View"))


        val adapter = GestureAdapter(gestureList)
        rvGesture.adapter = adapter
        rvGesture?.layoutManager = LinearLayoutManager(this)
        rvGesture.visibility = View.VISIBLE
    }
}