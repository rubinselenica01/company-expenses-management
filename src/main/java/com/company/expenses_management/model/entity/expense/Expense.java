package com.company.expenses_management.model.entity.expense;

import com.company.expenses_management.model.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import static com.company.expenses_management.utils.SQLConstants.*;

@Builder
@Entity
@Table(name = EXPENSE_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = EXPENSE_ID_PROPERTY)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = EXPENSE_EMPLOYEE_ID_PROPERTY, nullable = false)
    private User employee;

    @Column(name = EXPENSE_DESCRIPTION_PROPERTY)
    private String expenseDescription;

    @Column(name = AMOUNT_TO_REFUND_PROPERTY)
    private Double amountToRefund;

    @Column(name = REFUNDED_STATUS_PROPERTY)
    private Boolean refunded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = REFUNDED_BY_PROPERTY)
    private User refundedBy;

}
