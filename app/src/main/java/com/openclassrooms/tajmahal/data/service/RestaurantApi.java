package com.openclassrooms.tajmahal.data.service;

import androidx.lifecycle.LiveData;

import com.openclassrooms.tajmahal.domain.model.Restaurant;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.List;

/**
 * Interface pour la récupération de données de restaurant.
 * <p>
 * Représente l'API pour accéder aux informations des restaurants. Dans une application réelle,
 * cette interface pourrait être implémentée en utilisant une bibliothèque comme Retrofit, et inclurait des annotations
 * spécifiant les méthodes HTTP (comme GET, POST), l'URL du point de terminaison, et d'autres détails spécifiques à l'API.
 * </p>
 *
 * Pour les débutants : Une interface en Java est un plan d'une classe ou vous pouvez dire que c'est une collection de
 * méthodes abstraites et de constantes statiques. Dans une interface, chaque méthode est publique et abstraite mais elle ne
 * contient aucun constructeur. Une interface n'est pas une classe. Écrire une interface est similaire à écrire une classe,
 * mais ce sont deux concepts différents. Une classe décrit les attributs et les comportements d'un objet.
 * Une interface contient des comportements qu'une classe implémente.
 *
 * <p>
 * Ici, {@link RestaurantApi} fournit une méthode pour obtenir les détails d'un restaurant.
 * </p>
 *
 * @see Restaurant
 */

public interface RestaurantApi {

    /**
     * Récupère les détails d'un restaurant.
     * <p>
     * Cette méthode sera généralement connectée à un appel réseau ou à une requête de base de données dans sa
     * classe d'implémentation, récupérant les informations requises sur le restaurant.
     * </p>
     *
     * @return L'objet {@link Restaurant} contenant tous les détails du restaurant.
     */
    Restaurant getRestaurant();

    /**
     * Récupère toutes les critiques du restaurant.
     * <p>
     * Cette méthode sera généralement connectée à un appel réseau ou à une requête de base de données dans sa
     * classe d'implémentation, récupérant la liste des critiques existantes.
     * </p>
     *
     * @return L'objet {@link Restaurant} contenant tous les détails du restaurant.
     */

    List<Review> getReviews();

    void addReview(Review review);
}
