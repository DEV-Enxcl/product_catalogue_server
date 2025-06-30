package com.enxcl.cataloguemanagement.server.inventory.model;

import java.util.Objects;

public class InventoryMasterPK implements java.io.Serializable {

    public String sku;
    public String companyCode;

    public InventoryMasterPK() {
    }

    public InventoryMasterPK(String sku, String companyCode) {
        this.sku = sku;
        this.companyCode = companyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryMasterPK inventoryMasterPK = (InventoryMasterPK) o;
        return Objects.equals(sku, inventoryMasterPK.sku) && Objects.equals(companyCode, inventoryMasterPK.companyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, companyCode);
    }
}
