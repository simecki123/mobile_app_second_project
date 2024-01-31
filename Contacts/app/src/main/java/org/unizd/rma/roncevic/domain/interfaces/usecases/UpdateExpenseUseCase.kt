package org.unizd.rma.roncevic.domain.interfaces.usecases

import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity

interface UpdateExpenseUseCase {
    suspend fun execute(id: Int, data: ExpenseRequestEntity)
}