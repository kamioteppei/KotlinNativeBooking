package com.example.kotlinnativebooking.view.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.kotlinnativebooking.R

class BookablesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val lblBookableHotel: TextView = itemView.findViewById(R.id.lblBookableHotel)
    val lblBookableCharge: TextView = itemView.findViewById(R.id.lblBookableCharge)
    val lblBookableRoomCount: TextView = itemView.findViewById(R.id.lblBookableRoomCount)
    val lblBookableOption1: TextView = itemView.findViewById(R.id.lblBookableOption1)
    val lblBookableOption2: TextView = itemView.findViewById(R.id.lblBookableOption2)
    val lblBookableOption3: TextView = itemView.findViewById(R.id.lblBookableOption3)
}