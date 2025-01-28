package com.company.expenses_management.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

import static com.company.expenses_management.utils.SQLConstants.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = REPORT_TABLE_NAME)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID_PROPERTY)
    private UUID id;

    @Column(name = START_DATE_PROPERTY)
    private LocalDate startDate;

    @Column(name = END_DATE_PROPERTY)
    private LocalDate endDate;

    @Column(name = TOTAL_AMOUNT_REFUNDED)
    private Double totalAmountRefunded;
}
