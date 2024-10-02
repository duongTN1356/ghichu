package com.example.ghichu

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.text.SimpleDateFormat
import java.util.Locale

class MyHodel(itemView: View) : ViewHolder(itemView) {
    val tvTieuDe: TextView = itemView.findViewById(R.id.txttieude)
    val tvNoiDung: TextView = itemView.findViewById(R.id.txtnoidung)
    val tvDay: TextView = itemView.findViewById(R.id.txtngaythang)

    fun bind(model: Model) {
        tvTieuDe.text = model.tieude
        tvNoiDung.text = model.noidung
        tvDay.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(model.day)
    }
}