package com.example.ghichu

class deleteNotePresenter(val mdeleteNoteInterface: deleteNoteInterface, val sql: SQLiteHelper) {
    fun deleteNote(id: Int) {

        val isDeleted = sql.deleteModel(id)
        if (isDeleted) {
            mdeleteNoteInterface.checkdelete(true)

        } else {
            mdeleteNoteInterface.checkdelete(false)
        }
        mdeleteNoteInterface.refreshNoteList1()
    }

}