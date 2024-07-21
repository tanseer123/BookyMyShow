package com.example.bookmyshow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    private String theatre_name;

    @OneToMany
    private List<Screen> screens;

    @ManyToOne
    private City city;
}
