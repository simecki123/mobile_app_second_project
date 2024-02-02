package org.unizd.rma.roncevic.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import org.unizd.rma.roncevic.presentation.contact.create.CreateExpenseViewModel
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownMenuExample(createExpenseViewModel: CreateExpenseViewModel) {
    var isExpanded by remember {
        mutableStateOf(false)
    }



    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }) {

        TextField(
            value = createExpenseViewModel.category,
            onValueChange = {
                // This can be left empty, as the value is managed by the ViewModel
            },
            readOnly = true,
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {

            listOf("Taxes", "Shopping", "Coffee", "Party", "Other").forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        createExpenseViewModel.onCategoryChange(category)
                        isExpanded = false
                    }) {

                    Text(text = category)
                }
            }
        }
    }
}





