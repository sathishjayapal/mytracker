package me.sathish.trackgarmin.services;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import me.sathish.trackgarmin.entities.GarminRun;
import me.sathish.trackgarmin.entities.User;
import me.sathish.trackgarmin.exception.GarminRunNotFoundException;
import me.sathish.trackgarmin.mapper.GarminRunMapper;
import me.sathish.trackgarmin.model.query.FindGarminRunsQuery;
import me.sathish.trackgarmin.model.request.GarminRunRequest;
import me.sathish.trackgarmin.model.response.GarminRunResponse;
import me.sathish.trackgarmin.model.response.PagedResult;
import me.sathish.trackgarmin.repositories.GarminRunRepository;
import me.sathish.trackgarmin.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GarminRunService {

    private final GarminRunRepository garminRunRepository;
    private final GarminRunMapper garminRunMapper;
    private final TransactionTemplate transactionTemplate;
    private final UserRepository userRepository;

    public PagedResult<GarminRunResponse> findAllGarminRuns(FindGarminRunsQuery findGarminRunsQuery) {

        // create Pageable instance
        Pageable pageable = createPageable(findGarminRunsQuery);
        return transactionTemplate.execute(status -> {
            // do something
            Page<GarminRun> garminRunPage = garminRunRepository.findAll(pageable);
            List<GarminRunResponse> garminRunMapperResponseList =
                    garminRunMapper.toResponseList(garminRunPage.getContent());
            return new PagedResult<>(garminRunPage, garminRunMapperResponseList);
        });
    }

    public PagedResult<GarminRunResponse> searchGarminRuns(String query, FindGarminRunsQuery findGarminRunsQuery) {
        Pageable pageable = createPageable(findGarminRunsQuery);
        return transactionTemplate.execute(status -> {
            // do something
            Page<GarminRun> garminRunPage =
                    garminRunRepository.findGarminRunsByActivityNameContainingIgnoreCase(query, pageable);
            List<GarminRunResponse> garminRunMapperResponseList =
                    garminRunMapper.toResponseList(garminRunPage.getContent());
            return new PagedResult<>(garminRunPage, garminRunMapperResponseList);
        });
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
        Optional<User> sathishUser= userRepository.findById(1L);
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                        System.out.println("Inserting from the virtual thread"
                                + Thread.currentThread().getName());
                        handleUserRequest(garminRunRequest,sathishUser);
                    })
                    .start();
        }
        return handleUserRequest(garminRunRequest, sathishUser);
    }

    private GarminRunResponse handleUserRequest(GarminRunRequest garminRunRequest, Optional<User> sathishUser) {
        GarminRun garminRun = garminRunMapper.toEntity(garminRunRequest);
        if (sathishUser.isPresent()) {
            garminRun.setCreatedBy(sathishUser.get());
        }
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
