package is.hi.messagee2e.services.implementation;

import is.hi.messagee2e.dto.request.SendMessageRequest;
import is.hi.messagee2e.dto.response.ConversationSummaryResponse;
import is.hi.messagee2e.dto.response.MessageResponse;
import is.hi.messagee2e.persistence.entities.Message;
import is.hi.messagee2e.persistence.entities.User;
import is.hi.messagee2e.persistence.repositories.MessageRepository;
import is.hi.messagee2e.persistence.repositories.UserRepository;
import is.hi.messagee2e.services.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : 
 *
 *****************************************************************************/
@Service
@Transactional
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

        return messageRepository.findInboxMessages(currentUser.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<MessageResponse> getConversation(String otherUsersUsername, Authentication authentication){
        String username = authentication.getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        User otherUser = userRepository.findByUsername(otherUsersUsername)
                .orElseThrow(() -> new RuntimeException("Other user not found"));

        List<Message> conversation = messageRepository.findConversation(currentUser.getId(), otherUser.getId());

        List<Message> messageToUpdate = new ArrayList<>();
        for(Message message : conversation){
            if(message.getSender().getId() == otherUser.getId()
                    && message.getReceiver().getId() == currentUser.getId()
                    && !message.isRead()){
                message.setRead(true);
                messageToUpdate.add(message);
            }
        }
        if(!messageToUpdate.isEmpty()){
            messageRepository.saveAll(messageToUpdate);
        }

        return conversation.stream().map(this::mapToResponse).toList();
    }

    @Override
    public List<ConversationSummaryResponse> getConversationSummaries(Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Message> allMessages = messageRepository.findAllMessagesForUser(currentUser.getId());
        Map<String, ConversationSummaryResponse> conversationMap = new LinkedHashMap<>();

        for(Message message : allMessages){
            User otherUser;
            if(message.getSender().getId() == currentUser.getId()){
                otherUser = message.getReceiver();
            } else{
                otherUser = message.getSender();
            }
            String otherUsersUsername = otherUser.getUsername();
            if (!conversationMap.containsKey(otherUsersUsername)) {
                ConversationSummaryResponse summary = new ConversationSummaryResponse();
                summary.setOtherUsersUsername(otherUsersUsername);
                summary.setLastMessageEncryptedContent(message.getEncryptedContent());
                summary.setLastMessageSentAt(message.getSentAt());
                summary.setLastMessageRead(message.isRead());
                summary.setLastMessageSentByCurrentUser(
                        message.getSender().getId() == currentUser.getId()
                );
                summary.setUnreadCount(0);

                conversationMap.put(otherUsersUsername, summary);
            }
            if(message.getSender().getId() == otherUser.getId()
            && message.getReceiver().getId() == currentUser.getId()
            && !message.isRead()){
                ConversationSummaryResponse summary = conversationMap.get(otherUsersUsername);
                summary.setUnreadCount(summary.getUnreadCount() + 1);
            }
        }
        return new ArrayList<>(conversationMap.values());
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
