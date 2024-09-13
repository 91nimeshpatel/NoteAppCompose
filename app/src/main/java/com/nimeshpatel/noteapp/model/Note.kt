package com.nimeshpatel.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "note_title")
    var title: String,

    @ColumnInfo(name = "note_desc")
    var description: String,

    @ColumnInfo(name = "note_entry_date")
    var entryDate: Date = Calendar.getInstance().time
)
