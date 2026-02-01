package me.sathish.garmindatainitializer.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DomainEventDTO {

    private Long id;

    @NotNull private String eventId;

    @NotNull private String eventType;

    @NotNull private String payload;

    @NotNull @Size(max = 255)
    private String createdBy;

    @NotNull @Size(max = 255)
    private String updatedBy;

    private Long domain;
}
