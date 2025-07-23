package com.maghrebia.User.profiling.userP;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "user_profiles")
@Data
public class UserProfile {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String profession;
    private double monthlyIncome;
    private double monthlyExpenses;
    private boolean hasCreditHistory;
    // Ajoutez d'autres champs selon vos besoins
}