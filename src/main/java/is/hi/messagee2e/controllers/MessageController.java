package is.hi.messagee2e.controllers;

import is.hi.messagee2e.dto.request.SendMessageRequest;
import is.hi.messagee2e.dto.response.ConversationSummaryResponse;
import is.hi.messagee2e.dto.response.MessageResponse;
import is.hi.messagee2e.services.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : Controller for handling messages.
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

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendMessage(@Valid @RequestBody SendMessageRequest request,
                                                       Authentication authentication){
        return ResponseEntity.ok(messageService.sendMessage(request, authentication));
    }

    @GetMapping("/inbox")
    public ResponseEntity<List<MessageResponse>> getInbox(Authentication authentication){
        return ResponseEntity.ok(messageService.getInbox(authentication));
    }

    @GetMapping("conversation/{username}")
    public ResponseEntity<List<MessageResponse>> getConversation(@PathVariable String username,
                                                                 Authentication authentication){
        return ResponseEntity.ok(messageService.getConversation(username, authentication));
    }

    @GetMapping("conversations")
    public ResponseEntity<List<ConversationSummaryResponse>> getConversationsSummaries(Authentication authentication){
        return ResponseEntity.ok(messageService.getConversationSummaries(authentication));
    }
}
