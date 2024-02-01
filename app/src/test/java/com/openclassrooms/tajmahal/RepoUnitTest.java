package com.openclassrooms.tajmahal;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;

import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import static org.junit.Assert.*;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RepoUnitTest {
    private RestaurantRepository restaurantRepository;



    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setup() {

        restaurantRepository = new RestaurantRepository(new RestaurantFakeApi());

    }
    @Test
    public void testAddReview() throws InterruptedException { //test d'ajout review
        //création d'un nouvel avis
        Review newReview = new Review("Jeane","https://xsgames.co/randomusers/assets/avatars/female/3.jpg","Super restaurant",5);
        LiveData<List<Review>> review = restaurantRepository.getReviews();
        //ajout via méthode addReview
        restaurantRepository.addReview(newReview);
        //recup liste avis apres l'add
        List<Review> reviews = LiveDataTestUtil.getOrAwaitValue(review);//recup la liste de review via getreview stocké dans reveiws
        //Verification avis top list
        assertNotNull(reviews);//pas nul, init avec succès
        assertFalse(reviews.isEmpty());//verif non empty et donc rajouté avec succès
        Review addedReview = reviews.get(5);//recup review à la premiere position
        //comparaison des resultats
        assertEquals(newReview.getUsername(), addedReview.getUsername());
        assertEquals(newReview.getPicture(), addedReview.getPicture());
        assertEquals(newReview.getComment(), addedReview.getComment());
        assertEquals(newReview.getRate(), addedReview.getRate());
    }
}