package org.unizd.rma.roncevic.presentation.contact.details


import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity
import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity
import org.unizd.rma.roncevic.domain.interfaces.usecases.GetExpenseUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsExpenseViewModel @Inject constructor(
    private val getExpenseUseCase: GetExpenseUseCase
) : ViewModel() {

    var expense: ExpenseResponseEntity? = ExpenseResponseEntity(
        id = 1,
        name = "",
        amount = 0.0,
        category = "",
        date = "",
        imageUri = Uri.parse("")
    )



    suspend fun loadExpenseDetails(id: String) {
        try {
            expense = getExpenseUseCase.execute(id.toInt())
        } catch (e: Exception) {
            // Handle the error (e.g., display an error message)
            expense = null
        }
    }


}






