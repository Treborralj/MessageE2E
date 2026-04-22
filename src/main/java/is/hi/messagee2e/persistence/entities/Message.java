package is.hi.messagee2e.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/******************************************************************************
 * @author Róbert A. Jack
 * e-mail: ral9@hi.is
 * Description: Entity representing an encrypted message
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
    @Column(name = "encryptedContent", nullable = false, columnDefinition = "TEXT")
    private String encryptedContent;
    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;
    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    public Message(String encryptedContent, LocalDateTime sentAt, User sender, User receiver) {
        this.encryptedContent = encryptedContent;
        this.sentAt = sentAt;
        this.isRead = false;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message() {
    }

}
