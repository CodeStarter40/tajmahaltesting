package com.openclassrooms.tajmahal.di;

import com.openclassrooms.tajmahal.data.service.RestaurantApi;
import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/**
 * AppModule est responsable de fournir des dépendances au niveau de l'application
 * pour l'ensemble de l'application. Ce module est installé dans le SingletonComponent
 * assurant que les instances fournies sont conservées tout au long du cycle de vie de l'application.
 */

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    /**
     * Fournit une instance singleton de RestaurantApi. Dans cet exemple,
     * une fausse implémentation de l'API est utilisée, ce qui peut être utile
     * lors des tests ou dans des scénarios de simulation.
     *
     * @return Une instance singleton de RestaurantFakeApi.
     */

    @Provides
    @Singleton
    public RestaurantApi provideRestaurantApi() {
        return new RestaurantFakeApi();
    }
}
