package com.openclassrooms.tajmahal.ui.restaurant;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.ui.adapter.ReviewAdapter;

import java.util.Collection;
import java.util.Collections;

public class ReviewFragment extends Fragment {

    private ReviewAdapter reviewAdapter;
    private RecyclerView reviewsRecyclerView;
    private DetailsViewModel detailsViewModel;
    private RatingBar ratingBar;
    private EditText editTextComment;
    private Button submitButton;

    public ReviewFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout pour ce fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        submitButton = view.findViewById(R.id.submitButton);
        submitButton.setEnabled(false);
        editTextComment = view.findViewById(R.id.editTextReview);
        ratingBar = view.findViewById(R.id.ratingBar);
        initRecyclerView(view);
        setupBackButton(view);
        setupSubmitButton();

        editTextComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                checkFieldsForEmptyValues();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                checkFieldsForEmptyValues();
            }
        });

        ImageView imageView = view.findViewById(R.id.imageView); //ajout de l'image de profil sur url avec glide + effet Circle
        Glide.with(this)
                .load("https://xsgames.co/randomusers/assets/avatars/female/3.jpg")
                .circleCrop()
                .into(imageView);
        return view;
    }
    private void checkFieldsForEmptyValues() {
        String comment = editTextComment.getText().toString();
        float rating = ratingBar.getRating();

        if (!comment.isEmpty() && rating > 0) {
            submitButton.setEnabled(true);
            submitButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        } else {
            submitButton.setEnabled(false);
            submitButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey)));
        }
    }

    private void setupSubmitButton() {
        submitButton.setOnClickListener(view -> {
            String comment = editTextComment.getText().toString();
            int rating = (int) ratingBar.getRating();
            Review newReview = new Review("Manon Garcia","https://xsgames.co/randomusers/assets/avatars/female/3.jpg",comment,rating);//amelioration possible
            detailsViewModel.addReview(newReview);
            /*refreshReviews(); //rafraichi la page des review */
            hideKeyboard(view); //abaisse le clavier apres ajout d'un avis
            editTextComment.setText("");//clean le texte
            editTextComment.setHint("Merci pour votre commentaire!"); //change le hint apres clique validate
        });
    }

    private void hideKeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && getActivity().getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }
    private void refreshReviews() {
        detailsViewModel.getReviews().observe(getViewLifecycleOwner(),reviews -> {
            reviewAdapter.setReviews(reviews);
            reviewAdapter.notifyDataSetChanged(); //notification de adaptetaeur du changement
        });
    }

    private void setupBackButton(View view) {
        ImageView backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            //logique retour arriere
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initialisation du ViewModel
        detailsViewModel = new ViewModelProvider(requireActivity()).get(DetailsViewModel.class);
        observeReviews();
    }

    private void initRecyclerView(View view) {
        //initialisation du RecyclerView
        reviewsRecyclerView = view.findViewById(R.id.reviewsRecyclerView);
        //inversion de l'affichage du linearlayoutmanager
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);//commencer l'affichage par la fin de la liste, correction du besoin de scroll
        reviewsRecyclerView.setLayoutManager(layoutManager);
        reviewAdapter = new ReviewAdapter();
        reviewsRecyclerView.setAdapter(reviewAdapter);
    }

    private void observeReviews() {
        detailsViewModel.getReviews().observe(getViewLifecycleOwner(), reviews -> {
            //maj de l'adaptateur avec les nouvelles données
            reviewAdapter.setReviews(reviews);
        });

    }
}
