package ir.mahdi.sample.microservice.cards.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientContext {

    private String mobileNumber;
    private String deviceId;
    private String platform;       // ANDROID / IOS / WEB
    private String appVersion;
    private String ipAddress;
}

/**
 * we can use add into this context these fields for feature applications features
 *
 * fraud detection
 * device tracking
 * rate limiting per device
 * geo-analysis
 */
