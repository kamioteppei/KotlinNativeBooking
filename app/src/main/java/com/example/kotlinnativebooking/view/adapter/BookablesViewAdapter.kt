package com.example.kotlinnativebooking.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kotlinnativebooking.R
import com.example.kotlinnativebooking.model.BookableData
import com.example.kotlinnativebooking.view.holder.BookablesViewHolder

class BookablesViewAdapter(context: Context, val data: List<BookableData>) : RecyclerView.Adapter<BookablesViewHolder>() {
    val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookablesViewHolder {
        // ここでViewHolderを作る
        return BookablesViewHolder(inflater.inflate(R.layout.listview_bookables, parent, false))
    }

    override fun getItemCount(): Int {
        // データの要素数を返す
        return data.size
    }

    override fun onBindViewHolder(holder: BookablesViewHolder, position: Int) {
        // ViewHolderを通してデータをViewに設定する
        val hotel = data[position].hotel
        holder.lblBookableHotel.text = if (hotel==null) { "" } else { hotel.name }
        holder.lblBookableCharge.text = if (hotel==null) { "" } else { "¥${hotel.charge.toString()} / 1泊" }
        holder.lblBookableRoomCount.text = if (hotel==null) { "" } else { "残り ${data[position].roomCount}部屋" }
        holder.lblBookableOption1.text = if (hotel==null) { "" } else { hotel.option1 }
        holder.lblBookableOption2.text = if (hotel==null) { "" } else { hotel.option2 }
        holder.lblBookableOption3.text = if (hotel==null) { "" } else { hotel.option3 }
    }
}