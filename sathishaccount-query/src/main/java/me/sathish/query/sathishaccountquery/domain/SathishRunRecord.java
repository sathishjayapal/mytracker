package me.sathish.query.sathishaccountquery.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

public record SathishRunRecord(Long Id, BigDecimal totalMiles) {}
