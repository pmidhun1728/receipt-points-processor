package com.receipt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="receipt")

public class ReceiptEntity {
    @Id
    @Column(name ="id")
    private String id;

    @Column(name ="points")
    private int points;

    public ReceiptEntity(String id, int points) {

        this.id = id;
        this.points =points;
    }

    public ReceiptEntity() {
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
