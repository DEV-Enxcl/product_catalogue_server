package com.enxcl.cataloguemanagement.server.admin.model;

import java.util.Objects;

public class ParameterPK implements java.io.Serializable {

    public String parameterCode;
    public String companyCode;

    public ParameterPK() {
    }

    public ParameterPK(String parameterCode, String companyCode) {
        this.parameterCode = parameterCode;
        this.companyCode = companyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterPK parameterPK = (ParameterPK) o;
        return Objects.equals(parameterCode, parameterPK.parameterCode) && Objects.equals(companyCode, parameterPK.companyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterCode, companyCode);
    }
}
