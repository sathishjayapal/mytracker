package me.sathish.trackgarmin.services;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.sathish.trackgarmin.entities.GarminRun;
import me.sathish.trackgarmin.exception.GarminRunNotFoundException;
import me.sathish.trackgarmin.mapper.GarminRunMapper;
import me.sathish.trackgarmin.model.query.FindGarminRunsQuery;
import me.sathish.trackgarmin.model.request.GarminRunRequest;
import me.sathish.trackgarmin.model.response.GarminRunResponse;
import me.sathish.trackgarmin.model.response.PagedResult;
import me.sathish.trackgarmin.repositories.GarminRunRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GarminRunService {

    private final GarminRunRepository garminRunRepository;
    private final GarminRunMapper garminRunMapper;

    public PagedResult<GarminRunResponse> findAllGarminRuns(FindGarminRunsQuery findGarminRunsQuery) {

        // create Pageable instance
        Pageable pageable = createPageable(findGarminRunsQuery);

        Page<GarminRun> garminRunsPage = garminRunRepository.findAll(pageable);

        List<GarminRunResponse> garminRunResponseList = garminRunMapper.toResponseList(garminRunsPage.getContent());

        return new PagedResult<>(garminRunsPage, garminRunResponseList);
    }

    private Pageable createPageable(FindGarminRunsQuery findGarminRunsQuery) {
        int pageNo = Math.max(findGarminRunsQuery.pageNo() - 1, 0);
        Sort sort = Sort.by(
                findGarminRunsQuery.sortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
                        ? Sort.Order.asc(findGarminRunsQuery.sortBy())
                        : Sort.Order.desc(findGarminRunsQuery.sortBy()));
        return PageRequest.of(pageNo, findGarminRunsQuery.pageSize(), sort);
    }

    public Optional<GarminRunResponse> findGarminRunById(Long id) {
        return garminRunRepository.findById(id).map(garminRunMapper::toResponse);
    }

    @Transactional
    public GarminRunResponse saveGarminRun(GarminRunRequest garminRunRequest) {
        GarminRun garminRun = garminRunMapper.toEntity(garminRunRequest);
        GarminRun savedGarminRun = garminRunRepository.save(garminRun);
        return garminRunMapper.toResponse(savedGarminRun);
    }

    @Transactional
    public GarminRunResponse updateGarminRun(Long id, GarminRunRequest garminRunRequest) {
        GarminRun garminRun = garminRunRepository.findById(id).orElseThrow(() -> new GarminRunNotFoundException(id));

        // Update the garminRun object with data from garminRunRequest
        garminRunMapper.mapGarminRunWithRequest(garminRun, garminRunRequest);

        // Save the updated garminRun object
        GarminRun updatedGarminRun = garminRunRepository.save(garminRun);

        return garminRunMapper.toResponse(updatedGarminRun);
    }

    @Transactional
    public void deleteGarminRunById(Long id) {
        garminRunRepository.deleteById(id);
    }
}
