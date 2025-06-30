package com.enxcl.cataloguemanagement.server.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "RWDUSRMST")
@IdClass(UserPK.class) 
public class User {

    
    @Id
    @Column(name = "USRIDR", nullable = false)
    public String userID;

    @Id
    @Column(name = "COMPANY", nullable = false)
    public String companyCode;

    @Column(name = "USRNAM")
    public String userName;

    @Column(name = "USRPWD")
    public String password;

    @Column(name = "ROLGRP")
    public String role;

    @Column(name = "JOBLVL")
    public String joblevel;

    @Column(name = "EML")
    public String email;

    @Column(name = "PHN")
    public String phone;

    @Column(name = "KCKIDR")
    public String keyclockID;
}