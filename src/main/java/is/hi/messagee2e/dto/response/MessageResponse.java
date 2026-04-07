package is.hi.messagee2e.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : 
 *
 *****************************************************************************/
@Getter
@Setter
public class MessageResponse {
    private int id;
    private int senderId;
    private String senderUsername;
    private String ecnryptedContent;
    private LocalDateTime sentAt;
    private boolean isRead;

    public MessageResponse() {
    }

    public MessageResponse(int id,
                           int senderId,
                           String senderUsername,
                           String ecnryptedContent,
                           LocalDateTime sentAt,
                           boolean isRead) {
        this.id = id;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.ecnryptedContent = ecnryptedContent;
        this.sentAt = sentAt;
        this.isRead = isRead;
    }
}
