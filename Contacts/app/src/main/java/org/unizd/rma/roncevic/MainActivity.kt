package org.unizd.rma.roncevic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import dagger.hilt.android.AndroidEntryPoint
import org.unizd.rma.roncevic.presentation.contact.analysis.AnalysisExpenseScreen
import org.unizd.rma.roncevic.presentation.contact.analysis.AnalysisExpenseViewModel
import org.unizd.rma.roncevic.presentation.contact.create.CreateExpenseScreen
import org.unizd.rma.roncevic.presentation.contact.create.CreateExpenseViewModel
import org.unizd.rma.roncevic.presentation.contact.details.DetailsExpenseScreen
import org.unizd.rma.roncevic.presentation.contact.details.DetailsExpenseViewModel
import org.unizd.rma.roncevic.presentation.contact.list.ListExpenseScreen
import org.unizd.rma.roncevic.presentation.contact.list.ListExpenseViewModel
import org.unizd.rma.roncevic.presentation.contact.update.UpdateExpenseScreen
import org.unizd.rma.roncevic.presentation.contact.update.UpdateExpenseViewModel
import org.unizd.rma.roncevic.ui.theme.ExpenseTheme
import kotlin.contracts.contract

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ExpenseTheme {
                val navController = rememberNavController()
                Router(navController = navController)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExpenseTheme {
        Greeting("Android")
    }
}

@Composable
fun Router(navController: NavHostController) {
    val listExpenseViewModel: ListExpenseViewModel = hiltViewModel()
    val detailsExpenseViewModel: DetailsExpenseViewModel = hiltViewModel()
    val createExpenseViewModel: CreateExpenseViewModel = hiltViewModel()
    val analysisExpenseViewModel: AnalysisExpenseViewModel = hiltViewModel()
    val updateExpenseViewModel: UpdateExpenseViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "list") {

        composable("list") {
            ListExpenseScreen(navController = navController, listExpenseViewModel = listExpenseViewModel)
        }

        composable("create") {

            CreateExpenseScreen(navController = navController, createExpenseViewModel)
        }

        // Update these on new solution
        composable("details/{id}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("id")
            //val expense = listExpenseViewModel.getExpenseById(itemId!!)
            if (itemId != null) {
                DetailsExpenseScreen(navController = navController, detailsExpenseViewModel = detailsExpenseViewModel , id = itemId)
            }
        }

        composable("analyse") {
            AnalysisExpenseScreen(navController = navController, analysisExpenseViewModel)
        }

        composable("update{id}"){ backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("id")
            if (itemId != null) {
                UpdateExpenseScreen(navController = navController, updateExpenseViewModel, itemId )
            }

        }






        // Add similar composable for "edit/{id}" if needed
    }
}



