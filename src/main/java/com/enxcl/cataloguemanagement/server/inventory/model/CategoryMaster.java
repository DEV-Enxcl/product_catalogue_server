package com.enxcl.cataloguemanagement.server.inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "PRDCATCATMST")
@IdClass(CategoryMasterPK.class) 
public class CategoryMaster {
    @Id
    @Column(name = "CATIDR", nullable = false)
    public String categoryID;
    @Id
    @Column(name = "COMPANY", nullable = false)
    public String companyCode;

    @Column(name = "CATDES")
    public String description;
    @Column(name = "IMG")
    public String image;

    @Transient
    public byte[] imageData;
}
