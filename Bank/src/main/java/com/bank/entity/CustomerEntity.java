package com.bank.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerID;

    @Column(name = "ic_number", unique = true, nullable = false, length = 50)
    private String icNumber;

    @Column(name = "lastname", nullable = false, length = 200)
    private String lastname;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "creation_date", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;

    // @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> accountEntities = new ArrayList<>();
    
    // Set creationDate to current time if it's null before persisting
    @PrePersist
    protected void onCreate() {
        if (this.creationDate == null) {
            this.creationDate = LocalDateTime.now();
        }
    }
}