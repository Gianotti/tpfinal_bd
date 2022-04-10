package com.bd.tpfinal.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends PersistentEntity {

    private String name;

    private float price;

    private float weight;

    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private ProductType type;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistoricalProductPrice> prices = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<HistoricalProductPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<HistoricalProductPrice> prices) {
        this.prices = prices;
    }
}
