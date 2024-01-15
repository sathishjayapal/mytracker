package me.sathish.trackgarmin.controllers;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sathish.trackgarmin.exception.GarminRunNotFoundException;
import me.sathish.trackgarmin.model.query.FindGarminRunsQuery;
import me.sathish.trackgarmin.model.request.GarminRunRequest;
import me.sathish.trackgarmin.model.response.GarminRunResponse;
import me.sathish.trackgarmin.model.response.PagedResult;
import me.sathish.trackgarmin.services.GarminRunService;
import me.sathish.trackgarmin.utils.AppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/garminrun")
@Slf4j
@RequiredArgsConstructor
public class GarminRunController {

    private final GarminRunService garminRunService;

    @GetMapping
    public PagedResult<GarminRunResponse> getAllGarminRuns(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false)
                    int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false)
                    int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false)
                    String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false)
                    String sortDir) {
        FindGarminRunsQuery findGarminRunsQuery = new FindGarminRunsQuery(pageNo, pageSize, sortBy, sortDir);
        return garminRunService.findAllGarminRuns(findGarminRunsQuery);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarminRunResponse> getGarminRunById(@PathVariable Long id) {
        return garminRunService
                .findGarminRunById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new GarminRunNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<GarminRunResponse> createGarminRun(
            @RequestBody @Validated GarminRunRequest garminRunRequest) {
        GarminRunResponse response = garminRunService.saveGarminRun(garminRunRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/api/garminrun/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarminRunResponse> updateGarminRun(
            @PathVariable Long id, @RequestBody @Valid GarminRunRequest garminRunRequest) {
        return ResponseEntity.ok(garminRunService.updateGarminRun(id, garminRunRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GarminRunResponse> deleteGarminRun(@PathVariable Long id) {
        return garminRunService
                .findGarminRunById(id)
                .map(garminRun -> {
                    garminRunService.deleteGarminRunById(id);
                    return ResponseEntity.ok(garminRun);
                })
                .orElseThrow(() -> new GarminRunNotFoundException(id));
    }
}
