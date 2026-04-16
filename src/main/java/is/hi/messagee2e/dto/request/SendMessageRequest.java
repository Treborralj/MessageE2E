package is.hi.messagee2e.dto.request;

import jakarta.validation.Valid;
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
public class SendMessageRequest {
    @NotBlank(message = "Receiver username is required")
    private String receiverUsername;
    @Valid
    @NotBlank(message = "Encrypted content is required")
    private EncryptedMessagePayload encryptedContent;

    public SendMessageRequest() {
    }
}
