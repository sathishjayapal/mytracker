package me.sathish.garmindatainitializer.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "FileNameTracker.findByFileName",
                query = "SELECT f FROM FileNameTracker f WHERE f.fileName = :fileName"),
        @NamedQuery(
                name = "FileNameTracker.findByFileName.count",
                query = "SELECT COUNT(f) FROM FileNameTracker f WHERE f.fileName = :fileName")
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class FileNameTracker {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false, columnDefinition = "text")
    private String fileName;

    @Column(length = 40)
    private String updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false)
    private RunAppUser createdBy;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_by")
    private RunAppUser updateBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime updatedAt;

}