package org.unizd.rma.roncevic.domain.interfaces.usecases

import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity

interface UpdateExpenseUseCase {
    suspend fun execute(id: Int, name:String, amount:Double, category:String, date:String)
}