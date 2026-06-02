package ir.mahdi.sample.microservice.cards.utility;

import org.slf4j.MDC;

public final class CorrelationIdUtils {

    private CorrelationIdUtils() {
    }

    public static String getCorrelationId() {
        return MDC.get("correlationId");
    }
}
