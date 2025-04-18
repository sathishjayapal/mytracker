package me.sathish.trackstrava.controllers;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sathish.trackstrava.exception.StravaRunNotFoundException;
import me.sathish.trackstrava.model.request.StravaRunRequest;
import me.sathish.trackstrava.model.response.StravaRunResponse;
import me.sathish.trackstrava.services.StravaRunService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/stravarun")
@Slf4j
@RequiredArgsConstructor
public class StravaRunController {
    private final StravaRunService stravaRunService;

    @GetMapping
    public List<StravaRunResponse> getAllStravaRuns() {
        System.out.println("STRAVA-MS The name of the thread is find all"
                + Thread.currentThread().getName());
        return stravaRunService.findAllStravaRuns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StravaRunResponse> getStravaRunById(@PathVariable Long id) {
        return stravaRunService
                .findStravaRunById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new StravaRunNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<StravaRunResponse> createStravaRun(
            @RequestBody @Validated StravaRunRequest stravaRunRequest) {
        StravaRunResponse response = stravaRunService.saveStravaRun(stravaRunRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/api/stravarun/{id}")
                .buildAndExpand(response.run_number())
                .toUri();
        System.out.println(
                "Strava-MS The name of the thread is " + Thread.currentThread().getName());
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StravaRunResponse> updateStravaRun(
            @PathVariable Long id, @RequestBody @Valid StravaRunRequest stravaRunRequest) {
        return ResponseEntity.ok(stravaRunService.updateStravaRun(id, stravaRunRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StravaRunResponse> deleteStravaRun(@PathVariable Long id) {
        return stravaRunService
                .findStravaRunById(id)
                .map(stravaRun -> {
                    stravaRunService.deleteStravaRunById(id);
                    return ResponseEntity.ok(stravaRun);
                })
                .orElseThrow(() -> new StravaRunNotFoundException(id));
    }
}
