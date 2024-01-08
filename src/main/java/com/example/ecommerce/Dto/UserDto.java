package com.example.ecommerce.Dto;


import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Long userid;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Date dateOfBirth;

    private String password;

    private String confirmPassword;
}
