package org.unizd.rma.roncevic.presentation.contact.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.rememberImagePainter
import org.unizd.rma.roncevic.R
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
        ) {
            LazyColumn(modifier = Modifier.fillMaxHeight() ) {
                items(listExpenseViewModel.expenses) {item ->
                    Row (modifier = Modifier.padding(2.dp)){
                        Text(text = item.id)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = item.name)
                        Spacer(modifier = Modifier.width(5.dp))

                        Button(onClick = {
                            navController.navigate("details/${item.id}")
                        }) {
                            Text(text = "See details")
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        Button(onClick = {
                            //navController.navigate("edit/${item.id}")
                        }) {
                            Text(text = "Edit")
                        }

                        Spacer(modifier = Modifier.width(5.dp))
                        Button(onClick = {
                            navController.navigate("list/${item.id}")
                        }) {
                            Text(text = "Delete")
                        }
                    }
                }
            }
        }
    }
}





