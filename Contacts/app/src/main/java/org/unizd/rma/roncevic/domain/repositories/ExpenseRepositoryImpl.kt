package org.unizd.rma.roncevic.domain.repositories

import org.unizd.rma.roncevic.data.interfaces.ExpenseDataSource
import org.unizd.rma.roncevic.domain.interfaces.ExpenseRepository
import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity


class ExpenseRepositoryImpl constructor(private val expenseDataSource: ExpenseDataSource):
    ExpenseRepository {
    override suspend fun getExpense(): List<ExpenseResponseEntity> {
        return expenseDataSource.getAll()
    }

    override suspend fun getExpense(id: Int): ExpenseResponseEntity? {
        return expenseDataSource.getOne(id)
    }

    override suspend fun deleteExpense(id: Int) {
        return expenseDataSource.delete(id)
    }

    override suspend fun updateExpense(id: Int, name:String, amount:Double, category:String, date:String) {

        expenseDataSource.update(id, name, amount, category, date)
    }

    override suspend fun createExpense(data: ExpenseRequestEntity) {
        expenseDataSource.create(data)
    }

}