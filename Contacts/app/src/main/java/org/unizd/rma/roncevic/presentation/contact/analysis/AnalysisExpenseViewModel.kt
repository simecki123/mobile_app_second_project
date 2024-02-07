package org.unizd.rma.roncevic.presentation.contact.analysis

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.roncevic.domain.interfaces.usecases.GetAllExpenseUseCase
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity
import javax.inject.Inject

data class ExpenseAnalysisListResponseModel(
    val id: String,
    val name: String,
    val amount: String,
    val category: String,
    val date: String,
    val imageUri: Uri
)

fun ExpenseResponseEntity.toExpenseAnalysisListResponseModel():
        ExpenseAnalysisListResponseModel{
    return ExpenseAnalysisListResponseModel(
        id = id.toString(),
        name = name,
        amount = amount.toString(),
        category = category,
        date = date,
        imageUri = imageUri
    )
}

@HiltViewModel
class AnalysisExpenseViewModel @Inject constructor(
    private val getAllExpenseUseCase: GetAllExpenseUseCase
): ViewModel() {

    private val _errorMessage = mutableStateOf("")
    private val _reserveExpenses = mutableStateListOf<ExpenseAnalysisListResponseModel>()
    private val _chosenMonth = mutableStateOf("January")
    private val _moneySpent = mutableStateOf("0.0")

    val month: String
        get() = _chosenMonth.value

    val moneySpent: String
        get() = _moneySpent.value

    val errorMessage: String
        get() = _errorMessage.value

    val expensesForMonth: List<ExpenseAnalysisListResponseModel>
        get() = _reserveExpenses
            .filter { it.date.contains("/${getMonthNumber(month)}/") }
            .toList()

    private fun onMoneyChange() {
        var doubleMoney = 0.0
        for (expense: ExpenseAnalysisListResponseModel in expensesForMonth) {
            val am = expense.amount.toDouble()
            doubleMoney += am
        }
        _moneySpent.value = doubleMoney.toString()
    }

    fun onMonthChange(newMonth: String) {
        _chosenMonth.value = newMonth
        onMoneyChange()
    }

    private fun getMonthNumber(month: String): String {
        return when (month) {
            "January" -> "1"
            "February" -> "2"
            "March" -> "3"
            "April" -> "4"
            "May" -> "5"
            "June" -> "6"
            "July" -> "7"
            "August" -> "8"
            "September" -> "9"
            "October" -> "10"
            "November" -> "11"
            "December" -> "12"
            else -> "01" // Default to January if not recognized
        }
    }

    suspend fun getExpensesForAnalysis() {
        try {
            _reserveExpenses.clear()
            val list = getAllExpenseUseCase.execute()
            _reserveExpenses.addAll(list.map {
                it.toExpenseAnalysisListResponseModel()
            })
        } catch (e: Exception) {
            _errorMessage.value = "Greška ${e.message}"
        }
    }
}
