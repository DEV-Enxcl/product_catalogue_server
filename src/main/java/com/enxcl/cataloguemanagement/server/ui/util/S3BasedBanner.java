package com.enxcl.cataloguemanagement.server.ui.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import com.enxcl.cataloguemanagement.server.ui.model.BannerConfig;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3BasedBanner {
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

    public static String saveBannerImage(BannerConfig bannerConfig) {
        if (bannerConfig.imageData != null && bannerConfig.imageData.length > 0) {
            String extension = ".jpg";
            String filename = bannerConfig.bannerID + "_" + UUID.randomUUID() + extension;
            String key = "images/" + filename;
            try {
                Path tempFile = Files.createTempFile("bannerimg", extension);
                Files.write(tempFile, bannerConfig.imageData, StandardOpenOption.WRITE);
                PutObjectRequest putReq = PutObjectRequest.builder()
                        .bucket(BUCKET_NAME)
                        .key(key)
                        .build();
                s3.putObject(putReq, tempFile);
                Files.deleteIfExists(tempFile);
                return getPublicUrl(key);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to upload banner image to S3: " + e.getMessage(), e);
            }
        }
        return null;
    }

    private static String getPublicUrl(String key) {
        return "https://" + BUCKET_NAME + ".s3." + REGION.id() + ".amazonaws.com/" + key;
    }
}