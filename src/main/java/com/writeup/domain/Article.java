package com.writeup.domain;


import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private long id;

    @Column(name = "TITTLE", nullable = false)
    private String tittle;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "body", nullable = false)
    private String body;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Article(){

    }

    public Article(String tittle, String description , String body) {
        this.tittle = tittle;
        this.description = description;
        this.body = body;

    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
