package com.enxcl.cataloguemanagement.server.inventory.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.enxcl.cataloguemanagement.server.inventory.model.InventoryMaster;

public class FileCreationUtil {
    public static String saveCategoryImage(byte[] imageData, String categoryId) {
        if (imageData != null && imageData.length > 0) {
            String extension = ".jpg"; // Default, can be improved to detect type
            String filename = categoryId + "_" + UUID.randomUUID() + extension;
            String relativePath = "/images/" + filename;

            String[] targetDirs = {
                "/Users/sandeepsnair/Sandeep/work/enxcl/catalogue-app/product-catalogue-office/public/images",
                "/Users/sandeepsnair/Sandeep/work/enxcl/catalogue-app/product-catalogue/public/images"
            };
            boolean saved = false;
            for (String dir : targetDirs) {
                try {
                    File directory = new File(dir);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    File outFile = new File(directory, filename);
                    try (FileOutputStream fos = new FileOutputStream(outFile)) {
                        fos.write(imageData);
                        saved = true;
                    }
                } catch (IOException e) {
                    System.err.println("Failed to save image to " + dir + ": " + e.getMessage());
                }
            }
            if (saved) {
                return relativePath; // Only the filename, to be set in the 'image' field
            } else {
                throw new RuntimeException("Failed to save image to all target directories.");
            }
        }
        return null;
    }
    public static InventoryMaster saveInventoryImage(byte[] imageData, byte[][] additionalImageData, InventoryMaster inventoryMaster) {
        String[] targetDirs = {
            "/Users/sandeepsnair/Sandeep/work/enxcl/catalogue-app/product-catalogue-office/public/images/inventory",
            "/Users/sandeepsnair/Sandeep/work/enxcl/catalogue-app/product-catalogue/public/images/inventory"
        };
        // Save primary image
        if (imageData != null && imageData.length > 0) {
            String extension = ".jpg";
            String filename = inventoryMaster.sku + "_" + UUID.randomUUID() + extension;
            String relativePath = "/images/inventory/" + filename;
            boolean saved = false;
            for (String dir : targetDirs) {
                try {
                    File directory = new File(dir);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    File outFile = new File(directory, filename);
                    try (FileOutputStream fos = new FileOutputStream(outFile)) {
                        fos.write(imageData);
                        saved = true;
                    }
                } catch (IOException e) {
                    System.err.println("Failed to save image to " + dir + ": " + e.getMessage());
                }
            }
            if (saved) {
                inventoryMaster.image = relativePath;
            } else {
                throw new RuntimeException("Failed to save primary image to all target directories.");
            }
        }
        // Save additional images
        if (additionalImageData != null && additionalImageData.length > 0) {
            StringBuilder additionalImagesBuilder = new StringBuilder();
            for (int i = 0; i < additionalImageData.length; i++) {
                byte[] addImg = additionalImageData[i];
                if (addImg != null && addImg.length > 0) {
                    String extension = ".jpg";
                    String filename = inventoryMaster.sku + "_add_" + i + "_" + UUID.randomUUID() + extension;
                    String relativePath = "/images/inventory/" + filename;
                    boolean saved = false;
                    for (String dir : targetDirs) {
                        try {
                            File directory = new File(dir);
                            if (!directory.exists()) {
                                directory.mkdirs();
                            }
                            File outFile = new File(directory, filename);
                            try (FileOutputStream fos = new FileOutputStream(outFile)) {
                                fos.write(addImg);
                                saved = true;
                            }
                        } catch (IOException e) {
                            System.err.println("Failed to save additional image to " + dir + ": " + e.getMessage());
                        }
                    }
                    if (saved) {
                        if (additionalImagesBuilder.length() > 0) additionalImagesBuilder.append(",");
                        additionalImagesBuilder.append(relativePath);
                    } else {
                        System.err.println("Failed to save additional image " + i + " to all target directories.");
                    }
                }
            }
            inventoryMaster.additionalImages = additionalImagesBuilder.toString();
        }
        return inventoryMaster;
    }
}
