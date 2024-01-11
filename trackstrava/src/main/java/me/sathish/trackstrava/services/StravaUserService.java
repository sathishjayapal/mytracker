package me.sathish.trackstrava.services;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.sathish.trackstrava.entities.StravaUser;
import me.sathish.trackstrava.exception.StravaUserNotFoundException;
import me.sathish.trackstrava.mapper.StravaUserMapper;
import me.sathish.trackstrava.model.query.FindStravaUsersQuery;
import me.sathish.trackstrava.model.request.StravaUserRequest;
import me.sathish.trackstrava.model.response.PagedResult;
import me.sathish.trackstrava.model.response.StravaUserResponse;
import me.sathish.trackstrava.repositories.StravaUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StravaUserService {

    private final StravaUserRepository stravaUserRepository;
    private final StravaUserMapper stravaUserMapper;

    public PagedResult<StravaUser> findAllStravaUsers(FindStravaUsersQuery findStravaUsersQuery) {

        // create Pageable instance
        Pageable pageable = createPageable(findStravaUsersQuery);

        Page<StravaUser> stravaUsersPage = stravaUserRepository.findAll(pageable);

        List<StravaUserResponse> stravaUserResponseList =
                stravaUserMapper.toResponseList(stravaUsersPage.getContent());

        return new PagedResult<>(stravaUsersPage);
    }

    private Pageable createPageable(FindStravaUsersQuery findStravaUsersQuery) {
        int pageNo = Math.max(findStravaUsersQuery.pageNo() - 1, 0);
        Sort sort =
                Sort.by(
                        findStravaUsersQuery.sortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
                                ? Sort.Order.asc(findStravaUsersQuery.sortBy())
                                : Sort.Order.desc(findStravaUsersQuery.sortBy()));
        return PageRequest.of(pageNo, findStravaUsersQuery.pageSize(), sort);
    }

    public Optional<StravaUserResponse> findStravaUserById(Long id) {
        return stravaUserRepository.findById(id).map(stravaUserMapper::toResponse);
    }

    @Transactional
    public StravaUserResponse saveStravaUser(StravaUserRequest stravaUserRequest) {
        StravaUser stravaUser = stravaUserMapper.toEntity(stravaUserRequest);
        StravaUser savedStravaUser = stravaUserRepository.save(stravaUser);
        return stravaUserMapper.toResponse(savedStravaUser);
    }

    @Transactional
    public StravaUserResponse updateStravaUser(Long id, StravaUserRequest stravaUserRequest) {
        StravaUser stravaUser =
                stravaUserRepository
                        .findById(id)
                        .orElseThrow(() -> new StravaUserNotFoundException(id));

        // Update the stravaUser object with data from stravaUserRequest
        stravaUserMapper.mapStravaUserWithRequest(stravaUser, stravaUserRequest);

        // Save the updated stravaUser object
        StravaUser updatedStravaUser = stravaUserRepository.save(stravaUser);

        return stravaUserMapper.toResponse(updatedStravaUser);
    }

    @Transactional
    public void deleteStravaUserById(Long id) {
        stravaUserRepository.deleteById(id);
    }
}
