package com.example.ghichu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ghichu.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class addNote : AppCompatActivity(), addNoteInterface{
    lateinit var bind: ActivityAddNoteBinding
    lateinit var maddNotePresenter: addNotePresenter
    lateinit var sql: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val currentDate = Date() // Ngày hiện tại
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate: String = dateFormat.format(currentDate)
        val sqlDate: java.sql.Date = java.sql.Date(currentDate.time)
        // Cập nhật nội dung cho TextView
        bind.textView2.text = formattedDate
        sql = SQLiteHelper(this)
        maddNotePresenter = addNotePresenter(this, sql)
        bind.button.setOnClickListener {

            maddNotePresenter.addNote(bind.edttieude.text.toString(), bind.edtnoidung.text.toString(), sqlDate)
        }
    }

    override fun showNoteView() {
        TODO("Not yet implemented")
    }

    override fun refreshNoteList() {

    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun check(success: Boolean) {
        if (success) {
            Toast.makeText(this, "Thêm ghi chú thành công", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Thêm ghi chú thất bại", Toast.LENGTH_SHORT).show()
        }
    }
}