package com.respect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class User {
    @Column
    private String name;

    @Column
    private int age;
}
