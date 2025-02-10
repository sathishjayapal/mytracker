package me.sathish.query.sathishaccountquery.domain;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public record SathishRunRecord(Long Id,BigDecimal totalMiles) {
}

