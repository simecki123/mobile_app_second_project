package org.unizd.rma.roncevic.domain.usecases.contact

import org.unizd.rma.roncevic.domain.interfaces.ExpenseRepository
import org.unizd.rma.roncevic.domain.interfaces.usecases.DeleteExpenseUseCase

class DeleteExpenseUseCaseImpl constructor(private val expenseRepository: ExpenseRepository):
    DeleteExpenseUseCase {
    override suspend fun execute(id: Int) {
        expenseRepository.deleteExpense(id)
    }
}