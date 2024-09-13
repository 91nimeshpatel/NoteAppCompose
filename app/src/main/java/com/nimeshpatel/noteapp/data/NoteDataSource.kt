package com.nimeshpatel.noteapp.data

import com.nimeshpatel.noteapp.model.Note

/**
 * Created by Nimesh Patel on 10-Sep-24.
 * Purpose:
 */
class NoteDataSource {

    fun loadNotes(): List<Note> {
        return listOf(
            Note(
                title = "Good Morning",
                description = "Get Ready for BreakFast"
            ),
            Note(
                title = "Good AfterNoon",
                description = "Get Ready For Lunch"
            ),
            Note(
                title = "Good Evening",
                description = "Get Ready for HighTea"
            ),
            Note(
                title = "Good Night",
                description = "Get ready for Dinner and for Sleep"
            ),

            )
    }
}