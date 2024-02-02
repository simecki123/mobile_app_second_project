package org.unizd.rma.roncevic.presentation.contact.list

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.roncevic.domain.interfaces.usecases.GetAllExpenseUseCase
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity
import java.time.LocalDate
import javax.inject.Inject

data class ExpenseListResponseModel(
    val id: String,
    val name: String,
    val amount: String,
    val category: String,
    val date: String,
    val imageUri: Uri
)

fun ExpenseResponseEntity.toExpenseListResponseModel():
        ExpenseListResponseModel {
    return ExpenseListResponseModel(
        id = id.toString(),
        name = name,
        amount = amount.toString(),
        category = category,
        date = date,
        imageUri = imageUri
    )
}

@HiltViewModel
class ListExpenseViewModel @Inject constructor(
    private val getAllExpenseUseCase: GetAllExpenseUseCase
): ViewModel() {
    private val _errorMessage = mutableStateOf("")
    private val _expenses = mutableStateListOf<ExpenseListResponseModel>()

    val errorMessage : String
        get() = _errorMessage.value

    val expenses: List<ExpenseListResponseModel>
        get() = _expenses.toList()

    // doesnt start at all for some reason. Like I dont use it at all
    suspend fun getExpenses() {

        try {
            _expenses.clear()
            val list = getAllExpenseUseCase.execute()
            _expenses.addAll(list.map {
                it.toExpenseListResponseModel()
            })
        }catch (e: Exception) {
            println("Nije upilo")
            _errorMessage.value = "Greška ${e.message}"
        }
    }
}