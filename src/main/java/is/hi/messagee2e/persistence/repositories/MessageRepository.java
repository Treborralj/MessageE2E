package is.hi.messagee2e.persistence.repositories;

import is.hi.messagee2e.persistence.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/******************************************************************************
 * @author Róbert A. Jack
 * Tölvupóstur: ral9@hi.is
 * Lýsing : 
 *
 *****************************************************************************/
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
