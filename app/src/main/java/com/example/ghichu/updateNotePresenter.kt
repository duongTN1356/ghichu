package com.example.ghichu

import android.content.Context
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.Locale

class updateNotePresenter(val mNoteInterface: updateNoteInterface, val sql: SQLiteHelper, val context: Context){
    fun showInfo(){
        mNoteInterface.showInfoNote()
    }

    fun onNoteItemClicked(model: Model) {
        val mes = Intent(context, updateNoteView::class.java)
        mes.putExtra("id", model.id)
        mes.putExtra("td", model.tieude)
        mes.putExtra("nd", model.noidung)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(model.day) // Chuyển ngày thành chuỗi định dạng
        mes.putExtra("d", formattedDate)
        context.startActivity(mes)
    }

    fun updateNote(model: Model){
        if (model.id != -1) {

            val isUpdated = sql.updateModel(model.id, model.tieude, model.noidung, model.day)
            if (isUpdated) {
                mNoteInterface.check(true)

            } else {
                mNoteInterface.check(false)
            }
        } else {
            mNoteInterface.showError("Không tìm thấy ghi chú để cập nhật")
        }

    }



}