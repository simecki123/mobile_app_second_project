package org.unizd.rma.roncevic.presentation.components

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import org.unizd.rma.roncevic.presentation.contact.create.CreateExpenseViewModel
import java.text.SimpleDateFormat


import java.util.*
@Composable
fun showDatePicker(context: Context, createExpenseViewModel: CreateExpenseViewModel){

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        {_: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${month + 1}/$year"
            createExpenseViewModel.onDateChange(date.toString())
        }, year, month, day
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Selected Date: ${date.value}")
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            datePickerDialog.show()
        }) {
            Text(text = "Open Date Picker")
        }
    }

}

// Function to format the date as needed
private fun formatDate(date: Date): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return simpleDateFormat.format(date)
}
