package com.alticci.sequence.controller;

import com.alticci.sequence.service.SequenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/alticci")
@Tag(name = "Sequence")
@Slf4j
public class SequenceController {

    final SequenceService sequenceService;
    final CacheManager cacheManager;

    public SequenceController(SequenceService sequenceService, CacheManager cacheManager) {
        this.sequenceService = sequenceService;
        this.cacheManager = cacheManager;
    }

    @GetMapping(value = "/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Calculate a sequence")
    @CrossOrigin
    @Cacheable("sequence")
    public ResponseEntity<Map<Long, Long>> calculateSequence(@PathVariable("n") Long number) {
        return new ResponseEntity<>(sequenceService.calculateSequence(number), HttpStatus.OK);
    }

    @PostMapping(value = "/clearCache")
    @Operation(description = "Clear the cache")
    @CrossOrigin
    public ResponseEntity<String> clearCache() {

        for(String cache : cacheManager.getCacheNames()) {
            cacheManager.getCache(cache).clear();
        }
        return new ResponseEntity<>(sequenceService.clearCache(), HttpStatus.OK);
    }
}
