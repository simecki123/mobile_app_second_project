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

    var category by remember {
        mutableStateOf("Other")
    }




        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {isExpanded = it}) {

            TextField(value = category,
                onValueChange = {
                    createExpenseViewModel.onCategoryChange(category)
                },
                readOnly = true,
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                )
            
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                
                
                DropdownMenuItem(
                    onClick = {
                        category = "Taxes"
                        isExpanded = false
                    }) {
                    Text(text = "Taxes")
                }

                DropdownMenuItem(
                    onClick = {
                        category = "Shopping"
                        isExpanded = false
                    }) {
                    Text(text = "Shopping")
                }

                DropdownMenuItem(
                    onClick = {
                        category = "Coffee"
                        isExpanded = false
                    }) {
                    Text(text = "Coffee")
                }

                DropdownMenuItem(
                    onClick = {
                        category = "Party"
                        isExpanded = false
                    }) {
                    Text(text = "Party")
                }

                DropdownMenuItem(
                    onClick = {
                        category = "Other"
                        isExpanded = false
                    }) {
                    Text(text = "Other")
                }


            }

        }
    }




