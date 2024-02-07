package org.unizd.rma.roncevic.data.datasources.room

import org.unizd.rma.roncevic.data.datasources.room.entities.toExpenseResponseEntity
import org.unizd.rma.roncevic.data.datasources.room.entities.toExpenseRoomEntity
import org.unizd.rma.roncevic.data.interfaces.ExpenseDao
import org.unizd.rma.roncevic.data.interfaces.ExpenseDataSource
import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity


class RoomExpenseDataSource constructor(private val dao: ExpenseDao) : ExpenseDataSource {
    override suspend fun getAll(): List<ExpenseResponseEntity> {
        return dao.getAll().toList().map {
            it.toExpenseResponseEntity()
        }
    }

    override suspend fun getOne(id: Int): ExpenseResponseEntity? {
        val entity = dao.getById(id)
        if (entity != null) {
            return entity.toExpenseResponseEntity()
        }
        return null
    }

    override suspend fun delete(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun update(id: Int, name:String, amount:Double, category:String, date:String, imageUri: String) {

        dao.update(id, name, amount, category, date, imageUri)
    }

    override suspend fun create(data: ExpenseRequestEntity) {
        dao.insert(data.toExpenseRoomEntity())
    }

}