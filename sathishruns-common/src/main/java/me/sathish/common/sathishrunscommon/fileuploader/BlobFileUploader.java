package me.sathish.common.sathishrunscommon.fileuploader;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobContainerItem;
import com.azure.storage.blob.models.BlobItem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BlobFileUploader implements CommandLineRunner {
    // Path to the file to be uploaded
    @Value("${file.path}")
    private String filePath;

    @Value("${azure.storage.connection-string}")
    private String connectStr;
    // List of blob containers in the storage account
    private List<BlobContainerItem> cotainersList;
    // Client for interacting with the Azure Blob Storage service
    private BlobServiceClient blobServiceClient;

    // Constructor to initialize the BlobServiceClient and list of containers

    public BlobFileUploader(@Value("${azure.storage.connection-string}") String connectStr) {
        this.connectStr = connectStr;

        blobServiceClient =
                new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
        cotainersList = blobServiceClient.listBlobContainers().stream().toList();
    }

    /**
     * Checks if a file exists at the given path.
     *
     * @param filePath the path to the file
     * @return true if the file exists, false otherwise
     */
    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    /**
     * Lists all blobs in each container in the storage account.
     */
    void listBlobs() {
        for (BlobContainerItem blobContainerItem : cotainersList) {
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(blobContainerItem.getName());
            containerClient.listBlobs().forEach(blobItem -> {
                log.error("This is the blob name: {}", blobItem.getName());
                BlobClient blobClient = containerClient.getBlobClient(blobItem.getName());
                log.error("Blob URL: " + blobClient.getBlobUrl());
            });
            log.error("This is the container name: " + blobContainerItem.getName());
        }
    }

    /**
     * Uploads the activity file to the first container in the list.
     * If the file already exists in the container, it will not be uploaded again.
     * If the file does not exist at the specified path, an error message will be printed.
     */
    void uploadActivity() {
        if (fileExists(filePath)) {
            cotainersList.stream().findFirst().ifPresent(blobContainerItem -> {
                BlobContainerClient containerClient =
                        blobServiceClient.getBlobContainerClient(blobContainerItem.getName());
                Optional<BlobItem> result = containerClient.listBlobs().stream()
                        .filter(s -> s.getName()
                                .equalsIgnoreCase(
                                        Paths.get(filePath).getFileName().toString()))
                        .findFirst();
                result.ifPresentOrElse(
                        blobItem -> log.error("Blob already uploaded", new Throwable("Blob already in the storage container")), () -> containerClient
                                .getBlobClient(Paths.get(filePath).getFileName().toString())
                                .uploadFromFile(filePath, false));
            });

        } else {
            System.out.println("File does not exist");
        }
    }

    /**
     * Runs the application, listing blobs and uploading the activity file.
     *
     * @param args command-line arguments
     * @throws Exception if an error occurs
     */
    @Override
    public void run(String... args) throws Exception {
        listBlobs();
        uploadActivity();
    }
}
