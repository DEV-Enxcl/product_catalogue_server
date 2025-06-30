package com.enxcl.cataloguemanagement.server.ui.util;

public class S3BasedBanner {
    private static final String BUCKET_NAME = "your-s3-bucket-name";
    private static final String S3_BASE_URL = "https://your-s3-bucket-name.s3.amazonaws.com/";

    /*public static String saveBannerImage(BannerConfig bannerConfig) {
        if (bannerConfig.imageData != null && bannerConfig.imageData.length > 0) {
            String extension = ".jpg"; // You may want to detect from content-type
            String filename = "images/" + bannerConfig.bannerID + "_" + UUID.randomUUID() + extension;

            S3Client s3 = S3Client.builder()
                    .region(Region.US_EAST_1) // Change to your region
                    .credentialsProvider(DefaultCredentialsProvider.create())
                    .build();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(filename)
                    .contentType("image/jpeg")
                    .build();

            s3.putObject(putObjectRequest, RequestBody.fromBytes(bannerConfig.imageData));

            return S3_BASE_URL + filename;
        }
        return null;
    }*/
}