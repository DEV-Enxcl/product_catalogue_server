package com.enxcl.cataloguemanagement.server.ui.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enxcl.cataloguemanagement.server.ui.model.BannerConfig;
import com.enxcl.cataloguemanagement.server.ui.model.BannerConfigPK;

@Repository
public interface BannerConfigRepository extends JpaRepository<BannerConfig, BannerConfigPK> {
    
    @Query("SELECT b FROM BannerConfig b WHERE b.companyCode = :companyCode AND :currentDate BETWEEN b.validFrom AND b.validTo ORDER By b.listOrder")
    List<BannerConfig> findAllValidBannerConfigs(@Param("companyCode") String companyCode, @Param("currentDate") Date currentDate);
    
}
