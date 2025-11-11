package net.javaguides.banking_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//CONFIGURE TABLE DETAILS BY @TABLE ANNOTATION
@Table(name="accounts")
//MAKE CLASS AS JPA ENTITY USING @ENTITY ANNOTATION
@Entity
public class Account {
    //MAKE ID AS PRIMARY KEY AS ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;
    private  double balance;
}
