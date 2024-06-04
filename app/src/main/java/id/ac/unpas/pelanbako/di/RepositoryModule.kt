package id.ac.unpas.pelanbako.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.pelanbako.networks.ItemApi
import id.ac.unpas.pelanbako.persistences.ItemDao
import id.ac.unpas.pelanbako.repositories.TodoRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideTodoRepository(itemDao: ItemDao, itemApi: ItemApi): TodoRepository {
        return TodoRepository(itemApi, itemDao)
    }
}