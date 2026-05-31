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
public class CreateCardRequest {

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^09\\d{9}$", message = "Invalid mobile number")
    private String mobileNumber;

    @NotBlank(message = "Card holder name is required")
    private String cardHolderName;

    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
    private String cvv;

    @NotBlank(message = "Card type is required")
    private String cardType; // DEBIT / CREDIT

    @NotBlank(message = "Expiry date is required")
    private String expiryDate; // MM/YY

    private ClientContext clientContext;
}
