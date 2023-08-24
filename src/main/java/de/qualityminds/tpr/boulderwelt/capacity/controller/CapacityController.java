package de.qualityminds.tpr.boulderwelt.capacity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("capacity")
public class CapacityController {

    @GetMapping
    public @ResponseBody ResponseEntity<String> getCurrentCapacity() {
        return new ResponseEntity<>("42", HttpStatus.OK);
    }

}
