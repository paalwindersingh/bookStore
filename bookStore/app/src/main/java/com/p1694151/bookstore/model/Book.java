package com.p1694151.bookstore.model;

/**
 * Created by paalwinder on 07/10/17.
 */

public class Book {

    private int id;
    private int author_id;
    private String category;
    private String cover;
    private String date;
    private String publisher;
    private int quantity;
    private boolean shipToCanada;
    private boolean shipToUSA;
    private String title;

    public Book(int id, int author_id, String category, String cover, String date, String publisher, int quantity, boolean shipToCanada, boolean shipToUSA, String title) {
        this.id = id;
        this.author_id = author_id;
        this.category = category;
        this.cover = cover;
        this.date = date;
        this.publisher = publisher;
        this.quantity = quantity;
        this.shipToCanada = shipToCanada;
        this.shipToUSA = shipToUSA;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isShipToCanada() {
        return shipToCanada;
    }

    public void setShipToCanada(boolean shipToCanada) {
        this.shipToCanada = shipToCanada;
    }

    public boolean isShipToUSA() {
        return shipToUSA;
    }

    public void setShipToUSA(boolean shipToUSA) {
        this.shipToUSA = shipToUSA;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
