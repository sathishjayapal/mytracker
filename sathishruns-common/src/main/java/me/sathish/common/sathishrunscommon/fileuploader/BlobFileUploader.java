package me.sathish.common.sathishrunscommon.fileuploader;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobContainerItem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class BlobFileUploader implements CommandLineRunner {
    // Path to the file to be uploaded
    private static final String FILE_PATH = "/Volumes/MacProHD/HDD_Downloads/Activities-1.csv";
    // Name of the blob container
    String blobContainerName = "devsathisprj1storaccnt";
    // Connection string for the Azure Blob Storage account
    String connectStr = "geturownconnectionstring";
    // List of blob containers in the storage account
    private List<BlobContainerItem> cotainersList;
    // Client for interacting with the Azure Blob Storage service
    private BlobServiceClient blobServiceClient;
    // Constructor to initialize the BlobServiceClient and list of containers
    public BlobFileUploader() {
        blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectStr)
                .buildClient();
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
                System.out.println("This is the containerClient1 name: " + blobItem.getName());
            });
            System.out.println("This is the blob name: " + blobContainerItem.getName());
        }
    }
    /**
     * Uploads the activity file to the first container in the list.
     *
     * @throws IOException if an I/O error occurs
     */
    void uploadActivity() throws IOException {
        if (fileExists(FILE_PATH)) {
            cotainersList.stream().findFirst().ifPresent(blobContainerItem -> {
                BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(blobContainerItem.getName());
                containerClient.getBlobClient(blobContainerName + LocalDateTime.now()).uploadFromFile(FILE_PATH);
            });
            System.out.println("File uploaded");
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
