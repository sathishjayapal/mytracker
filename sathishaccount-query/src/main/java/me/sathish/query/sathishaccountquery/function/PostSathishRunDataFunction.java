package me.sathish.query.sathishaccountquery.function;

import java.util.Optional;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import me.sathish.query.sathishaccountquery.domain.SathishRunData;
import me.sathish.query.sathishaccountquery.domain.SathishRunRecord;
import me.sathish.query.sathishaccountquery.repository.SathishRunDataRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostSathishRunDataFunction implements Consumer<SathishRunRecord> {
    private final SathishRunDataRepository repository;

    @Override
    public void accept(SathishRunRecord inputSathishRunData) {
        System.out.println("Received SathishRunRecord: " + inputSathishRunData);
        Optional<SathishRunData> runData = repository.findById(inputSathishRunData.Id());
        if (runData.isPresent()) {
            System.out.println("SathishRunData already exists: " + runData.get());
            SathishRunData existingRunData = runData.get();
            existingRunData.setTotalMiles(inputSathishRunData.totalMiles());
            existingRunData.setUpdatedBy("Sathishu");
            repository.save(existingRunData);
        } else {
            System.out.println("SathishRunData does not exist, creating new record");
            SathishRunData newRunData = new SathishRunData(inputSathishRunData.totalMiles());
            newRunData.setCreatedBy("Sathish");
            newRunData.setUpdatedBy("Sathish");
            repository.save(newRunData);
        }
    }
}
