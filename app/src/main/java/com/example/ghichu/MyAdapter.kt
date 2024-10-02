package com.example.ghichu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val modelList: List<Model>,val listener: OnItemClickListener, val longClickListener: OnItemLongClickListener) : RecyclerView.Adapter<MyHodel>() {
    interface OnItemClickListener {
        fun onItemClick(model: Model)
    }
    interface OnItemLongClickListener {
        fun onItemLongClick(model: Model)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHodel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyHodel(view)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun onBindViewHolder(holder: MyHodel, position: Int) {
        val model = modelList[position]
        holder.bind(model)

        holder.itemView.setOnClickListener{
            listener.onItemClick(model)
        }

        holder.itemView.setOnLongClickListener {
            longClickListener.onItemLongClick(model)
            true // Trả về true để cho biết sự kiện đã được xử lý
        }
    }

}