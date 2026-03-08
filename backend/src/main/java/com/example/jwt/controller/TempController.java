package com.example.jwt.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class TempController {

    @GetMapping("/orders")
    public List<String> getOrders(){
        return List.of(
            "Order 1 - Laptop",
            "Order 2 - Mobile",
            "Order 3 - Headphones"
        );
    }
}