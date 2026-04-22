package is.hi.messagee2e.persistence.repositories;

import is.hi.messagee2e.persistence.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description: Repository for accessing information on messages.
 *
 *****************************************************************************/
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("""
        SELECT m
        FROM Message m
        JOIN FETCH m.sender
        JOIN FETCH m.receiver
        WHERE (m.sender.id = :user1Id AND m.receiver.id = :user2Id)
           OR (m.sender.id = :user2Id AND m.receiver.id = :user1Id)
        ORDER BY m.sentAt ASC
    """)
    List<Message> findConversation(@Param("user1Id") int user1Id,
                                   @Param("user2Id") int user2Id);

    @Query("""
    SELECT m
    FROM Message m
    JOIN FETCH m.sender
    JOIN FETCH m.receiver
    WHERE m.sender.id = :userId OR m.receiver.id = :userId
    ORDER BY m.sentAt DESC
""")
    List<Message> findAllMessagesForUser(@Param("userId") int userId);
}
