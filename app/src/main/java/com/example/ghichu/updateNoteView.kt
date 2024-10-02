package com.example.ghichu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ghichu.databinding.ActivityNoteViewBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class updateNoteView : AppCompatActivity(), updateNoteInterface {
    lateinit var bind: ActivityNoteViewBinding
    lateinit var mupdateNotePresenter: updateNotePresenter
    lateinit var sql: SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityNoteViewBinding.inflate(layoutInflater)
        setContentView(bind.root)

        sql = SQLiteHelper(this)
        mupdateNotePresenter = updateNotePresenter(this, sql, this)
        mupdateNotePresenter.showInfo()

        val currentDate = Date() // Ngày hiện tại
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate: String = dateFormat.format(currentDate)
        val sqlDate: java.sql.Date = java.sql.Date(currentDate.time)

        bind.button.setOnClickListener {
            val model = Model(intent.getIntExtra("id", -1), bind.edttieude.text.toString(), bind.edtnoidung.text.toString(), sqlDate)
            mupdateNotePresenter.updateNote(model)
        }
    }

    override fun showInfoNote() {
        bind.edttieude.setText(intent.getStringExtra("td"))
        bind.edtnoidung.setText(intent.getStringExtra("nd"))
        bind.textView2.setText(intent.getStringExtra("d"))
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun check(success: Boolean) {
        if (success) {
            Toast.makeText(this, "Sửa ghi chú thành công", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Sửa ghi chú thất bại", Toast.LENGTH_SHORT).show()
        }
    }

    override fun refreshNoteList() {
        TODO("Not yet implemented")
    }
}