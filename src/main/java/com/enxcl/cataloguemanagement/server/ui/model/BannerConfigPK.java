package com.enxcl.cataloguemanagement.server.ui.model;

import java.util.Objects;

public class BannerConfigPK implements java.io.Serializable {

    public String bannerID;
    public String companyCode;

    public BannerConfigPK() {
    }

    public BannerConfigPK(String bannerID, String companyCode) {
        this.bannerID = bannerID;
        this.companyCode = companyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BannerConfigPK bannerConfigPK = (BannerConfigPK) o;
        return Objects.equals(bannerID, bannerConfigPK.bannerID) && Objects.equals(companyCode, bannerConfigPK.companyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bannerID, companyCode);
    }
}
