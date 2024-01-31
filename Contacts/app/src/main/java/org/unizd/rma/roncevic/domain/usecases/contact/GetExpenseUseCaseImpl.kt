package org.unizd.rma.roncevic.domain.usecases.contact

import org.unizd.rma.roncevic.domain.interfaces.ExpenseRepository
import org.unizd.rma.roncevic.domain.interfaces.usecases.GetExpenseUseCase
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity

class GetExpenseUseCaseImpl constructor(private val expenseRepository: ExpenseRepository):
    GetExpenseUseCase {
    override suspend fun execute(id: Int): ExpenseResponseEntity? {
        return expenseRepository.getExpense(id)
    }
}