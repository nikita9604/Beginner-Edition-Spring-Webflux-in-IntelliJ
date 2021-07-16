package com.nikita.springwebexample8.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private int id;
    private String firstname;
    private String lastname;
    private String email;
}
