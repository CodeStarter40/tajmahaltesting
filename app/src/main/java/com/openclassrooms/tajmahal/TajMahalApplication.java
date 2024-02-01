package com.openclassrooms.tajmahal;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

/**
 * La classe principale de l'application pour le projet TajMahal.
 * <p>
 * Cette classe est annotée avec {@code @HiltAndroidApp}, la marquant comme le point d'entrée
 * pour l'injection de dépendances Dagger-Hilt. En étendant la classe {@code Application}
 * et en la annotant avec {@code @HiltAndroidApp}, Hilt générera et initialisera automatiquement
 * les composants et modules DI nécessaires pour l'application.
 * </p>
 * <p>
 * Les développeurs doivent être prudents lorsqu'ils ajoutent de la logique à cette classe, car cela affecte
 * l'ensemble du cycle de vie de l'application. Généralement, cette classe devrait uniquement être utilisée pour
 * les configurations et initialisations à l'échelle de l'application.
 * </p>
 *
 * <strong>Note pour les débutants :</strong> Hilt est une bibliothèque d'injection de dépendances pour Android
 * qui réduit le code standard nécessaire pour faire une injection de dépendances manuelle dans votre projet.
 * L'injection de dépendances permet aux objets de recevoir d'autres objets (dépendances)
 * dont ils ont besoin d'une source externe plutôt que de les créer en interne.
 *
 * @see <a href="https://developer.android.com/training/dependency-injection/hilt-android">La documentation officielle de Hilt</a>
 */
@HiltAndroidApp
public class TajMahalApplication extends Application {
}
