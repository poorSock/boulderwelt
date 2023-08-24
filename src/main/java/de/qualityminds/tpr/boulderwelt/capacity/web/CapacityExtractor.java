package de.qualityminds.tpr.boulderwelt.capacity.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CapacityExtractor {

    private static final Logger logger = LoggerFactory.getLogger(CapacityExtractor.class);

    private static final String BOULDERWELT_URL = "https://www.boulderwelt-muenchen-ost.de/";

    public Double getCapacityFromWeb(){
        String tagSelector = ".crowd-level-pointer";
        try {
            Document document = Jsoup.connect(BOULDERWELT_URL).get();
            Element element = document.select(tagSelector).first();
            String styleAttribute = element.child(0).attr("style");
            return extractCapacityFromStyleMargin(styleAttribute);
        } catch (IOException | NullPointerException e) {
            logger.error("No Element with selector '{}' found on page '{}'", tagSelector, BOULDERWELT_URL);
            logger.debug(e.getMessage());
        }
        return null;
    }

    private Double extractCapacityFromStyleMargin(String styleAttribute){
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(styleAttribute);

        if (matcher.find()) {
            String numberStr = matcher.group();
            double currentCapacity = Double.parseDouble(numberStr);
            BigDecimal roundedCapacity = BigDecimal.valueOf(currentCapacity).setScale(2, RoundingMode.HALF_UP);
            return roundedCapacity.doubleValue();
        } else {
            logger.error("Couldn't get capacity from styleMargin '{}'", styleAttribute);
        }
        return null;
    }
}
