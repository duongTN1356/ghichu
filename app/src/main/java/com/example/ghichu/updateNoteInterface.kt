package com.example.ghichu

interface updateNoteInterface {
    fun showInfoNote()
    fun showError(error: String)
    fun check(success: Boolean)
    fun refreshNoteList()
}