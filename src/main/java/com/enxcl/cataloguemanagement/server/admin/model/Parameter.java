package com.enxcl.cataloguemanagement.server.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "RWDPARMST")
@IdClass(ParameterPK.class) 
public class Parameter {

    
    @Id
    @Column(name = "PARCOD", nullable = false)
    public String parameterCode;

    @Id
    @Column(name = "COMPANY", nullable = false)
    public String companyCode;

    @Column(name = "PARVAL")
    public String parameterValue;

    @Column(name = "PARDES")
    public String parameterDescription;

}