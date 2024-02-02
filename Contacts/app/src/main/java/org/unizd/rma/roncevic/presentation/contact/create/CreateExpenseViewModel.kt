package org.unizd.rma.roncevic.presentation.contact.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.roncevic.domain.interfaces.usecases.CreateExpenseUseCase
import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class CreateExpenseViewModel @Inject constructor(
    private val createExpenseUseCase: CreateExpenseUseCase
): ViewModel() {
    private val _errorMessage = mutableStateOf("")
    private val _name = mutableStateOf("")
    private val _amount = mutableStateOf("")
    private val _category = mutableStateOf("")
    private val _date = mutableStateOf("")
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

    suspend fun createExpense() {
        //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        //val realDate = LocalDate.parse(_date.value, formatter)
        try {
            createExpenseUseCase.execute(
                ExpenseRequestEntity(id = null, name = _name.value,
                amount = _amount.value.toDouble(), category = _category.value, date =_date.value, imageUri = _imageUri.value)
            )

        } catch (e: Exception) {
            _errorMessage.value = "Error ${e.message}"
        }
    }
}