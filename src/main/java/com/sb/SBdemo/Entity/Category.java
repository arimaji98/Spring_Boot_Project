package com.sb.SBdemo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CATEGORY_TABLE")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private int catId;

    @Column(name = "CATEGORY_TITLE")
    private String catTitle;

    @Column(name = "CATEGORY_DESCRIPTION")
    private String catDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> post = new ArrayList<>();
}
