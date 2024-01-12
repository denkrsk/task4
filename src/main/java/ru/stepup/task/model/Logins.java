package ru.stepup.task.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Logins {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name="access_date")
    private Timestamp access_date;
    @Column(name="user_id")
    private Long user_id;
    @Column(name="application")
    private String application;
}
