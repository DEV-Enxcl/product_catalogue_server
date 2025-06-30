package com.enxcl.cataloguemanagement.server.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "RWDMNUMST")
@IdClass(MenuPK.class) 
public class Menu {

    @Id
    @Column(name = "MNUCOD", nullable = false)
    public String menuID;

    @Id
    @Column(name = "COMPANY", nullable = false)
    public String companyCode;

    @Column(name = "MNUNAM")
    public String menuName;

    @Column(name = "MNUIDX")
    public int menuIndex;

    @Column(name = "ACT")
    public String action;

    @Column(name = "LOG")
    public String logo;

    @Column(name = "ODRDIS")
    public int orderOfDisplay;

    @Column(name = "PARMNUIDX")
    public int parentMenuIdex;

    @Column(name = "PRVCOD")
    public String privileges;
}