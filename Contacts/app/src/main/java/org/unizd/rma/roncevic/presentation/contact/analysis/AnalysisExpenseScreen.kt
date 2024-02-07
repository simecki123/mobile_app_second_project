package org.unizd.rma.roncevic.presentation.contact.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.unizd.rma.roncevic.presentation.components.DropdownMenuMonths
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color


@Composable
fun AnalysisExpenseScreen(
    navController: NavController,
    analysisExpenseViewModel: AnalysisExpenseViewModel = hiltViewModel<AnalysisExpenseViewModel>()
) {

    LaunchedEffect(analysisExpenseViewModel.month) {
        analysisExpenseViewModel.getExpensesForAnalysis()
    }

    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "Analyze expenses")
                },
                actions = {
                    DropdownMenuMonths(analysisExpenseViewModel = analysisExpenseViewModel)
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(onClick = {
                        navController.navigate("list")
                    }) {
                        Text(text = "Back")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Text(
                    text = "Total monthly expense: ${analysisExpenseViewModel.moneySpent}",
                    modifier = Modifier.padding(16.dp)
                )
            }
        },
        content = {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                var num = 1
                items(analysisExpenseViewModel.expensesForMonth) { item ->
                    // Use a Box to wrap each row and set background color
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .background(if (num % 2 == 0) Color.Magenta else Color.Cyan)
                            .clickable { /* Handle row click if needed */ }
                    ) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            Text(text = num.toString())
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = item.name)
                            Spacer(modifier = Modifier.width(5.dp))

                            Button(onClick = {
                                navController.navigate("details/${item.id}")
                            }) {
                                Text(text = "See details")
                            }

                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }
                    num += 1
                }
            }

        }
    )
}
