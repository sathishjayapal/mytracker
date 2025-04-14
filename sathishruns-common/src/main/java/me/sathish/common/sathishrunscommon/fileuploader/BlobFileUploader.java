package me.sathish.common.sathishrunscommon.fileuploader;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobContainerItem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BlobFileUploader {
    private List<BlobContainerItem> containersList = List.of();
    private final BlobServiceClient blobServiceClient;

    @Value("${file.path}")
    private String filePath;

    public BlobFileUploader(Environment environment) {
        String connectStr = environment.getProperty("azure.storage.connection-string");
        blobServiceClient =
                new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
        try {
            containersList = blobServiceClient.listBlobContainers().stream().toList();
        } catch (Exception e) {
            log.error("Error in BlobFileUploader constructor");
        }
    }

    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    void listBlobs() {
        if (containersList.isEmpty()) {
            log.error("No blobs to list. No containers found");
            return;
        }
        for (BlobContainerItem container : containersList) {
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container.getName());
            containerClient.listBlobs().forEach(blob -> {
                log.error("Blob name: {}", blob.getName());
                log.error(
                        "Blob URL: {}",
                        containerClient.getBlobClient(blob.getName()).getBlobUrl());
            });
            log.error("Container name: {}", container.getName());
        }
    }

    void uploadActivity() {
        if (containersList.isEmpty()) {
            log.error("No uploads as - No containers found");
            return;
        }
        if (fileExists(filePath)) {
            containersList.stream().findFirst().ifPresent(container -> {
                BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container.getName());
                boolean blobExists = containerClient.listBlobs().stream().anyMatch(blob -> blob.getName()
                        .equalsIgnoreCase(Paths.get(filePath).getFileName().toString()));
                if (blobExists) {
                    log.error("Blob already uploaded");
                } else {
                    containerClient
                            .getBlobClient(Paths.get(filePath).getFileName().toString())
                            .uploadFromFile(filePath, false);
                }
            });
        } else {
            System.out.println("File does not exist");
        }
    }
}
