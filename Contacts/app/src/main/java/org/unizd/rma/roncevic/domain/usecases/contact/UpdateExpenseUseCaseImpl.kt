package org.unizd.rma.roncevic.domain.usecases.contact

import org.unizd.rma.roncevic.domain.interfaces.ExpenseRepository
import org.unizd.rma.roncevic.domain.interfaces.usecases.UpdateExpenseUseCase
import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity

class UpdateExpenseUseCaseImpl constructor(private val expenseRepository: ExpenseRepository):
    UpdateExpenseUseCase {
    override suspend fun execute(id: Int, name:String, amount:Double, category:String, date:String, imageUri: String) {
        return expenseRepository.updateExpense(id, name, amount, category, date, imageUri)
    }
}