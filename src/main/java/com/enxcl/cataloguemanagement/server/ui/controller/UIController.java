package com.enxcl.cataloguemanagement.server.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enxcl.cataloguemanagement.server.ui.model.BannerConfig;
import com.enxcl.cataloguemanagement.server.ui.service.UIService;

@RestController
@RequestMapping("/productcatalogue/api/ui")
public class UIController {

    @Autowired
    private UIService uiService;

    @GetMapping("/{company}/findAllValidBanners")
    public ResponseEntity<List<BannerConfig>> findAllValidBanners(@PathVariable String company) {
        List<BannerConfig> banners = uiService.findAllBannerConfig(company);
        return banners != null ? ResponseEntity.ok(banners) : ResponseEntity.notFound().build();
    }

    @PostMapping(
        value = "/saveBannerConfig",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<BannerConfig> saveBannerConfig(
            @RequestPart("banner") BannerConfig bannerConfig,
            @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                bannerConfig.imageData = imageFile.getBytes();
            }
            BannerConfig saved = uiService.saveBannerConfig(bannerConfig);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{company}/deleteBannerConfig/{bannerId}")
    public ResponseEntity<Void> deleteBannerConfig(@PathVariable String company, @PathVariable String bannerId) {
        try {
            uiService.deleteBannerConfig(company, bannerId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); 
        }
    }
}
