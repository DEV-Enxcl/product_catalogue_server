package com.enxcl.cataloguemanagement.server.admin.model;

import java.util.Objects;

public class MenuPK implements java.io.Serializable {

    public String menuID;
    public String companyCode;

    public MenuPK() {
    }

    public MenuPK(String menuID, String companyCode) {
        this.menuID = menuID;
        this.companyCode = companyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuPK menuPK = (MenuPK) o;
        return Objects.equals(menuID, menuPK.menuID) && Objects.equals(companyCode, menuPK.companyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuID, companyCode);
    }
}
