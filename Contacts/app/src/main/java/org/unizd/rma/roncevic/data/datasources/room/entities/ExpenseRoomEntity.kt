package org.unizd.rma.roncevic.data.datasources.room.entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.unizd.rma.roncevic.domain.models.ExpenseRequestEntity
import org.unizd.rma.roncevic.domain.models.ExpenseResponseEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Entity(tableName = "expenses")
data class ExpenseRoomEntity(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val amount: Double,
    val category: String,
    val date: String,
    val imageUri: String
)

fun ExpenseRoomEntity.toExpenseResponseEntity(): ExpenseResponseEntity {
    return ExpenseResponseEntity(
        id = id!!,
        name = name,
        amount = amount,
        category = category,
        date = date,
        imageUri = Uri.parse(imageUri)

    )
}

fun ExpenseRequestEntity.toExpenseRoomEntity(): ExpenseRoomEntity{
    return ExpenseRoomEntity(
        id = id,
        name = name,
        amount = amount,
        category = category,
        date = date,
        imageUri = imageUri
    )
}