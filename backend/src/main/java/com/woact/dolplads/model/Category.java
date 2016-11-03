package com.woact.dolplads.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by dolplads on 26/10/2016.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private CategoryEnum categoryEnum;

    @OneToOne
    private Category subCategory;

    private int level;

    public Category(CategoryEnum categoryEnum, Category subCategory) {
        this.categoryEnum = categoryEnum;
        this.subCategory = subCategory;

        if (subCategory != null) {
            int l = subCategory.getLevel();
            level = l + 1;
        }
    }
}
