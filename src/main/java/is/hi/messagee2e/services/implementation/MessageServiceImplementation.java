package is.hi.messagee2e.services.implementation;

import is.hi.messagee2e.dto.request.SendMessageRequest;
import is.hi.messagee2e.dto.response.MessageResponse;
import is.hi.messagee2e.persistence.entities.Message;
import is.hi.messagee2e.persistence.entities.User;
import is.hi.messagee2e.persistence.repositories.MessageRepository;
import is.hi.messagee2e.persistence.repositories.UserRepository;
import is.hi.messagee2e.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : 
 *
 *****************************************************************************/
@Service
public class MessageServiceImplementation implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageServiceImplementation(MessageRepository messageReposito, UserRepository userRepository) {
        this.messageRepository = messageReposito;
        this.userRepository = userRepository;
    }

    public MessageResponse sendMessage(SendMessageRequest request, Authentication authentication){
        String senderUsername = authentication.getName();

        User sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findByUsername(request.getReceiverUsername())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (request.getEncryptedContent() == null || request.getEncryptedContent().isBlank()){
            throw new RuntimeException("Message content cannot be empty");
        }

        Message message = new Message(request.getEncryptedContent(), LocalDateTime.now(), sender, receiver);

        Message savedMessage = messageRepository.save(message);

        return mapToResponse(savedMessage);

    }

    public List<MessageResponse> getInbox(Authentication authentication){
        String username = authentication.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return messageRepository.findByReceiverIdOrderBySentAtAsc(currentUser.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<MessageResponse> getConversation(int otherUserId, Authentication authentication){
        String username = authentication.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.findById(otherUserId)
                .orElseThrow(() -> new RuntimeException("Other user not found"));

        return messageRepository.findConversation(currentUser.getId(), otherUserId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private MessageResponse mapToResponse(Message message){
        return new MessageResponse(
                message.getId(),
                message.getSender().getId(),
                message.getSender().getUsername(),
                message.getEncryptedContent(),
                message.getSentAt(),
                message.isRead()
        );
    }
}
