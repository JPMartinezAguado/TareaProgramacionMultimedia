package com.jpmartineza.tareaprogramacionmultimedia.data.Inyecciones

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import android.app.Application
import androidx.room.Room
import com.jpmartineza.tareaprogramacionmultimedia.data.network.ChisteService
import com.jpmartineza.tareaprogramacionmultimedia.data.network.RetrofitClient
import com.jpmartineza.tareaprogramacionmultimedia.data.repositorios.AnunciossRepositorio
import com.jpmartineza.tareaprogramacionmultimedia.data.repositorios.chisteRepositorio
import com.jpmartineza.tareaprogramacionmultimedia.data.room.AnunciosDB
import com.jpmartineza.tareaprogramacionmultimedia.data.room.AnunciosDBDao
import com.jpmartineza.tareaprogramacionmultimedia.domain.AddAnuncioUserCase
import com.jpmartineza.tareaprogramacionmultimedia.domain.GetAnunciosUserCase
import com.jpmartineza.tareaprogramacionmultimedia.domain.RemoveAnuncioUserCase
import com.jpmartineza.tareaprogramacionmultimedia.domain.obtenerChisteUserCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAnunciosRepository(dao: AnunciosDBDao): AnunciossRepositorio {
        return AnunciossRepositorio(dao)
    }

    @Provides
    fun provideGetAnunciosUseCase(repository: AnunciossRepositorio): GetAnunciosUserCase {
        return GetAnunciosUserCase(repository)
    }

    @Provides
    fun provideAddAnuncioUseCase(repository: AnunciossRepositorio): AddAnuncioUserCase {
        return AddAnuncioUserCase(repository)
    }

    @Provides
    fun provideRemoveAnuncioUseCase(repository: AnunciossRepositorio): RemoveAnuncioUserCase {
        return RemoveAnuncioUserCase(repository)
    }

    @Provides
    fun provideAnunciosDBDao(application: Application): AnunciosDBDao {
        return Room.databaseBuilder(
            application,
            AnunciosDB::class.java,
            "AnunciosDB"
        ).build().anunciosDao()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient()
    }
    @Provides
    @Singleton
    fun provideChisteService(retrofitClient: RetrofitClient): ChisteService {
        return retrofitClient.service
    }

    @Provides
    fun provideChisteRepositorio(retrofitClient: RetrofitClient): chisteRepositorio {
        return chisteRepositorio(retrofitClient)
    }

    @Provides
    fun provideObtenerChisteUseCase(chisteRepositorio: chisteRepositorio): obtenerChisteUserCase {
        return obtenerChisteUserCase(chisteRepositorio)
    }
}


