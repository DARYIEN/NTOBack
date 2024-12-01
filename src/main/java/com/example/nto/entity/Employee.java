package com.example.nto.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")


@JsonPropertyOrder({"id", "login", "name", "photo", "position", "lastVisit"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Column(nullable = false, unique = true)
    private String login;

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String photo;

    @Getter
    @Column(nullable = false)
    private String position;

    @Getter
    @Column(name = "last_visit", nullable = false)
    private LocalDateTime lastVisit;

    public Long getId() { return id; }

}
