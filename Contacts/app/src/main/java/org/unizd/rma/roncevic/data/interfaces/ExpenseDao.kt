package org.unizd.rma.roncevic.data.interfaces

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import org.unizd.rma.roncevic.data.datasources.room.entities.ExpenseRoomEntity
import java.time.LocalDate

@Database(entities = [ExpenseRoomEntity::class], version = 2)
abstract class ExpenseDatabase: RoomDatabase(){
    abstract val expenseDao: ExpenseDao

    companion object {
        const val DATABASE_NAME = "expenses_db"
    }
}

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses")
    suspend fun getAll(): List<ExpenseRoomEntity>
    @Query("SELECT * FROM expenses WHERE id = :id")
    suspend fun getById(id: Int): ExpenseRoomEntity?
    @Query("DELETE FROM expenses WHERE id = :id")
    suspend fun deleteById(id: Int)
    @Query("UPDATE expenses SET name = :name, amount = :amount, category = :category, date = :date, imageUri = :imageUri WHERE id = :id")
    suspend fun update(id: Int, name: String, amount: Double, category: String, date: String, imageUri: String)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: ExpenseRoomEntity)
}