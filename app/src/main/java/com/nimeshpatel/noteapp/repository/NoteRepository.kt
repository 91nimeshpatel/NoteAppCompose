package com.nimeshpatel.noteapp.repository

import com.nimeshpatel.noteapp.data.NoteDatabaseDao
import com.nimeshpatel.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Nimesh Patel on 14-Sep-24.
 * Purpose:
 */
class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    suspend fun addNote(note: Note) = noteDatabaseDao.addNote(note = note)

    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note = note)

    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note = note)

    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()

    suspend fun getNoteById(id: String) = noteDatabaseDao.getNoteById(id)

    suspend fun getNoteList() = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()

}