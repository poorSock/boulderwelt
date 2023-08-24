package de.qualityminds.tpr.boulderwelt.capacity.controller;

import de.qualityminds.tpr.boulderwelt.capacity.model.Capacity;
import de.qualityminds.tpr.boulderwelt.capacity.web.CapacityExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("capacity")
public class CapacityController {

    @Autowired
    CapacityExtractor capacityExtractor;

    @GetMapping
    public @ResponseBody ResponseEntity<Capacity> getCurrentCapacity() {
        double capacity = capacityExtractor.getCapacityFromWeb();
        return new ResponseEntity<>(new Capacity(capacity, LocalDateTime.now()), HttpStatus.OK);
    }

}
