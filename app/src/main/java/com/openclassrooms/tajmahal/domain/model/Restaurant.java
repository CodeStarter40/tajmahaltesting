package com.openclassrooms.tajmahal.domain.model;


import java.util.Objects;

/**
 * Représente un restaurant et ses divers attributs.
 * <p>
 * Cette classe modélise un restaurant avec son nom, son type (par exemple, indien, italien), ses heures d'ouverture,
 * adresse, site web, numéro de téléphone, et la disponibilité des options de restauration sur place et à emporter.
 * </p>
 * <p>
 * Pour les débutants : Une classe est un plan pour créer des objets en Java. Les objets sont des instances d'une classe.
 * Dans ce cas, la classe 'Restaurant' nous permet de représenter différents restaurants avec leurs détails spécifiques.
 * <p>
 * Exemple :
 * <pre>
 * Restaurant tajMahal = new Restaurant("Taj Mahal", "Indien", "11h30 - 22h00",
 *                                      "123 Street", "http://tajmahal.com", "1234567890", vrai, faux);
 * </pre>
 */

public class Restaurant {

    // Member variables representing attributes of a restaurant.
    private String name;
    private String type;
    private String hours;
    private String address;
    private String website;
    private String phoneNumber;
    private boolean dineIn;
    private boolean takeAway;

    /**
     * Constructeur pour la classe Restaurant.
     *
     * @param name        Le nom du restaurant.
     * @param type        Le type ou la cuisine du restaurant (par exemple, indien, italien).
     * @param hours       Les heures d'ouverture du restaurant.
     * @param address     L'adresse du restaurant.
     * @param website     L'URL du site web du restaurant.
     * @param phoneNumber Le numéro de téléphone de contact du restaurant.
     * @param dineIn      Un booléen indiquant si la restauration sur place est disponible.
     * @param takeAway    Un booléen indiquant si le service à emporter est disponible.
     */

    public Restaurant(String name, String type, String hours, String address, String website, String phoneNumber, boolean dineIn, boolean takeAway) {
        this.name = name;
        this.type = type;
        this.hours = hours;
        this.address = address;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.dineIn = dineIn;
        this.takeAway = takeAway;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return dineIn == that.dineIn && takeAway == that.takeAway && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(hours, that.hours) && Objects.equals(address, that.address) && Objects.equals(website, that.website) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, hours, address, website, phoneNumber, dineIn, takeAway);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDineIn() {
        return dineIn;
    }

    public void setDineIn(boolean dineIn) {
        this.dineIn = dineIn;
    }

    public boolean isTakeAway() {
        return takeAway;
    }

    public void setTakeAway(boolean takeAway) {
        this.takeAway = takeAway;
    }


}
