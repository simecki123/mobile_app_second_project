package org.unizd.rma.roncevic.presentation.contact.update

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.roncevic.domain.interfaces.usecases.GetExpenseUseCase
import org.unizd.rma.roncevic.domain.interfaces.usecases.UpdateExpenseUseCase
import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class UpdateExpenseViewModel @Inject constructor(
    private val updateExpenseUseCase: UpdateExpenseUseCase
): ViewModel() {
    private val _errorMessage = mutableStateOf("")
    private val _name = mutableStateOf("")
    private val _amount = mutableStateOf("")
    private val _category = mutableStateOf("Other")
    private val calendar = Calendar.getInstance()
    private val _date = mutableStateOf(formatDate(calendar.time))
    private val _imageUri = mutableStateOf("")

    val name : String
        get() = _name.value

    val amount: String
        get() = _amount.value

    val category: String
        get() = _category.value

    val date: String
        get() = _date.value

    val imageUri: String
        get() = _imageUri.value

    val errorMessage: String
        get() = _errorMessage.value

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onAmountChange(newAmount: String){
        _amount.value = newAmount
    }

    fun onCategoryChange(newCategory: String) {
        _category.value = newCategory
    }

    fun onDateChange(newDate: String) {
        _date.value = newDate
    }

    fun onImageUriChange(newImageUri: String) {
        _imageUri.value = newImageUri
    }



    suspend fun updateExpense(id: String) {
        val ide = id.toInt()
       try {
           updateExpenseUseCase.execute(ide, _name.value, _amount.value.toDouble(), _category.value, _date.value, _imageUri.value)
       } catch (e: Exception) {
           _errorMessage.value = "Error ${e.message}"
       }
    }
}

private fun formatDate(date: Date): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return simpleDateFormat.format(date)
}
