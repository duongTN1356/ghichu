package com.example.ghichu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ghichu.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity(), addNoteInterface, updateNoteInterface, MyAdapter.OnItemClickListener, deleteNoteInterface, MyAdapter.OnItemLongClickListener{

    lateinit var bind: ActivityMainBinding
    lateinit var mupdateNotePresenter: updateNotePresenter
    lateinit var maddNotePresenter: addNotePresenter
    lateinit var sql: SQLiteHelper
    lateinit var adapter: MyAdapter
    lateinit var modelList: List<Model>
    lateinit var mdeleteNotePresenter: deleteNotePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        sql = SQLiteHelper(this)

        mupdateNotePresenter = updateNotePresenter(this, sql, this)
        bind.rcv.layoutManager = LinearLayoutManager(this)

        mdeleteNotePresenter = deleteNotePresenter(this, sql)

        modelList = sql.getAllModels()
        adapter = MyAdapter(modelList, this,this)
        bind.rcv.adapter = adapter

        maddNotePresenter = addNotePresenter(this, sql)

        bind.btnAdd.setOnClickListener {
            showAdd()
        }
    }

    private fun showAdd() {
        maddNotePresenter.showAddNoteScreen()
    }


    override fun showNoteView() {
        val mes = Intent(this, addNote::class.java)
        startActivity(mes)
    }

    override fun refreshNoteList() {
        modelList = sql.getAllModels()
        adapter.notifyDataSetChanged()
    }

    override fun refreshNoteList1() {
        modelList = sql.getAllModels()
        adapter.notifyDataSetChanged()

    }

    override fun checkdelete(success: Boolean) {
        if (success) {
            Toast.makeText(this, "Xóa ghi chú thành công", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Xóa ghi chú thất bại", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showError(error: String) {
        TODO("Not yet implemented")
    }

    override fun check(success: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(model: Model) {
        mupdateNotePresenter.onNoteItemClicked(model)
    }

    override fun showInfoNote() {
        TODO("Not yet implemented")
    }

    override fun onItemLongClick(model: Model) {
        mdeleteNotePresenter.deleteNote(model.id)
    }


}