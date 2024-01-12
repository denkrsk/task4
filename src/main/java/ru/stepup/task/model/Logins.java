package ru.stepup.task.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="app_logins")
public class Logins {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @CreationTimestamp
    @Column(name="access_date")
    private Timestamp accessDate;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Users user;

    @Column(name="application")
    private String application;
}
