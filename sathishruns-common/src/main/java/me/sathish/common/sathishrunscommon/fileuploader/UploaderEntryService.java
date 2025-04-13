package me.sathish.common.sathishrunscommon.fileuploader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class UploaderEntryService {
    private final Environment environment;
    private final BlobFileUploader blobFileUploader;
    @Value("${file.folder}")
    private String folderPath;
    @Value("${file.path}")
    private String filePath;
    UploaderEntryService(Environment environment, BlobFileUploader blobFileUploader) {
        this.environment = environment;
        this.blobFileUploader = blobFileUploader;
    }

    @Scheduled(cron = "${scheduling.cron}")
    public void run() {
        if (environment.getActiveProfiles().length > 0) {
            String profile = environment.getActiveProfiles()[0];
            if (profile.equals("local")) {
                File folder = new File(folderPath);
                if (folder.exists() && folder.isDirectory()) {
                    File[] files = folder.listFiles();
                    if (files != null && files.length > 0) {
                        for (File file : files) {
                            System.out.println("File: " + file.getName());
                        }
                    } else {
                        System.out.println("Folder is empty");
                    }
                } else {
                    System.out.println("Folder does not exist or is not a directory");
                }
            } else {
                blobFileUploader.listBlobs();
                blobFileUploader.uploadActivity();
            }
        }
    }
}
