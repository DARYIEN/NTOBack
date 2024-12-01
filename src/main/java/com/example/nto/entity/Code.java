package com.example.nto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "code")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long value;
}
