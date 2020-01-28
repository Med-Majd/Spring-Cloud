package com.sid.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CircuitController {

    @GetMapping("/defaultCountries")
    public Map<String,String> countries() {
        Map<String, String> data = new HashMap<>();
        data.put("message","default Countries");
        data.put("countries","Maroc, Algerie, Tunisie, ...");
        return data;
    }


    @GetMapping("/defaultSalat")
    public Map<String,String> muslim() {
        Map<String, String> data = new HashMap<>();
        data.put("message","Horaire priere");
        data.put("fajr","7:00");
        data.put("dohr","13:57");
        return data;
    }
}
