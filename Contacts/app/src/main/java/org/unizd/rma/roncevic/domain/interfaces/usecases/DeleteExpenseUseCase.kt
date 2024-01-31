package org.unizd.rma.roncevic.domain.interfaces.usecases

interface DeleteExpenseUseCase {
    suspend fun execute(id: Int)
}