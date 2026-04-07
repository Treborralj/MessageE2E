package is.hi.messagee2e.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : Entity class for messages.
 *
 *****************************************************************************/
@Setter
@Getter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String encryptedContent;
    private LocalDateTime sentAt;
    private boolean delivered;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    public Message(String encryptedContent, LocalDateTime sentAt, boolean delivered, User sender, User receiver) {
        this.encryptedContent = encryptedContent;
        this.sentAt = sentAt;
        this.delivered = delivered;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message() {
    }

}
