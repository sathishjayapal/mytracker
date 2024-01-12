package me.sathish.trackstrava.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StravaRunResponse(Long run_number, String run_name, LocalDateTime run_date, int miles, Long start_location) {}
