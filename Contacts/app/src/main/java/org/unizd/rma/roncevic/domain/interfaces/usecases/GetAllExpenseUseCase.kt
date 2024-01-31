package org.unizd.rma.roncevic.domain.interfaces.usecases

import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity

interface GetAllExpenseUseCase {
    suspend fun execute(): List<ExpenseResponseEntity>
}