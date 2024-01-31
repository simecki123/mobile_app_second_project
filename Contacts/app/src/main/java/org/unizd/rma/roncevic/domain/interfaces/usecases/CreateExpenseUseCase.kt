package org.unizd.rma.roncevic.domain.interfaces.usecases

import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity

interface CreateExpenseUseCase {
    suspend fun execute(expense: ExpenseRequestEntity)
}