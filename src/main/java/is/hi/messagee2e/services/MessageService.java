package is.hi.messagee2e.services;

import is.hi.messagee2e.dto.request.SendMessageRequest;
import is.hi.messagee2e.dto.response.ConversationSummaryResponse;
import is.hi.messagee2e.dto.response.MessageResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description: Defines message-related business functions.
 *
 *****************************************************************************/
public interface MessageService {
    public void sendMessage(SendMessageRequest request, Authentication authentication);

    public List<MessageResponse> getConversation(String otherUsersUsername, Authentication authentication);

    List<ConversationSummaryResponse> getConversationSummaries(Authentication authentication);
}
