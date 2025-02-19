/*
package com.images.system.entity;

import com.images.system.authentication.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String imagePath;

    // Correctly reference the User entity instead of Long for userId
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    // Constructors
    public Image() {
    }

    public Image(String imagePath, AppUser user) {
        this.imagePath = imagePath;
        this.user = user;
    }
}
*/
