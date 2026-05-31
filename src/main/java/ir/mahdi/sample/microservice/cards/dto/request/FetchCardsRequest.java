package ir.mahdi.sample.microservice.cards.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FetchCardsRequest {

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^09\\d{9}$")
    private String mobileNumber;
}
