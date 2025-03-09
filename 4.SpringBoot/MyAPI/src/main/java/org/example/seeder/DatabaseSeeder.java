package org.example.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private TablesSeeder tablesSeeder;

    @Autowired
    private RoleSeeder roleSeeder;

    @Autowired
    private UserSeeder userSeeder;

    @Value("${photos.dir}")
    private String PHOTO_FOLDER;
    private final String[] SAMPLE_PHOTOS = {
            "https://img.freepik.com/free-photo/modern-stationary-collection-arrangement_23-2149309643.jpg",
            "https://www.shutterstock.com/image-illustration/bookshelf-minimalist-simple-stuff-item-600nw-2341757133.jpg",
            "https://t3.ftcdn.net/jpg/03/34/79/68/360_F_334796865_VVTjg49nbLgQPG6rgKDjVqSb5XUhBVsW.jpg"
    };

    @Override
    public void run(String... args) throws Exception {
        tablesSeeder.seed();
        roleSeeder.seed();
        userSeeder.seed();
        createPhotoFolder();
        downloadSamplePhotos();
    }

    private void createPhotoFolder() {
        File folder = new File(PHOTO_FOLDER);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("Photo folder created at: " + folder.getAbsolutePath());
            } else {
                System.err.println("Failed to create photo folder at: " + folder.getAbsolutePath());
            }
        } else {
            System.out.println("Photo folder already exists at: " + folder.getAbsolutePath());
        }
    }

    private void downloadSamplePhotos() {
        downloadPhoto(SAMPLE_PHOTOS[0], "electronics.jpg");
        downloadPhoto(SAMPLE_PHOTOS[1], "books.jpg");
        downloadPhoto(SAMPLE_PHOTOS[2], "clothing.jpg");
    }

    private void downloadPhoto(String photoUrl, String fileName) {
        try {
            URL url = new URL(photoUrl);
            //String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);
            Path targetPath = Path.of(PHOTO_FOLDER, fileName);
            Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Downloaded photo: " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to download photo from URL: " + photoUrl);
            e.printStackTrace();
        }
    }
}
