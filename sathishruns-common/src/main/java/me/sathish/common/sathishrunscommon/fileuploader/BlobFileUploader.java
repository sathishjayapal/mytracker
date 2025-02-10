package me.sathish.common.sathishrunscommon.fileuploader;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.BlobContainerItem;
import com.azure.storage.blob.models.BlobItem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BlobFileUploader {

    @Value("${file.path}")
    private String filePath;

    private final List<BlobContainerItem> containersList;
    private final BlobServiceClient blobServiceClient;

    public BlobFileUploader(@Value("${azure.storage.connection-string}") String connectStr) {
        blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
        containersList = blobServiceClient.listBlobContainers().stream().toList();
    }

    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    void listBlobs() {
        for (BlobContainerItem container : containersList) {
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container.getName());
            containerClient.listBlobs().forEach(blob -> {
                log.error("Blob name: {}", blob.getName());
                log.error("Blob URL: {}", containerClient.getBlobClient(blob.getName()).getBlobUrl());
            });
            log.error("Container name: {}", container.getName());
        }
    }

    void uploadActivity() {
        if (fileExists(filePath)) {
            containersList.stream().findFirst().ifPresent(container -> {
                BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container.getName());
                boolean blobExists = containerClient.listBlobs().stream()
                        .anyMatch(blob -> blob.getName().equalsIgnoreCase(Paths.get(filePath).getFileName().toString()));
                if (blobExists) {
                    log.error("Blob already uploaded");
                } else {
                    containerClient.getBlobClient(Paths.get(filePath).getFileName().toString()).uploadFromFile(filePath, false);
                }
            });
        } else {
            System.out.println("File does not exist");
        }
    }

    @Scheduled(cron = "${scheduling.cron}")
    public void run() {
        listBlobs();
        uploadActivity();
    }
}
