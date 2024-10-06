package com.mielandmoon.travelling_viajes.destination

import com.mielandmoon.travelling_viajes.destination.data.remote.DestinationService
import com.mielandmoon.travelling_viajes.destination.data.remote.DestinationServiceImpl
import com.mielandmoon.travelling_viajes.destination.data.repository.DestinationRepositoryImpl
import com.mielandmoon.travelling_viajes.destination.domain.repository.DestinationRepository
import com.mielandmoon.travelling_viajes.destination.domain.usecase.AddToFavoriteUseCase
import com.mielandmoon.travelling_viajes.destination.domain.usecase.DeleteFavoriteUseCase
import com.mielandmoon.travelling_viajes.destination.domain.usecase.GetDestinationUseCase
import com.mielandmoon.travelling_viajes.destination.domain.usecase.GetDestinationsUseCase
import com.mielandmoon.travelling_viajes.destination.domain.usecase.GetFavoriteDestinationsByUser
import com.mielandmoon.travelling_viajes.destination.domain.usecase.IsDestinationFavoriteUseCase
import com.mielandmoon.travelling_viajes.destination.presentation.viewmodel.DestinationDetailViewModel
import com.mielandmoon.travelling_viajes.destination.presentation.viewmodel.DestinationViewModel
import com.mielandmoon.travelling_viajes.destination.presentation.viewmodel.FavoriteViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val destinationModule = module {
    single<DestinationService> { DestinationServiceImpl(get()) }

    single<DestinationRepository> { DestinationRepositoryImpl(get()) }

    factory { GetDestinationsUseCase(get()) }
    factory { GetDestinationUseCase(get()) }
    factory { GetFavoriteDestinationsByUser(get()) }
    factory { IsDestinationFavoriteUseCase(get()) }
    factory { AddToFavoriteUseCase(get()) }
    factory { DeleteFavoriteUseCase(get()) }

    viewModelOf(::DestinationViewModel)
    viewModelOf(::DestinationDetailViewModel)
    viewModelOf(::FavoriteViewModel)
}
