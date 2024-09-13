package com.nimeshpatel.noteapp.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nimeshpatel.noteapp.model.Note
import com.nimeshpatel.noteapp.util.DateTypeConverter

/**
 * Created by Nimesh Patel on 13-Sep-24.
 * Purpose:
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao
}