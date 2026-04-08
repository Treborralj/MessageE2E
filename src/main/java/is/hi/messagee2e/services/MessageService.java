package is.hi.messagee2e.services;

import is.hi.messagee2e.dto.request.SendMessageRequest;
import is.hi.messagee2e.dto.response.ConversationSummaryResponse;
import is.hi.messagee2e.dto.response.MessageResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : 
 *
 *****************************************************************************/
public interface MessageService {
    public MessageResponse sendMessage(SendMessageRequest request, Authentication authentication);

    public List<MessageResponse> getInbox(Authentication authentication);

    public List<MessageResponse> getConversation(String otherUsersUsername, Authentication authentication);

    List<ConversationSummaryResponse> getConversationSummaries(Authentication authentication);
}
