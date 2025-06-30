package com.enxcl.cataloguemanagement.server.inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "PRDCATINVMST")
@IdClass(InventoryMasterPK.class) 
public class InventoryMaster {
    @Id
    @Column(name = "SKU", nullable = false)
    public String sku;
    @Id
    @Column(name = "COMPANY", nullable = false)
    public String companyCode;

    @Column(name = "INVNAM")
    public String name;
    @Column(name = "INVDES")
    public String description;
    @Column(name = "IMG")
    public String image;
    @Transient
    public byte[] imageData;
    @Column(name = "ADLIMG")
    public String additionalImages;
    @Transient
    public byte[][] additionalImageData;
    @Column(name = "CUSRAT")
    public double customerRating;
    @Column(name = "SELPRC")
    public double sellingPrice;
    @Column(name = "MRP")
    public double mrp;
    @Column(name = "CAT")
    public String category;

    @Column(name = "FEATUR")
    public String features;

    @Column(name = "SPEFIC", columnDefinition = "TEXT")
    public String specifications;
}
