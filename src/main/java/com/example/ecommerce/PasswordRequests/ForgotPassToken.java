package com.example.ecommerce.PasswordRequests;

import com.example.ecommerce.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ForgotPassToken {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userid")
    private UserEntity user;
}
