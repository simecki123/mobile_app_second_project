package org.unizd.rma.roncevic.presentation.contact.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.unizd.rma.roncevic.ui.theme.PinkRed
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.rememberImagePainter
import kotlinx.coroutines.runBlocking
import org.unizd.rma.roncevic.R
import org.unizd.rma.roncevic.ui.theme.PinkRed

@Composable
fun ListExpenseScreen(
    navController: NavController,
    listExpenseViewModel: ListExpenseViewModel = hiltViewModel<ListExpenseViewModel>()
) {
    LaunchedEffect(Unit,
        block = {
            listExpenseViewModel.getExpenses()
        })

    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "List of expenses")
                },
                actions = {
                    Button(onClick = {
                        navController.navigate("analyse")
                    }) {
                        Text(text = "Analyse")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(onClick = {
                        navController.navigate("create")
                    }) {
                        Text(text = "New")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                var num = 1
                items(listExpenseViewModel.expenses) { item ->
                    // Use a Box to wrap each row and set background color
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .background(if (num % 2 == 0) Color.Magenta else Color.Cyan)
                            .clickable { /* Handle row click if needed */ }
                    ) {
                        Row {
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

                            Spacer(modifier = Modifier.width(5.dp))
                            Button(onClick = {
                                runBlocking {
                                    listExpenseViewModel.deleteExpenseById(item.id)
                                    navController.navigate("list")
                                }
                            }) {
                                Text(text = "Delete")
                            }

                            Spacer(modifier = Modifier.width(5.dp))

                            Spacer(modifier = Modifier.width(5.dp))

                            Button(onClick = {
                                runBlocking {
                                    navController.navigate("update${item.id}")
                                }
                            }) {
                                Text(text = "Update")
                            }
                        }
                    }
                    num += 1
                }
            }


        }
    }
}







