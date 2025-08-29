package ads.pbe.labpsqlspringboot.repositories;

import ads.pbe.labpsqlspringboot.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * FROM message WHERE inbox = :inbox ORDER BY creation_date DESC LIMIT 1 FOR UPDATE", nativeQuery = true)
    Message findAndLockMessageByInbox(@Param("inbox") String inbox);

    @Modifying
    @Query(value = "DELETE FROM message WHERE id = :id", nativeQuery = true)
    void deleteMessageById(@Param("id") Long id);
}