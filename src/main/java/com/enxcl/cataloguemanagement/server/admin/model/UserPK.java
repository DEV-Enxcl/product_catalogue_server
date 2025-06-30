package com.enxcl.cataloguemanagement.server.admin.model;

import java.util.Objects;

public class UserPK implements java.io.Serializable {

    public String userID;
    public String companyCode;

    public UserPK() {
    }

    public UserPK(String userID, String companyCode) {
        this.userID = userID;
        this.companyCode = companyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPK userPK = (UserPK) o;
        return Objects.equals(userID, userPK.userID) && Objects.equals(companyCode, userPK.companyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, companyCode);
    }
}
