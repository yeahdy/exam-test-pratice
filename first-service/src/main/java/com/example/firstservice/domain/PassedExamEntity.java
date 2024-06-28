package com.example.firstservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name="PASSED_EXAM")
public class PassedExamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EXAM", nullable = false)
    private String exam;

    @Column(name = "STUDENT_NAME", nullable = false)
    private String studentName;

    @Column(name = "AVG_SCORE", nullable = false)
    private Double avgScore;

}
