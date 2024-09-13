package com.nimeshpatel.noteapp.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nimeshpatel.noteapp.R
import com.nimeshpatel.noteapp.components.NoteButton
import com.nimeshpatel.noteapp.components.NoteInputText
import com.nimeshpatel.noteapp.data.NoteDataSource
import com.nimeshpatel.noteapp.model.Note
import java.text.SimpleDateFormat

/**
 * Created by Nimesh Patel on 09-Sep-24.
 * Purpose:
 */

@Composable
fun NoteScreen(
    notes: List<Note>, onAddNotes: (Note) -> Unit, onRemoveNote: (Note) -> Unit
) {
    Column(modifier = Modifier.padding(6.dp)) {
        MyTopAppBar()
        InputSection(onAddNotes)
        NotesResult(notes,onRemoveNote)
    }

}

@Composable
private fun NotesResult(notes: List<Note>, onRemoveNote: (Note) -> Unit) {
    HorizontalDivider(modifier = Modifier.padding(10.dp))
    LazyColumn {
        items(notes) { note ->
            NoteRow(note = note, onNoteClick = {
                onRemoveNote(note)
            })
        }
    }
}

@Composable
private fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClick: (Note) -> Unit
) {
    Log.e("neem", "NoteRow: ", )
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomEnd = 33.dp))
            .fillMaxWidth(),
        color = Color(0XFFDFE6EB),
        shadowElevation = 6.dp
    ) {
        val formatter = SimpleDateFormat("EEE, d MMM YYY")
        Column(
            modifier = Modifier
                .clickable {
                    onNoteClick(note)
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Title: " + note.title,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Description: " + note.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Date: " + formatter.format(note.entryDate),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }

}

@Composable
private fun InputSection(onAddNotes: (Note) -> Unit) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
            text = title,
            label = "Title",
            onTextChange = {
                if (it.all { char -> char.isLetter() || char.isWhitespace() }) title = it
            })

        NoteInputText(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
            text = description,
            label = "Description",
            onTextChange = {
                if (it.all { char -> char.isLetter() || char.isWhitespace() }) description = it
            })

        NoteButton(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
            text = "Save",
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    // Notes Added
                    onAddNotes(Note(title = title, description = description))
                    Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
                    title = ""
                    description = ""
                }
            })
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MyTopAppBar() {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    }, actions = {
        Icon(
            imageVector = Icons.Rounded.Notifications, contentDescription = "Notification Icon"
        )
    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFDADFE3))
    )
}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNotes = {}, onRemoveNote = {})
}