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
public class ConversationSummaryResponse {
    private String otherUsersUsername;
    private String lastMessageEncryptedContent;
    private LocalDateTime lastMessageSentAt;
    private boolean lastMessageRead;
    private boolean lastMessageSentByCurrentUser;
    private int unreadCount;

    public ConversationSummaryResponse() {
    }

    public ConversationSummaryResponse(String otherUsersUsername,
                                       String lastMessageEncryptedContent,
                                       LocalDateTime lastMessageSentAt,
                                       boolean lastMessageRead,
                                       boolean lastMessageSentByCurrentUser,
                                       int unreadCount) {
        this.otherUsersUsername = otherUsersUsername;
        this.lastMessageEncryptedContent = lastMessageEncryptedContent;
        this.lastMessageSentAt = lastMessageSentAt;
        this.lastMessageRead = lastMessageRead;
        this.lastMessageSentByCurrentUser = lastMessageSentByCurrentUser;
        this.unreadCount = unreadCount;
    }
}
