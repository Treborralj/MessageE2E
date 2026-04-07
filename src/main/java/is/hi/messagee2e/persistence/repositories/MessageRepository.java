package is.hi.messagee2e.persistence.repositories;

import is.hi.messagee2e.persistence.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : 
 *
 *****************************************************************************/
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByReceiverIdOrderBySentAtAsc(int receiverId);

    @Query("""
            SELECT m
            FROM Message m
            WHERE (m.sender.id = :user1Id AND m.receiver.id = :user2Id)
               OR (m.sender.id = :user2Id AND m.receiver.id = :user1Id)
            ORDER BY m.sentAt ASC
                          
""")
    List<Message> findConversation(int user1Id, int user2Id);
}
