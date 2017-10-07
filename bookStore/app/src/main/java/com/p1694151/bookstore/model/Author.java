package com.p1694151.bookstore.model;

/**
 * Created by paalwinder on 07/10/17.
 */

public class Author {
    private String name;
    private String image;

    public Author(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
