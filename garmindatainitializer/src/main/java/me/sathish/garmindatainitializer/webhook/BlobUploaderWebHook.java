package me.sathish.garmindatainitializer.webhook;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class BlobUploaderWebHook {
    private static final String SECRET = "my_secret_token";

    @PostMapping("/receive")
    public ResponseEntity<String> receiveWebhook(
            @RequestBody Map<String, Object> payload, @RequestHeader("X-Webhook-Token") String token) {
        if (!SECRET.equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        System.out.println("Valid Webhook received: " + payload);
        return ResponseEntity.ok("Success");
    }
}
