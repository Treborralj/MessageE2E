package is.hi.messagee2e.services.implementation;

import is.hi.messagee2e.persistence.repositories.MessageRepository;
import is.hi.messagee2e.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : 
 *
 *****************************************************************************/
@Service
public class MessageServiceImplementation implements MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImplementation(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

}
