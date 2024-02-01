package com.openclassrooms.tajmahal;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;

import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;
import com.openclassrooms.tajmahal.ui.restaurant.DetailsViewModel;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ReviewUnitTest {
    private DetailsViewModel detailsViewModel;
    private RestaurantRepository restaurantRepository;
    private MutableLiveData<List<Review>> mockLiveData;


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setup() {
        //mock repo
        restaurantRepository = mock(RestaurantRepository.class);

        //création avis de test en list
        List<Review> testReviews = new ArrayList<>();
        testReviews.add(new Review("Jeen", "https://xsgames.co/randomusers/assets/avatars/female/3.jpg", "Super restaurant", 5));
        testReviews.add(new Review("Roro", "https://xsgames.co/randomusers/assets/avatars/male/3.jpg", "Bonne expérience", 4));
        testReviews.add(new Review("jojo", "https://xsgames.co/randomusers/assets/avatars/male/5.jpg", "Pas mal, mais peut mieux faire", 3));

        //mettre en place la donnée initiale
        mockLiveData = new MutableLiveData<>();
        mockLiveData.setValue(testReviews);

        //config comportement simulé du reposi
        when(restaurantRepository.getReviews()).thenReturn(mockLiveData);

        //init viewmodel avec le repo mocké
        detailsViewModel = new DetailsViewModel(restaurantRepository);
    }


    @Test
    public void givenReviewModel_whenInitialized_thenCorrectValuesReturned() {
        //Création d'une instance de Review
        Review review = new Review("Alice", "https://xsgames.co/randomusers/assets/avatars/female/3.jpg", "Très bon restaurant", 4);

        //Vérification que les getters retournent les bonnes valeurs
        assertEquals("Alice", review.getUsername());
        assertEquals("https://xsgames.co/randomusers/assets/avatars/female/3.jpg", review.getPicture());
        assertEquals("Très bon restaurant", review.getComment());
        assertEquals(4, review.getRate());
    }
    @Test
    public void testFakeApiResponses() { //test de la fakeApi
        RestaurantFakeApi fakeApi = new RestaurantFakeApi();
        assertNotNull(fakeApi.getRestaurant());
        assertNotNull(fakeApi.getReviews());
    }

    @Test
    public void testAverageRating() {
        //data du test préconfigurée dans le before
        List<Review> reviews = mockLiveData.getValue();

        //appel de la méthode calculateAverageRating du ViewModel
        double averageRating = detailsViewModel.calculateAverageRating(reviews);

        //vérif de la note moyenne
        double expectedAverage = (5 + 4 + 3) / 3.0;
        assertEquals(expectedAverage, averageRating, 0.01);
    }

}
