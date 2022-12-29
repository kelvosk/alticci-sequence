package com.alticci.sequence.serviceimpl;

import com.alticci.sequence.error.IndexException;
import com.alticci.sequence.error.NegativeIndexException;
import com.alticci.sequence.service.SequenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SequenceServiceImpl implements SequenceService {

    private Map<Long, Long> map = new HashMap<>();
    private static final String CLEAR_MESSAGE = "The cache is now cleared";
    private static final String NEGATIVE_MESSAGE = "Can't run a sequence using negative numbers";

    private static final String INDEX_MESSAGE = "Index is null";

    @PostConstruct
    void init() {
        log.info("Initializing map");
        this.map.put(0L, internalSeq(0L));
        this.map.put(1L, internalSeq(1L));
        this.map.put(2L, internalSeq(1L));
    }

    @Override
    public Map<Long, Long> calculateSequence(Long number) {
        long startTime = System.nanoTime();
        isNumberNegativeOrNull(number);

        run(number);
        double duration = System.nanoTime() - startTime;
        log.info("Execution Time: "  + (duration/1000000F) +  " milliseconds");
        return this.map;
    }

    @Override
    public String clearCache() {
        log.info("Clearing cache with: {} items", this.map.size());
        this.map.clear();
        this.init();
        return CLEAR_MESSAGE;
    }

    private void run(Long number){
        for(long i = 0; i <= number; i++){
            Long actualSec = findNumberInMap(i);
            if (actualSec == null) {
                save(i, internalSeq(i));
            } else {
                save(i, actualSec);
            }
        }
    }

    private void save(Long index, Long value){
        this.map.put(index, value);
    }

    private Long findNumberInMap(Long index) {
        return this.map.get(Optional.ofNullable(index).
                orElseThrow(() -> new IndexException(INDEX_MESSAGE)));
    }

    private Long internalSeq(Long number) {
        if(isNumberEqualToZero(number)) {
            return 0L;
        }
        if(isNumberLowerThanTwo(number)) {
            return 1L;
        }

        return internalSeq(number - 3) + internalSeq(number - 2);
    }

    private boolean isNumberEqualToZero(Long number) {
        return number == 0L;
    }

    private boolean isNumberLowerThanTwo(Long number) {
        return number <= 2L;
    }

    private void isNumberNegativeOrNull(Long number) {
        if(number < 0L || number == null)
            throw new NegativeIndexException(NEGATIVE_MESSAGE);
    }

}
