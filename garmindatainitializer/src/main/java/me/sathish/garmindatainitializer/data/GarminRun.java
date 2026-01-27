package me.sathish.garmindatainitializer.data;

import jakarta.persistence.*;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class GarminRun {

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
    private String activityId;

    @Column(nullable = false, columnDefinition = "text")
    private String activityDate;

    @Column(nullable = false, columnDefinition = "text")
    private String activityType;

    @Column(nullable = false, columnDefinition = "text")
    private String activityName;

    @Column(columnDefinition = "text")
    private String activityDescription;

    @Column(columnDefinition = "text")
    private String elapsedTime;

    @Column(nullable = false, columnDefinition = "text")
    private String distance;

    @Column(columnDefinition = "text")
    private String maxHeartRate;

    @Column(columnDefinition = "text")
    private String calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false)
    private RunAppUser createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_by_id")
    private RunAppUser updateBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime updatedAt;

}

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
class RunAppUser {

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

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "createdBy")
    private Set<GarminRun> createdByGarminRuns = new HashSet<>();

    @OneToMany(mappedBy = "createdBy")
    private Set<FileNameTracker> createdByFileNameTrackers = new HashSet<>();

    @OneToMany(mappedBy = "updateBy")
    private Set<GarminRun> updatedByGarminRuns = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "run_app_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RunnerAppRole> roles = new HashSet<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime updatedAt;

}
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
class RunnerAppRole {

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

    @Column(nullable = false, unique = true)
    private String roleName;

    @Column(length = 255)
    private String description;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime updatedAt;

}
