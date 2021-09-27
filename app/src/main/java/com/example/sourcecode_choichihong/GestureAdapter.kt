package com.example.sourcecode_choichihong

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.content.Intent
import android.util.Log


class GestureAdapter(var gestureList : List<Gesture>) : RecyclerView.Adapter<GestureAdapter.GestureViewHolder>() {

    class GestureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvGesture = itemView.findViewById<TextView>(R.id.tvGesture)
        val buttonView = itemView.findViewById<Button>(R.id.buttonView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GestureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gesture, parent, false)
        return GestureViewHolder(view)
    }

    override fun getItemCount() : Int {
        return gestureList.size
    }

    override fun onBindViewHolder(holder: GestureViewHolder, position: Int) {
        val gesture : Gesture = gestureList[position]
        holder?.tvGesture?.setText(gesture.name)
        holder?.buttonView?.setText(gesture.aval)

        holder?.buttonView?.setOnClickListener {
            var intent = Intent(MainActivity.Statified.getContext(), GestureExpertVideoPlayerActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("gestureName", gesture.name)
            intent.putExtra("gestureVideoPath", gesture.videoPath)
            MainActivity.Statified.getContext()?.startActivity(intent)
        }
    }
}