package me.sathish.garmindatainitializer.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQuery(
    name = "FileNameTracker.findByFileName",
    query = "SELECT f FROM FileNameTracker f WHERE f.fileName = :fileName"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file_name_tracker", schema = "runs_schema")
public class FileNameTracker extends GarminMSBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "file_name", unique = true)
    private String fileName;


}
