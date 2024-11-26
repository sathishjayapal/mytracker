package me.sathish.trackgarmin.controllers;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sathish.trackgarmin.audit.ConfigServerClient;
import me.sathish.trackgarmin.exception.GarminRunNotFoundException;
import me.sathish.trackgarmin.model.query.FindGarminRunsQuery;
import me.sathish.trackgarmin.model.request.GarminRunRequest;
import me.sathish.trackgarmin.model.response.GarminRunResponse;
import me.sathish.trackgarmin.model.response.PagedResult;
import me.sathish.trackgarmin.services.GarminRunService;
import me.sathish.trackgarmin.utils.GarminMSAppConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/garminrun")
@Slf4j
@RequiredArgsConstructor
public class GarminRunController {

    private final GarminRunService garminRunService;
    private final ConfigServerClient configServerClient;

    //    private final GarminRunProducer garminRunProducer;
    //    private final GarminRunConsumer garminRunConsumer;

    @GetMapping
    public PagedResult<GarminRunResponse> getAllGarminRuns(
            @RequestParam(value = "pageNo", defaultValue = GarminMSAppConstants.DEFAULT_PAGE_NUMBER, required = false)
                    int pageNo,
            @RequestParam(value = "pageSize", defaultValue = GarminMSAppConstants.DEFAULT_PAGE_SIZE, required = false)
                    int pageSize,
            @RequestParam(value = "sortBy", defaultValue = GarminMSAppConstants.DEFAULT_SORT_BY, required = false)
                    String sortBy,
            @RequestParam(
                            value = "sortDir",
                            defaultValue = GarminMSAppConstants.DEFAULT_SORT_DIRECTION,
                            required = false)
                    String sortDir) {
        //        garminRunProducer.send();
        FindGarminRunsQuery findGarminRunsQuery = new FindGarminRunsQuery(pageNo, pageSize, sortBy, sortDir);
        System.out.println("Garmin-MS The name of the thread is find all"
                + Thread.currentThread().getName());
        //        garminRunConsumer.recieve();
        configServerClient.getConfiguration("track-garmin", "dev");
        return garminRunService.findAllGarminRuns(findGarminRunsQuery);
    }

    @GetMapping("/search")
    public PagedResult<GarminRunResponse> searchGarminRuns(
            @RequestParam(value = "pageNo", defaultValue = GarminMSAppConstants.DEFAULT_PAGE_NUMBER, required = false)
                    int pageNo,
            @RequestParam(value = "pageSize", defaultValue = GarminMSAppConstants.DEFAULT_PAGE_SIZE, required = false)
                    int pageSize,
            @RequestParam(value = "sortBy", defaultValue = GarminMSAppConstants.DEFAULT_SORT_BY, required = false)
                    String sortBy,
            @RequestParam(
                            value = "sortDir",
                            defaultValue = GarminMSAppConstants.DEFAULT_SORT_DIRECTION,
                            required = false)
                    String sortDir,
            @RequestParam(value = "activityName", defaultValue = GarminMSAppConstants.DEFAULT_RUN, required = false)
                    String activityName) {
        FindGarminRunsQuery findGarminRunsQuery = new FindGarminRunsQuery(pageNo, pageSize, sortBy, sortDir);
        return garminRunService.searchGarminRuns(activityName, findGarminRunsQuery);
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
        System.out.println(
                "Garmin-MS The name of the thread is " + Thread.currentThread().getName());
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
