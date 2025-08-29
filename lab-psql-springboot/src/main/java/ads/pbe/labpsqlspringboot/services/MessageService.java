package ads.pbe.labpsqlspringboot.services;

import ads.pbe.labpsqlspringboot.models.Message;
import ads.pbe.labpsqlspringboot.repositories.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepo;
    public MessageService(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Transactional
    public Message pollMessage(String inbox) {
        Message message = this.messageRepo.findAndLockMessageByInbox(inbox);
        if (message != null) {
            this.messageRepo.deleteMessageById(message.getId());
        }
        return message;
    }

    public void newMessage(String inbox, String message) {
        Message msg = new Message(inbox, message, new Date());
        this.messageRepo.save(msg);
    }
}
