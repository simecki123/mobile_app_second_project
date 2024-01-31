package org.unizd.rma.roncevic.domain.usecases.contact

import org.unizd.rma.roncevic.domain.interfaces.ExpenseRepository
import org.unizd.rma.roncevic.domain.interfaces.usecases.GetAllExpenseUseCase
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity

class GetAllExpenseUseCaseImpl constructor(private val expenseRepository: ExpenseRepository):
    GetAllExpenseUseCase {
    override suspend fun execute(): List<ExpenseResponseEntity> {
        return expenseRepository.getExpense()
    }
}