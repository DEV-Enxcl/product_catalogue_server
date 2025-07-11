package com.enxcl.cataloguemanagement.server.inventory.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import com.enxcl.cataloguemanagement.server.inventory.model.InventoryMaster;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3CreationUtil {
    private static final String BUCKET_NAME = "test-bucket-for-hospitalapp-productcat"; // TODO: set your bucket name
    private static final Region REGION = Region.US_EAST_1; // TODO: set your region
    private static final S3Client s3 = S3Client.builder()
            .region(REGION)
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create("AKIATCKATC4X72Y6O6DL", "1Gu9aPKu4VFhWTRxn5p8D459P4x/SY2r6TZe4cdl")
        )
    )
            .build();

    public static String saveCategoryImage(byte[] imageData, String categoryId) {
        if (imageData != null && imageData.length > 0) {
            String extension = ".jpg";
            String filename = categoryId + "_" + UUID.randomUUID() + extension;
            String key = "images/" + filename;
            try {
                Path tempFile = Files.createTempFile("catimg", extension);
                Files.write(tempFile, imageData, StandardOpenOption.WRITE);
                PutObjectRequest putReq = PutObjectRequest.builder()
                        .bucket(BUCKET_NAME)
                        .key(key)
                        .build();
                s3.putObject(putReq, tempFile);
                Files.deleteIfExists(tempFile);
                return getPublicUrl(key);
            } catch (Exception e) {
                throw new RuntimeException("Failed to upload category image to S3: " + e.getMessage(), e);
            }
        }
        return null;
    }

    public static InventoryMaster saveInventoryImage(byte[] imageData, byte[][] additionalImageData, InventoryMaster inventoryMaster) {
        // Save primary image
        if (imageData != null && imageData.length > 0) {
            String extension = ".jpg";
            String filename = inventoryMaster.sku + "_" + UUID.randomUUID() + extension;
            String key = "images/inventory/" + filename;
            try {
                Path tempFile = Files.createTempFile("invimg", extension);
                Files.write(tempFile, imageData, StandardOpenOption.WRITE);
                PutObjectRequest putReq = PutObjectRequest.builder()
                        .bucket(BUCKET_NAME)
                        .key(key)
                        .build();
                s3.putObject(putReq, tempFile);
                Files.deleteIfExists(tempFile);
                inventoryMaster.image = getPublicUrl(key);
            } catch (Exception e) {
                throw new RuntimeException("Failed to upload inventory image to S3: " + e.getMessage(), e);
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
                    String key = "images/inventory/" + filename;
                    try {
                        Path tempFile = Files.createTempFile("invaddimg", extension);
                        Files.write(tempFile, addImg, StandardOpenOption.WRITE);
                        PutObjectRequest putReq = PutObjectRequest.builder()
                                .bucket(BUCKET_NAME)
                                .key(key)
                                .build();
                        s3.putObject(putReq, tempFile);
                        Files.deleteIfExists(tempFile);
                        if (additionalImagesBuilder.length() > 0) additionalImagesBuilder.append(",");
                        additionalImagesBuilder.append(getPublicUrl(key));
                    } catch (Exception e) {
                        System.err.println("Failed to upload additional image to S3: " + e.getMessage());
                    }
                }
            }
            inventoryMaster.additionalImages = additionalImagesBuilder.toString();
        }
        return inventoryMaster;
    }

    private static String getPublicUrl(String key) {
        return "https://" + BUCKET_NAME + ".s3." + REGION.id() + ".amazonaws.com/" + key;
    }
}
