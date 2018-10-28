package com.samuel.house.manager.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue
    @Column(name="h_id")
    private Long id;

    @Column(name="h_street")
    private String streetAddress;
    @Column(name="h_city")
    private String city;
    @Column(name="h_postcode")
    private String postcode;
    @Column(name="h_country")
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(id, house.id) &&
                Objects.equals(streetAddress, house.streetAddress) ;//&&
//                Objects.equals(number, house.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetAddress);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
