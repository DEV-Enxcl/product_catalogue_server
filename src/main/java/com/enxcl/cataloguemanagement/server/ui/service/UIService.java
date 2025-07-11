package com.enxcl.cataloguemanagement.server.ui.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enxcl.cataloguemanagement.server.ui.model.BannerConfig;
import com.enxcl.cataloguemanagement.server.ui.model.BannerConfigPK;
import com.enxcl.cataloguemanagement.server.ui.repository.BannerConfigRepository;
import com.enxcl.cataloguemanagement.server.ui.util.S3BasedBanner;

@Service
public class UIService {
    @Autowired
    private BannerConfigRepository bannerConfigRepository;

    public List<BannerConfig> findAllBannerConfig(String company) {
        return bannerConfigRepository.findAllValidBannerConfigs(company, new Date());
    }


    public BannerConfig saveBannerConfig(BannerConfig bannerConfig) {
        if (bannerConfig.imageData != null && bannerConfig.imageData.length > 0) {
            bannerConfig.companyCode = "EN";
            //String relativePath = FileBasedBanner.saveBannerImage(bannerConfig);
            String relativePath = S3BasedBanner.saveBannerImage(bannerConfig);
            bannerConfig.imageURL = relativePath;
            bannerConfig.imageData = null; // Clear transient data
        }
        return bannerConfigRepository.save(bannerConfig);
    }
    //implment delete banner config
    public void deleteBannerConfig(String company, String bannerId) {
        BannerConfig bannerConfig = bannerConfigRepository.findById(new BannerConfigPK(bannerId,company)).orElse(null);
        if (bannerConfig != null) {
            bannerConfigRepository.delete(bannerConfig);
        }
    }
}
