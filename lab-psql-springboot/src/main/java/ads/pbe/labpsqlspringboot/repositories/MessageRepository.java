package ads.pbe.labpsqlspringboot.repositories;

import ads.pbe.labpsqlspringboot.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}