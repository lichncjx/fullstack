package com.onecool.fullstack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongController {

    @GetMapping("/ping")
    public PingPong getPingPong() {
        return new PingPong("Pong");
    }

    public record PingPong(String result) {
    }
}
