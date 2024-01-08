package com.example.ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Topro_users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userid;

    @NotBlank(message = "Entry cannot be blank")
    @Column(name = "first_Name")
    private String firstName;

    @NotBlank(message = "Entry cannot be blank")
    @Column(name = "middle_Name")
    private String middleName;

    @NotBlank(message = "Entry cannot be blank")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Input a valid email address")
    @Column(name = "email")
    private String email;


    @Column(name = "DOB")
    private Date dateOfBirth;

    @NotBlank(message = "Entry cannot be blank")
    @Column(name = "phone_Number")
    private String phoneNumber;

    @JsonIgnore
    @NotBlank(message = "Password required")
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @NotBlank(message = "Password required")
    @Column(name = "confirm_Password")
    private String confirmPassword;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
