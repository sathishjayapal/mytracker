package me.sathish.garmindatainitializer.tracker.data;

import jakarta.persistence.*;
import lombok.*;
import me.sathish.garmindatainitializer.data.GarminMSBaseEntity;

@Entity
@Table(name = "file_name_tracker", schema = "garminrunsschema")
@Getter
@Setter
@ToString
public class EventTracker extends GarminMSBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;
}
