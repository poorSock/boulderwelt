package de.qualityminds.tpr.boulderwelt.web;

import de.qualityminds.tpr.boulderwelt.capacity.web.CapacityExtractor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CapacityExtractorTest {

    @Autowired
    CapacityExtractor capacityExtractor;

    @Test
    void assertThatCapacityIsExtracted(){
        assertThat(capacityExtractor.getCapacityFromWeb()).isNotNaN();
        assertThat(capacityExtractor.getCapacityFromWeb()).isNotNegative();
    }
}
