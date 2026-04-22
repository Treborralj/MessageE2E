package is.hi.messagee2e.controllers;

import is.hi.messagee2e.dto.request.SendMessageRequest;
import is.hi.messagee2e.dto.response.ConversationSummaryResponse;
import is.hi.messagee2e.dto.response.MessageResponse;
import is.hi.messagee2e.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description : Controller for handling API requests related to sending
 *               and receiving messages.
 *
 *****************************************************************************/
@RestController
@RequestMapping("/message")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    /**
     * Stores an encrypted message sent by an authenticated user.
     * @param request the encrypted message and related data
     * @param authentication the authentication information of the current user
     * @return HTTP 200 ok if the message was successfully stored.
     */
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody SendMessageRequest request,
                                                       Authentication authentication){
        messageService.sendMessage(request, authentication);
        return ResponseEntity.ok().build();
    }

    /**
     * Returns the conversation between the authenticated user and another user.
     * @param username the username of the other participant
     * @param authentication the authentication information of the current user
     * @return a list of messages exchanged between the two users
     */
    @GetMapping("conversation/{username}")
    public ResponseEntity<List<MessageResponse>> getConversation(@PathVariable String username,
                                                                 Authentication authentication){
        return ResponseEntity.ok(messageService.getConversation(username, authentication));
    }

    /**
     * Returns conversation summaries for the authenticated user.
     * @param authentication the authentication information of the current user
     * @return a list of conversation summaries
     */
    @GetMapping("conversations")
    public ResponseEntity<List<ConversationSummaryResponse>> getConversationsSummaries(Authentication authentication){
        return ResponseEntity.ok(messageService.getConversationSummaries(authentication));
    }
}
