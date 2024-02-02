package org.unizd.rma.roncevic.presentation.contact.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import coil.compose.rememberImagePainter
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity
import org.unizd.rma.roncevic.presentation.contact.list.ExpenseListResponseModel
import org.unizd.rma.roncevic.presentation.contact.list.ListExpenseViewModel



@Composable
fun DetailsExpenseScreen(
    navController: NavController,
    detailsExpenseViewModel: DetailsExpenseViewModel,
    id: String
) {

    LaunchedEffect(Unit,
        block = {
            detailsExpenseViewModel.loadExpenseDetails(id)
        })
    var expense: ExpenseResponseEntity? = detailsExpenseViewModel.expense


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Details of expense")
                },
                actions = {
                    Button(onClick = {
                        navController.navigate("list")
                    }) {
                        Text(text = "Back")
                    }
                }
            )
        }
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            Text(text = "Name of expense")
            Spacer(modifier = Modifier.width(16.dp))
            Text(expense!!.name)
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Amount of money")
            Spacer(modifier = Modifier.width(16.dp))
            Text(expense.amount.toString())
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Category")
            Spacer(modifier = Modifier.width(16.dp))
            Text(expense.category)
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Date")
            Spacer(modifier = Modifier.width(16.dp))
            Text(expense.date)
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.width(16.dp))

            if (expense.imageUri.path?.isNotEmpty() == true) {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = rememberImagePainter(expense.imageUri),
                    contentDescription = "Image")
            } else {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = painterResource(id = org.unizd.rma.roncevic.R.drawable.ic_image),
                    contentDescription = "Image")
            }

        }
    }
}