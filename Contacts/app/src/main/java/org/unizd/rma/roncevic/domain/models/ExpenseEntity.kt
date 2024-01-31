package org.unizd.rma.roncevic.domain.models

import android.net.Uri
import java.time.LocalDate


data class ExpenseResponseEntity(
    val id: Int,
    val name: String,
    val amount: Double,
    val category: String,
    val date: LocalDate,
    val imageUri: Uri
)

data class ExpenseRequestEntity(
    val id: Int? = null,
    val name: String,
    val amount: Double,
    val category: String,
    val date: String,
    val imageUri: String
)