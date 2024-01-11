package me.sathish.trackstrava.controllers;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sathish.trackstrava.exception.StravaUserNotFoundException;
import me.sathish.trackstrava.model.request.StravaUserRequest;
import me.sathish.trackstrava.model.response.StravaUserResponse;
import me.sathish.trackstrava.services.StravaUserService;
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
@RequestMapping("/api/stravauser")
@Slf4j
@RequiredArgsConstructor
public class StravaUserController {

    private final StravaUserService stravaUserService;

    @GetMapping("/{id}")
    public ResponseEntity<StravaUserResponse> getStravaUserById(@PathVariable Long id) {
        return stravaUserService
                .findStravaUserById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new StravaUserNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<StravaUserResponse> createStravaUser(
            @RequestBody @Validated StravaUserRequest stravaUserRequest) {
        StravaUserResponse response = stravaUserService.saveStravaUser(stravaUserRequest);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/api/stravauser/{id}")
                        .buildAndExpand(response.id())
                        .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StravaUserResponse> updateStravaUser(
            @PathVariable Long id, @RequestBody @Valid StravaUserRequest stravaUserRequest) {
        return ResponseEntity.ok(stravaUserService.updateStravaUser(id, stravaUserRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StravaUserResponse> deleteStravaUser(@PathVariable Long id) {
        return stravaUserService
                .findStravaUserById(id)
                .map(
                        stravaUser -> {
                            stravaUserService.deleteStravaUserById(id);
                            return ResponseEntity.ok(stravaUser);
                        })
                .orElseThrow(() -> new StravaUserNotFoundException(id));
    }
}
