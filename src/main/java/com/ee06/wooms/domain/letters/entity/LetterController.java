package com.ee06.wooms.domain.letters.entity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LetterController {
    @PostMapping("/api/letters/test")
    public String test() {
        return "test";
    }
}
