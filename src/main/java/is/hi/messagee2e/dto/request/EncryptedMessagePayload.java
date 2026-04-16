package is.hi.messagee2e.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : 
 *
 *****************************************************************************/
@Getter
@Setter
public class EncryptedMessagePayload {
    @NotBlank(message = "Sender AES key is required")
    private String encryptedAesKeyForSender;

    @NotBlank(message = "Receiver AES key is required")
    private String encryptedAesKeyForReceiver;

    @NotBlank(message = "IV is required")
    private String iv;

    @NotBlank(message = "Ciphertext is required")
    private String ciphertext;

    public EncryptedMessagePayload() {
    }
}
