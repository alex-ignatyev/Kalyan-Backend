package com.kalyan.di

import com.kalyan.db.AdminDatabase
import com.kalyan.db.AdminDatabaseImpl
import com.kalyan.db.TobaccosDatabase
import com.kalyan.db.TobaccosDatabaseImpl
import com.kalyan.db.UsersDatabase
import com.kalyan.db.UsersDatabaseImpl
import org.koin.dsl.module

val databaseModule = module {
    single<AdminDatabase> { AdminDatabaseImpl() }
    single<UsersDatabase> { UsersDatabaseImpl() }
    single<TobaccosDatabase> { TobaccosDatabaseImpl() }
}
