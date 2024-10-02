package com.example.ghichu

import java.sql.Date

class addNotePresenter(val maddNoteView: addNoteInterface, val sql: SQLiteHelper) {
    fun showAddNoteScreen(){
        maddNoteView.showNoteView()
    }
    fun addNote(tieude: String, noidung: String, day: Date) {
        // Kiểm tra dữ liệu đầu vào
        if (tieude.isEmpty() || noidung.isEmpty()) {
            maddNoteView.showError("Tiêu đề và nội dung không được để trống")
            return
        }else{
            // Tạo đối tượng Model, không cần cung cấp id vì SQLite sẽ tự động tăng giá trị
            val model = Model(id=0, tieude, noidung, day)

            // Thêm vào cơ sở dữ liệu

            val result = sql.insertModel(model)

            // Kiểm tra kết quả
            if (result > 0) {
                maddNoteView.check(true)
                maddNoteView.refreshNoteList()
            } else {
                maddNoteView.check(false)
            }

        }


    }
}