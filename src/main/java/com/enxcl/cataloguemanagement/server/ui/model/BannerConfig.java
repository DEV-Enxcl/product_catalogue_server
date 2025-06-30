package com.enxcl.cataloguemanagement.server.ui.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "PRDCATBNRCFG")
@IdClass(BannerConfigPK.class) 
public class BannerConfig {
    @Id
    @Column(name = "BNRIDR", nullable = false)
    public String bannerID;
    @Id
    @Column(name = "COMPANY", nullable = false)
    public String companyCode;

    @Column(name = "PRITXT")
    public String primaryText;
    @Column(name = "SECTXT")
    public String secondaryText;
    @Column(name = "PRIFNTSIZ")
    public String primaryFontSize;
    @Column(name = "SECFNDSIZ")
    public String secondaryFontSize;
    @Column(name = "PRIFNDCLR")
    public String primaryFontColor;
    @Column(name = "SECFNDCLR")
    public String secondaryFontColor;
    @Column(name = "VLDFRM")
    public Date validFrom;
    @Column(name = "VLDTOO")
    public Date validTo;
    @Column(name = "ACT")
    public String action;
    @Column(name = "IMGURL")
    public String imageURL;
    @Transient
    public byte[] imageData;
    @Column(name = "LSTODR")
    public int listOrder;
}
