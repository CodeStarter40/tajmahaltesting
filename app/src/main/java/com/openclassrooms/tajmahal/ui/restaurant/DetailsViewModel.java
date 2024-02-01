package com.openclassrooms.tajmahal.ui.restaurant;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.domain.model.Restaurant;
import com.openclassrooms.tajmahal.domain.model.Review;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * MainViewModel est responsable de la préparation et de la gestion des données pour le {@link DetailsFragment}.
 * Il communique avec le {@link RestaurantRepository} pour récupérer les détails des restaurants et fournit
 * des méthodes utilitaires liées à l'interface utilisateur du restaurant.
 * Ce ViewModel est intégré avec Hilt pour l'injection de dépendances.
 */
@HiltViewModel
public class DetailsViewModel extends ViewModel {

    private  RestaurantRepository restaurantRepository;
    private MutableLiveData<List<Review>> reviewsLiveData;

    public void addReview(Review review) {

        restaurantRepository.addReview(review);
    }

    /**
     * Constructeur que Hilt utilisera pour créer une instance de MainViewModel.
     *
     * @param restaurantRepository Le répertoire qui fournira les données des restaurants.
     */
    @Inject
    public DetailsViewModel(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewsLiveData = new MutableLiveData<>();
        getReviews();
    }

    /**
     * Récupère les détails de la liste des reviews
     *
     * @return Un objet LiveData contenant les détails de la liste sur methode dans restaurantRepository
     */
    public LiveData<List<Review>> getReviews() { // methode pour acceder aux avis depuis l'exterieur du Vm
        return restaurantRepository.getReviews(); //accès aux data depuis le Repo, get la liste des reviews
    }


    //methode public calculaverage pour test
    public double calculateAverageRating(List<Review> reviews) {
        if (reviews != null && !reviews.isEmpty()) {
            double sum = 0;
            for (Review review : reviews) {
                sum += review.getRate();
            }
            return sum / reviews.size();
        }
        return 0.0;
    }


    /**
     * Récupère les détails du restaurant Taj Mahal.
     *
     * @return Un objet LiveData contenant les détails du restaurant Taj Mahal.
     */
    public LiveData<Restaurant> getTajMahalRestaurant() {
        return restaurantRepository.getRestaurant();
    }

    /**
     * Récupère le jour actuel de la semaine en français.
     *
     * @return Une chaîne représentant le jour actuel de la semaine en français.
     */
    public String getCurrentDay(Context context) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dayString;

        switch (dayOfWeek) {
            case Calendar.MONDAY:
                dayString = context.getString(R.string.monday);
                break;
            case Calendar.TUESDAY:
                dayString = context.getString(R.string.tuesday);
                break;
            case Calendar.WEDNESDAY:
                dayString = context.getString(R.string.wednesday);
                break;
            case Calendar.THURSDAY:
                dayString = context.getString(R.string.thursday);
                break;
            case Calendar.FRIDAY:
                dayString = context.getString(R.string.friday);
                break;
            case Calendar.SATURDAY:
                dayString = context.getString(R.string.saturday);
                break;
            case Calendar.SUNDAY:
                dayString = context.getString(R.string.sunday);
                break;
            default:
                dayString = "";
        }
        return dayString;
    }

}
