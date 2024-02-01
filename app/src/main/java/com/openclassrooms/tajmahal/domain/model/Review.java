package com.openclassrooms.tajmahal.domain.model;

import java.util.Objects;


/**
 * Représente un avis d'utilisateur.
 * Cette classe encapsule tous les détails d'un avis, y compris le nom d'utilisateur de la critique,
 * sa photo de profil, le commentaire qu'il a laissé, et la note qu'il a donné.
 */

public class Review {

    /** Le nom de l'utilisateur qui a laissé l'avis. */
    private String username;

    /** La photo de profil de l'utilisateur qui a laissé l'avis. */
    private String picture;

    /** Le commentaire ou retour d'information donné par l'utilisateur. */
    private String comment;

    /** La note fournie par l'utilisateur. Généralement sur 5 ou 10. */
    private int rate;

    /**
     * Construit une nouvelle instance de Review.
     *
     * @param username le nom de l'utilisateur qui laisse l'avis
     * @param picture  l'URL ou le chemin de la photo de profil de l'utilisateur
     * @param comment  le retour ou le commentaire de l'utilisateur
     * @param rate     la note donnée par l'utilisateur
     */
    public Review(String username, String picture, String comment, int rate) {
        this.username = username;
        this.picture = picture;
        this.comment = comment;
        this.rate = rate;
    }

    /**
     * Retourne le nom d'utilisateur de la critique.
     *
     * @return une chaîne de caractères représentant le nom d'utilisateur
     */
    public String getUsername() {
        return username;
    }

    /**
     * Défini ou met à jour le nom d'utilisateur de la critique.
     *
     * @param username le nouveau nom d'utilisateur à définir
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Retourne la photo de profil du critique.
     *
     * @return une chaîne de caractères représentant l'URL ou le chemin de la photo
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Défini ou met à jour la photo de profil du critique.
     *
     * @param picture la nouvelle URL ou le chemin de la photo de profil à définir
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Retourne le commentaire laissé par le critique.
     *
     * @return une chaîne de caractères contenant le retour ou le commentaire
     */
    public String getComment() {
        return comment;
    }

    /**
     * Défini ou met à jour le commentaire laissé par le critique.
     *
     * @param comment le nouveau commentaire ou retour à définir
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Retourne la note donnée par le critique.
     *
     * @return un entier représentant la valeur de la note
     */
    public int getRate() {
        return rate;
    }

    /**
     * Défini ou met à jour la note donnée par le critique.
     *
     * @param rate la nouvelle valeur de la note à définir
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * Compare cette critique avec un autre objet pour vérifier l'égalité.
     * Deux critiques sont considérées comme égales si tous leurs champs sont identiques.
     *
     * @param o l'objet à comparer
     * @return vrai si les objets sont égaux, faux sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rate == review.rate && Objects.equals(username, review.username) && Objects.equals(picture, review.picture) && Objects.equals(comment, review.comment);
    }

    /**
     * Génère un code de hachage pour cette critique basé sur ses champs.
     *
     * @return le code de hachage généré
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, picture, comment, rate);
    }
}
