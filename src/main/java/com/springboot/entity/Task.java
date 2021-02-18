package com.springboot.entity;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String taskDescription;
    @ManyToOne
    private Project project;
    @OneToOne
    private User user;

}
