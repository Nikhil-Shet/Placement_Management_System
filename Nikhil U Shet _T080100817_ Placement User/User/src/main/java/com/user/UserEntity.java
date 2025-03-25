package com.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Optional: Customize table name
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Or other generation strategy
    private Integer id;

    private String name;
    private float price;
    private String email;

    // No-args constructor (REQUIRED by JPA)
    public UserEntity() {}

    // Constructor with all fields (recommended)
    public UserEntity(String name, float price, String email) {
        this.name = name;
        this.price = price;
        this.email = email;
    }

    // Getters and Setters (Essential for JPA and data binding)
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", email='" + email + '\'' +
                '}';
    }
}