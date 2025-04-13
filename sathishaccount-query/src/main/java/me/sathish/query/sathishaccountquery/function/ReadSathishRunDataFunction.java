package me.sathish.query.sathishaccountquery.function;

import java.math.BigDecimal;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import me.sathish.query.sathishaccountquery.domain.SathishRunData;
import me.sathish.query.sathishaccountquery.domain.SathishRunRecord;
import me.sathish.query.sathishaccountquery.repository.SathishRunDataRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReadSathishRunDataFunction implements Function<String, SathishRunRecord> {

    private final SathishRunDataRepository repository;

    @Override
    public SathishRunRecord apply(String id) {
        SathishRunData sathishRunData = repository.findById(Long.valueOf(id)).orElse(null);
        if (sathishRunData == null) {
            return new SathishRunRecord(0l, BigDecimal.ZERO);
        } else {
            return new SathishRunRecord(sathishRunData.getId(), sathishRunData.getTotalMiles());
        }
    }
}
