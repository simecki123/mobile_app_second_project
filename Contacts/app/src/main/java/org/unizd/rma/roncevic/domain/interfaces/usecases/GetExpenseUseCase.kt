package org.unizd.rma.roncevic.domain.interfaces.usecases

import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity

interface GetExpenseUseCase {
    suspend fun execute(id: Int): ExpenseResponseEntity?
}