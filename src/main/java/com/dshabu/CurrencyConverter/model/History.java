package com.dshabu.CurrencyConverter.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "from_amt")
    public double fromAmt;

    @Column(name = "to_amt")
    public double toAmt;

    @Column(name = "from_currency")
    public String fromCurrency;

    @Column(name = "to_currency")
    public String toCurrency;

    @Column(name = "inserted")
    @CreationTimestamp
    public Date inserted;

    public History() {
    }

    public int getId() {
        return id;
    }

    public double getFromAmt() {
        return fromAmt;
    }

    public void setFromAmt(double fromAmt) {
        this.fromAmt = fromAmt;
    }

    public double getToAmt() {
        return toAmt;
    }

    public void setToAmt(double toAmt) {
        this.toAmt = toAmt;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Date getInserted() {
        return inserted;
    }

    public void setInserted(Date inserted) {
        this.inserted = inserted;
    }
}
