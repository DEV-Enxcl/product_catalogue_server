package com.enxcl.cataloguemanagement.server.inventory.model;

import java.util.Objects;

public class CategoryMasterPK implements java.io.Serializable {

    public String categoryID;
    public String companyCode;

    public CategoryMasterPK() {
    }

    public CategoryMasterPK(String categoryID, String companyCode) {
        this.categoryID = categoryID;
        this.companyCode = companyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryMasterPK categoryMasterPK = (CategoryMasterPK) o;
        return Objects.equals(categoryID, categoryMasterPK.categoryID) && Objects.equals(companyCode, categoryMasterPK.companyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID, companyCode);
    }
}
