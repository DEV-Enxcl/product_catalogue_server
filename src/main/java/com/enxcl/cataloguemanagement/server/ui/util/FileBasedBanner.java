package com.enxcl.cataloguemanagement.server.ui.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.enxcl.cataloguemanagement.server.ui.model.BannerConfig;

public class FileBasedBanner {
    public static String saveBannerImage(BannerConfig bannerConfig) {
        if (bannerConfig.imageData != null && bannerConfig.imageData.length > 0) {
            String extension = ".jpg"; // Default, you may want to detect from content-type
            String filename = bannerConfig.bannerID + "_" + UUID.randomUUID() + extension;
            String relativePath = "/images/" + filename;

            String[] targetDirs = {
                "/Users/sandeepsnair/Sandeep/work/enxcl/catalogue-app/product-catalogue-office/public/images",
                "/Users/sandeepsnair/Sandeep/work/enxcl/catalogue-app/product-catalogue/public/images"
            };
            for (String dir : targetDirs) {
                try {
                    File outFile = new File(dir, filename);
                    try (FileOutputStream fos = new FileOutputStream(outFile)) {
                        fos.write(bannerConfig.imageData);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Failed to save image to " + dir, e);
                }
            }
            return relativePath;
        }
        return null;
    }
}