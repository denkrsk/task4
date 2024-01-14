package ru.stepup.task.model;

import jakarta.persistence.*;
import lombok.*;
import ru.stepup.task.repository.LoginsRepository;

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

    @Column(name="access_date")
    private Timestamp accessDate;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Users user;

    @Column(name="application")
    private String application;

    public void setApplication(String application) {
        switch (application) {
            case "WEB":
                application = "web";
                break;

            case "mobile":
                application = "mobile";
                break;

            default:
                application = "other";
                break;
        }
        this.application = application;
    }
}

