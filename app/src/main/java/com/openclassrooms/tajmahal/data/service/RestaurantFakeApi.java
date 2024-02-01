package com.openclassrooms.tajmahal.data.service;

import com.openclassrooms.tajmahal.domain.model.Restaurant;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Une implémentation fictive de {@link RestaurantApi} à des fins de test et de développement.
 * <p>
 * Cette classe simule une API en retournant un objet {@link Restaurant} codé en dur, éliminant le
 * besoin d'appels réels au réseau ou à la base de données. De telles implémentations fictives ou simulées sont couramment utilisées dans
 * les tests unitaires et lors du prototypage d'une application.
 * </p>
 * <p>
 * Pour les débutants : En développement logiciel, un mock est une version simulée d'un système externe
 * (comme une API). Les mocks sont utilisés pour isoler et tester certaines parties du logiciel sans
 * dépendre de systèmes externes. En utilisant un mock, les développeurs peuvent simuler comment le système réel
 * se comporterait. Dans ce cas, au lieu de faire un véritable appel API pour obtenir les détails d'un restaurant,
 * nous utilisons des valeurs codées en dur.
 * </p>
 *
 * <p>
 * Cette classe retourne les détails d'un restaurant spécifique, "Taj Mahal", avec des attributs prédéfinis.
 * </p>
 *
 * @see Restaurant
 * @see RestaurantApi
 */

public class RestaurantFakeApi implements RestaurantApi {

    private List<Review> reviews = new ArrayList<>(Arrays.asList(
            new Review("Ranjit Singh", "https://xsgames.co/randomusers/assets/avatars/male/71.jpg", "Service très rapide et nourriture délicieuse, nous mangeons ici chaque week-end, c'est très rapide et savoureux. Continuez ainsi!", 5),
            new Review("Martyna Siddeswara", "https://xsgames.co/randomusers/assets/avatars/female/31.jpg", "Un service excellent et des plats incroyablement savoureux. Nous sommes vraiment satisfaits de notre expérience au restaurant.", 4),
            new Review("Komala Alanazi", "https://xsgames.co/randomusers/assets/avatars/male/46.jpg", "La cuisine est délicieuse et le service est également excellent. Le propriétaire est très sympathique et veille toujours à ce que votre repas soit satisfaisant. Cet endroit est un choix sûr!", 5),
            new Review("David John", "https://xsgames.co/randomusers/assets/avatars/male/67.jpg", "Les currys manquaient de diversité de saveurs et semblaient tous à base de tomates. Malgré les évaluations élevées que nous avons vues et nos attentes, nous avons été déçus.", 2),
            new Review("Emilie Hood", "https://xsgames.co/randomusers/assets/avatars/female/20.jpg", "Très bon restaurant Indien ! Je recommande.", 4)
    ));

    /**
     * Récupère un objet {@link Restaurant} codé en dur pour le "Taj Mahal".
     * <p>
     * Cette méthode simule un appel API en retournant immédiatement un objet Restaurant
     * avec des attributs prédéfinis. L'objet représente le restaurant "Taj Mahal"
     * avec des détails spécifiques.
     * </p>
     *
     * @return L'objet {@link Restaurant} codé en dur pour le "Taj Mahal".
     */

    @Override
    public Restaurant getRestaurant() {
        return new Restaurant("Taj Mahal", "Indien", "11h30 - 14h30・18h30 - 22h00",
                "12 Avenue de la Brique - 75010 Paris", "http://www.tajmahal.fr", "06 12 34 56 78",
                true, true);
    }


    /**
     * Récupère un objet {@link Review} codé en dur pour le "Taj Mahal".
     * <p>
     * Cette méthode simule un appel API en retournant immédiatement une liste de critiques
     * avec des attributs prédéfinis.
     * </p>
     *
     * @return La liste codée en dur de {@link Review} pour le "Taj Mahal".
     */

    @Override
    public List<Review> getReviews() {

        return reviews;
    }

    public void addReview(Review review) {

        reviews.add(review);
    }

}
