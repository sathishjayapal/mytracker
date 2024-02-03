package me.sathish.trackstrava.services;

import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;

import lombok.RequiredArgsConstructor;
import me.sathish.trackstrava.entities.StravaRun;
import me.sathish.trackstrava.exception.StravaRunNotFoundException;
import me.sathish.trackstrava.mapper.StravaRunMapper;
import me.sathish.trackstrava.model.query.FindStravaRunsQuery;
import me.sathish.trackstrava.model.request.StravaRunRequest;
import me.sathish.trackstrava.model.response.PagedResult;
import me.sathish.trackstrava.model.response.StravaRunResponse;
import me.sathish.trackstrava.repositories.StravaRunRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StravaRunService {

    private final StravaRunRepository stravaRunRepository;
    private final StravaRunMapper stravaRunMapper;
    public Optional<StravaRunResponse> findStravaRunById(Long id) {
        return stravaRunRepository.findById(id).map(stravaRunMapper::toResponse);
    }
    public List<StravaRunResponse> findAllStravaRuns() {

        // create Pageable instance
        List<StravaRun> stravaRunsPage = stravaRunRepository.findAllByRunNumber();

        List<StravaRunResponse> stravaUserResponseList =
            stravaRunMapper.toResponseList(stravaRunsPage);

        return stravaUserResponseList;
    }
    private Pageable createPageable(FindStravaRunsQuery findStravaRunsQuery) {
        int pageNo = Math.max(findStravaRunsQuery.pageNo() - 1, 0);
        Sort sort = Sort.by(
            findStravaRunsQuery.sortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.Order.asc(findStravaRunsQuery.sortBy())
                : Sort.Order.desc(findStravaRunsQuery.sortBy()));
        return PageRequest.of(pageNo, findStravaRunsQuery.pageSize(), sort);
    }

    @Transactional
    public StravaRunResponse saveStravaRun(StravaRunRequest stravaRunRequest) {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                System.out.println("Inserting from the system thread \t" +Thread.currentThread().getName());
                StravaRun stravaRun = stravaRunMapper.toEntity(stravaRunRequest);
                stravaRun.setRunNumber(RandomGenerator.getDefault().nextLong());
                stravaRun.setCustomerId(RandomGenerator.getDefault().nextLong(0L,Long.MAX_VALUE));
                StravaRun savedStravaRun = stravaRunRepository.save(stravaRun);
                }).start();
        }
        StravaRun stravaRun = stravaRunMapper.toEntity(stravaRunRequest);
        stravaRun.setRunNumber(RandomGenerator.getDefault().nextLong());
        stravaRun.setCustomerId(RandomGenerator.getDefault().nextLong(0L,Long.MAX_VALUE));
        StravaRun savedStravaRun = stravaRunRepository.save(stravaRun);
        return stravaRunMapper.toResponse(savedStravaRun);
    }

    @Transactional
    public StravaRunResponse updateStravaRun(Long id, StravaRunRequest stravaRunRequest) {
        StravaRun stravaRun =
                stravaRunRepository
                        .findById(id)
                        .orElseThrow(() -> new StravaRunNotFoundException(id));

        // Update the stravaRun object with data from stravaRunRequest
        stravaRunMapper.mapStravaRunWithRequest(stravaRun, stravaRunRequest);

        // Save the updated stravaRun object
        StravaRun updatedStravaRun = stravaRunRepository.save(stravaRun);

        return stravaRunMapper.toResponse(updatedStravaRun);
    }

    @Transactional
    public void deleteStravaRunById(Long id) {
        stravaRunRepository.deleteById(id);
    }
}
