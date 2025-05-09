package me.sathish.garmindatainitializer.data;

import jakarta.persistence.*;

@Entity
@Table(name = "file_name_tracker", schema = "runs_schema")
public class FileNameTracker extends GarminMSBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "file_name", unique = true)
    private String fileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
