package com.bd.tpfinal.DTOs;

public class ProductDTO {
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
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

    public String getNombreSupplier() {
        return nombreSupplier;
    }

    public void setNombreSupplier(String nombreSupplier) {
        this.nombreSupplier = nombreSupplier;
    }
    public ProductTypeDTO getType() {
        return type;
    }

    public void setType(ProductTypeDTO type) {
        this.type = type;
    }
    public ProductDTO() {}

    public ProductDTO(
            String nombreProducto,
            String descripcionProducto,
            float price,
            float weight,
            ProductTypeDTO type,
            String nombreSupplier) {
        this.setNombreProducto(nombreProducto);
        this.setDescripcionProducto(descripcionProducto);
        this.setPrice(price);
        this.setWeight(weight);
        this.setType(type);
        this.setNombreSupplier(nombreSupplier);
    }

    private String nombreProducto;
    private String descripcionProducto;
    private float price;
    private float weight;
    private ProductTypeDTO type;
    private String nombreSupplier;
}
