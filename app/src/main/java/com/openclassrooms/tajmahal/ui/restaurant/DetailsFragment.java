package com.openclassrooms.tajmahal.ui.restaurant;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.databinding.FragmentDetailsBinding;
import com.openclassrooms.tajmahal.domain.model.Restaurant;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * DetailsFragment est le point d'entrée de l'application et sert d'interface utilisateur principale.
 * Il affiche des détails sur un restaurant et offre des fonctionnalités pour ouvrir son emplacement
 * sur une carte, appeler son numéro de téléphone, ou consulter son site web.
 * <p>
 * Cette classe utilise {@link FragmentDetailsBinding} pour la liaison de données avec sa mise en page et
 * {@link DetailsViewModel} pour interagir avec les sources de données et gérer les données liées à l'interface utilisateur.
 */
@AndroidEntryPoint
public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    private DetailsViewModel detailsViewModel;

    /**
     * Cette méthode est appelée lorsque le fragment est créé pour la première fois.
     * Elle est utilisée pour effectuer une initialisation unique.
     *
     * @param savedInstanceState Un bundle contenant l'état de l'instance précédemment sauvegardé.
     *                           Si le fragment est recréé à partir d'un état sauvegardé précédent, c'est cet état.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Cette méthode est appelée immédiatement après `onCreateView()`.
     * Utilisez cette méthode pour effectuer l'initialisation finale une fois que les vues du fragment ont été inf.
     *
     * @param view               La Vue retournée par `onCreateView()`.
     * @param savedInstanceState Si non nul, ce fragment est en train d'être reconstruit
     *                           à partir d'un état sauvegardé précédent comme indiqué ici.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI(); // Sets up user interface components.
        setupViewModel(); // Prepares the ViewModel for the fragment.
        detailsViewModel.getTajMahalRestaurant().observe(requireActivity(), this::updateUIWithRestaurant); // Observes changes in the restaurant data and updates the UI accordingly.

        detailsViewModel.getReviews().observe(getViewLifecycleOwner(), reviews -> {
            if (reviews != null && !reviews.isEmpty()) {
                float sum = 0;

                int[] noteCount = new int[5]; // Pour les notes de 1 à 5
                for (Review review : reviews) {

                    //calcul de la somme pour la moyenne
                    sum += review.getRate();

                    //incrémentation pour les compteurs de notes
                    if (review.getRate() >= 1 && review.getRate() <= 5) {
                        noteCount[review.getRate() - 1]++;
                    }
                }
                //count et affichage de la moyenne
                float average = sum / reviews.size();
                binding.numberRating.setText(String.format("%.1f", average));
                binding.ratingBar.setRating(average);

                //Maj des ProgressBar
                updateProgressBars(noteCount, reviews.size());

                //Maj du nombre total de reviews
                binding.numberTotalRating.setText("(" + reviews.size() + ")");
                binding.numberTotalRating.setVisibility(View.VISIBLE);
            } else {
                //gestion du cas où il n'y a pas de reviews
                binding.numberRating.setText("ERREUR");
                binding.ratingBar.setRating(0);
                binding.numberTotalRating.setVisibility(View.GONE);
            }

        });

        Button buttonLetRating = view.findViewById(R.id.buttonLetRating);
        buttonLetRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new ReviewFragment())
                        .addToBackStack(null) // Pour permettre à l'utilisateur de revenir au fragment précédent
                        .commit();
            }
        });
    }



    // declaration methode updateProgressBars
    private void updateProgressBars(int[] noteCount, int totalReviews) {
        if (totalReviews > 0); { //si 0 progressBar pas mise à jour.
            // 0 compte 1
            // binding des id
            binding.progressBar4.setProgress((noteCount[4] * 100) / totalReviews); // Pour les notes de 5
            binding.progressBar3.setProgress((noteCount[3] * 100) / totalReviews); // Pour les notes de 4
            binding.progressBar2.setProgress((noteCount[2] * 100) / totalReviews); // Pour les notes de 3
            binding.progressBar5.setProgress((noteCount[1] * 100) / totalReviews); // Pour les notes de 2
            binding.progressBar1.setProgress((noteCount[0] * 100) / totalReviews); // Pour les notes de 1
        }

    }

    /**
     * Crée et retourne la hiérarchie de vue associée au fragment.
     *
     * @param inflater L'objet LayoutInflater qui peut être utilisé pour gonfler toutes les vues dans le fragment.
     * @param container Si non nul, c'est la vue parente à laquelle l'interface utilisateur du fragment doit être attachée.
     * Le fragment ne doit pas ajouter la vue lui-même mais la retourner.
     * @param savedInstanceState Si non nul, ce fragment est en train d'être reconstruit
     * à partir d'un état sauvegardé précédent comme indiqué ici.
     * @return Retourne la Vue pour l'interface utilisateur du fragment, ou null.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false); // Binds the layout using view binding.
        return binding.getRoot(); // Returns the root view.
    }


    /**
     * Configure les propriétés spécifiques à l'interface utilisateur, telles que les drapeaux de l'interface système et la couleur de la barre d'état.
     */
    private void setupUI() {
        Window window = requireActivity().getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * Initialise le ViewModel pour cette activité.
     */
    private void setupViewModel() {
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
    }

    /**
     * Met à jour les composants de l'interface utilisateur avec les données du restaurant fournies.
     *
     * @param restaurant L'objet restaurant contenant les détails à afficher.
     */
    private void updateUIWithRestaurant(Restaurant restaurant) {
        if (restaurant == null) return;

        binding.tvRestaurantName.setText(restaurant.getName());
        binding.tvRestaurantDay.setText(detailsViewModel.getCurrentDay(requireContext()));
        binding.tvRestaurantType.setText(String.format("%s %s", getString(R.string.restaurant), restaurant.getType()));
        binding.tvRestaurantHours.setText(restaurant.getHours());
        binding.tvRestaurantAddress.setText(restaurant.getAddress());
        binding.tvRestaurantWebsite.setText(restaurant.getWebsite());
        binding.tvRestaurantPhoneNumber.setText(restaurant.getPhoneNumber());
        binding.chipOnPremise.setVisibility(restaurant.isDineIn() ? View.VISIBLE : View.GONE);
        binding.chipTakeAway.setVisibility(restaurant.isTakeAway() ? View.VISIBLE : View.GONE);

        binding.buttonAdress.setOnClickListener(v -> openMap(restaurant.getAddress()));
        binding.buttonPhone.setOnClickListener(v -> dialPhoneNumber(restaurant.getPhoneNumber()));
        binding.buttonWebsite.setOnClickListener(v -> openBrowser(restaurant.getWebsite()));
    }

    /**
     * Ouvre l'adresse fournie dans Google Maps ou affiche une erreur si Google Maps
     * n'est pas installé.
     *
     * @param address L'adresse à afficher dans Google Maps.
     */
    private void openMap(String address) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(requireActivity(), R.string.maps_not_installed, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Compose le numéro de téléphone fourni ou affiche une erreur s'il n'y a pas d'application de composition
     * installée.
     *
     * @param phoneNumber Le numéro de téléphone à composer.
     */
    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(requireActivity(), R.string.phone_not_found, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Ouvre l'URL du site web fourni dans un navigateur ou affiche une erreur s'il n'y a pas de
     * navigateur installé.
     *
     * @param websiteUrl L'URL du site web à ouvrir.
     */
    private void openBrowser(String websiteUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(requireActivity(), R.string.no_browser_found, Toast.LENGTH_SHORT).show();
        }
    }

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

}