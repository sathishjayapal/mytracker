package me.sathish.query.sathishaccountquery.repository;

import me.sathish.query.sathishaccountquery.domain.SathishRunData;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SathishRunDataRepository extends CrudRepository<SathishRunData, Long> {
}
