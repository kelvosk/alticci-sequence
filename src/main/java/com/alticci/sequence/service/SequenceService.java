package com.alticci.sequence.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SequenceService {

    Map<Long, Long> calculateSequence(Long number);
    String clearCache();
}
