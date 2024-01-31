package org.unizd.rma.roncevic.data.interfaces

import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity

interface ExpenseDataSource {
    suspend fun getAll(): List<ExpenseResponseEntity>

    suspend fun getOne(id: Int): ExpenseResponseEntity?

    suspend fun delete(id: Int)

    suspend fun update(id: Int, expense: ExpenseRequestEntity)

    suspend fun create(data: ExpenseRequestEntity)
}