package com.example.coffeeshop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    private String userName;
    private String password;
    private String email;
    private String gender;

    // Security & Audit fields requested in Activity 1
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    // Automatically set default values when a user is created
    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDate.now();
        this.modifiedDate = LocalDate.now();
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }

    // Automatically update the modified date when updated
    @PreUpdate
    protected void onUpdate() {
        this.modifiedDate = LocalDate.now();
    }
}

