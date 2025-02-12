package com.nikirocks.finance;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // this is unique
    @Column(unique = true)
    private String name;
    @Column
    private String description;

}
