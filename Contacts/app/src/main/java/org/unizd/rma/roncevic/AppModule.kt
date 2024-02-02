package org.unizd.rma.roncevic

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.unizd.rma.roncevic.data.datasources.room.RoomExpenseDataSource
import org.unizd.rma.roncevic.data.interfaces.ExpenseDataSource
import org.unizd.rma.roncevic.data.interfaces.ExpenseDatabase
import org.unizd.rma.roncevic.domain.interfaces.ExpenseRepository
import org.unizd.rma.roncevic.domain.interfaces.usecases.CreateExpenseUseCase
import org.unizd.rma.roncevic.domain.interfaces.usecases.DeleteExpenseUseCase
import org.unizd.rma.roncevic.domain.interfaces.usecases.GetAllExpenseUseCase
import org.unizd.rma.roncevic.domain.interfaces.usecases.UpdateExpenseUseCase
import org.unizd.rma.roncevic.domain.repositories.ExpenseRepositoryImpl
import org.unizd.rma.roncevic.domain.usecases.contact.CreateExpenseUseCaseImpl
import org.unizd.rma.roncevic.domain.usecases.contact.DeleteExpenseUseCaseImpl
import org.unizd.rma.roncevic.domain.usecases.contact.GetAllExpenseUseCaseImpl
import org.unizd.rma.roncevic.domain.usecases.contact.UpdateExpenseUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object AppModule {

    @Provides
    @Singleton
    fun providesExpenseDataSource(
        @ApplicationContext
        context: Context
    ): ExpenseDataSource {
        return RoomExpenseDataSource(
            dao = Room.databaseBuilder(
                context,
                ExpenseDatabase::class.java,
                ExpenseDatabase.DATABASE_NAME
            ).build().expenseDao
        )
    }

    @Provides
    @Singleton
    fun providesExpenseRepository(
        dataSource: ExpenseDataSource
    ): ExpenseRepository {
        return ExpenseRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetExpenseUseCase(
        repository: ExpenseRepository
    ): GetAllExpenseUseCase {
        return GetAllExpenseUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesDeleteExpenseUseCase(repository: ExpenseRepository): DeleteExpenseUseCase {
        return DeleteExpenseUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesUpdateExpenseUseCase(repository: ExpenseRepository): UpdateExpenseUseCase {
        return UpdateExpenseUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesCreateExpenseUseCase(
        repository: ExpenseRepository
    ): CreateExpenseUseCase {
        return CreateExpenseUseCaseImpl(repository)
    }
}