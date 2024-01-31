package org.unizd.rma.roncevic.domain.usecases.contact

import org.unizd.rma.roncevic.domain.interfaces.ExpenseRepository
import org.unizd.rma.roncevic.domain.interfaces.usecases.CreateExpenseUseCase
import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity

class CreateExpenseUseCaseImpl constructor(private val expenseRepository: ExpenseRepository):
    CreateExpenseUseCase {
    override suspend fun execute(expense: ExpenseRequestEntity) {
        expenseRepository.createExpense(expense)
    }
}