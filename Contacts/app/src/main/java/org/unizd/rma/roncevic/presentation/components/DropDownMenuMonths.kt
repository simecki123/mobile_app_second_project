package org.unizd.rma.roncevic.presentation.components

import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.unizd.rma.roncevic.presentation.contact.analysis.AnalysisExpenseViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownMenuMonths(
    analysisExpenseViewModel: AnalysisExpenseViewModel
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = Modifier.width(150.dp)) {

        TextField(
            value = analysisExpenseViewModel.month,
            onValueChange = {
                // This can be left empty, as the value is managed by the ViewModel
            },
            readOnly = true,
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {

            listOf("January", "February", "March", "April", " May","June","July","August","September",
                "October","November","December").forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        analysisExpenseViewModel.onMonthChange(category)
                        isExpanded = false
                    }) {

                    Text(text = category)
                }
            }
        }
    }
}