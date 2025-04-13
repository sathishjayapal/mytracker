package me.sathish.eventservice.events.data;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DomainEventDTO {
    private Long id;
    private String eventId;
    private String type;
    private String payload;
    private String domainName;
    private LocalDateTime eventRecordedAt;
}
