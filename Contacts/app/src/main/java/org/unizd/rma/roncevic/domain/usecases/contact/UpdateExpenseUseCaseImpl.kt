package org.unizd.rma.roncevic.domain.usecases.contact

import org.unizd.rma.roncevic.domain.interfaces.ExpenseRepository
import org.unizd.rma.roncevic.domain.interfaces.usecases.UpdateExpenseUseCase
import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity

class UpdateExpenseUseCaseImpl constructor(private val expenseRepository: ExpenseRepository):
    UpdateExpenseUseCase {
    override suspend fun execute(id: Int, data: ExpenseRequestEntity) {
        return expenseRepository.updateExpense(id, data)
    }
}