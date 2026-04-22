package is.hi.messagee2e.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description: DTO representing a message that is returned to the client.
 *
 *****************************************************************************/
@Getter
@Setter
public class MessageResponse {
    private int id;
    private int senderId;
    private String senderUsername;
    private String encryptedContent;
    private LocalDateTime sentAt;
    private boolean read;

    public MessageResponse() {
    }

    public MessageResponse(int id,
                           int senderId,
                           String senderUsername,
                           String encryptedContent,
                           LocalDateTime sentAt,
                           boolean read) {
        this.id = id;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.encryptedContent = encryptedContent;
        this.sentAt = sentAt;
        this.read = read;
    }
}
