package com.example.ghichu

interface addNoteInterface {
    fun showNoteView()
    fun refreshNoteList()
    fun showError(error: String)
    fun check(success: Boolean)
}