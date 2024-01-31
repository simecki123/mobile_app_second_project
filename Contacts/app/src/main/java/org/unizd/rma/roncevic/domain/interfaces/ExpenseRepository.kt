package org.unizd.rma.roncevic.domain.interfaces

import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity

interface ExpenseRepository {
    suspend fun getExpense(): List<ExpenseResponseEntity>
    suspend fun getExpense(id: Int): ExpenseResponseEntity?
    suspend fun deleteExpense(id: Int)
    suspend fun updateExpense(id: Int, data: ExpenseRequestEntity)
    suspend fun createExpense(data: ExpenseRequestEntity)
}