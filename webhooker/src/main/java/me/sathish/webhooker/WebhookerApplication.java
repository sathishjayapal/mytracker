package me.sathish.webhooker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication
public class WebhookerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebhookerApplication.class, args);
    }

}
@RestController
@RequestMapping("/webhook")
class BlobUploaderWebHook {
    private static final String SECRET = "my_secret_token";

    @PostMapping("/webhook")
    public ResponseEntity<String> receiveWebhook(@RequestBody Map<String, Object> payload) {

        System.out.println("Valid Webhook received: " + payload);
        return ResponseEntity.ok("Success");
    }

}
