package com.kalyan.di

import com.kalyan.repository.TobaccoRepository
import com.kalyan.repository.TobaccoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<TobaccoRepository> { TobaccoRepositoryImpl(get()) }
}