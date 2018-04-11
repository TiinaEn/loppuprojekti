package fi.academy.loppuprojekti_rest.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Destination {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String category;
    @NotNull
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    @NotNull
    private String country;
    @NotNull
    private String city;
    private Timestamp dateAndTime = new Timestamp(new Date().getTime());
    private String subcategory;
    @Lob
    private String description;
    private Integer rating;
    private Integer priceRange;
    private Boolean breakfastIncluded;
    private String weblink;
    @ManyToOne
    @JoinColumn (name = "user")
    private User user;


    public Destination() {
    }

    public Destination(@NotNull String category, @NotNull String name, @NotNull String country, @NotNull String city, String description, User user) {
        this.category = category;
        this.name = name;
        this.country = country;
        this.city = city;
        this.description = description;
        this.user = user;
    }

    public Destination(@NotNull String category, @NotNull String name, String address, @NotNull String country, @NotNull String city, String description, Integer rating, String weblink, User user) {
        this.category = category;
        this.name = name;
        this.address = address;
        this.country = country;
        this.city = city;
        this.description = description;
        this.rating = rating;
        this.weblink = weblink;
        this.user = user;
    }

    public Destination(@NotNull String category, @NotNull String name, String address, double latitude, double longitude, @NotNull String country, @NotNull String city, String description, Integer rating, String weblink, User user) {
        this.category = category;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.description = description;
        this.rating = rating;
        this.weblink = weblink;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(Integer priceRange) {
        this.priceRange = priceRange;
    }

    public Boolean getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(Boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public String getWeblink() { return weblink; }

    public void setWeblink(String weblink) { this.weblink = weblink; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
