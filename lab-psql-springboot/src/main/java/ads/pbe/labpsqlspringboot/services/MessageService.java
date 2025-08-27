package ads.pbe.labpsqlspringboot.services;

import ads.pbe.labpsqlspringboot.models.Message;
import ads.pbe.labpsqlspringboot.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepo;
    public MessageService(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    public String getAllUsers() {
        List<Message> allMessages = this.messageRepo.findAll();
        StringBuilder res = new StringBuilder();

        allMessages.forEach((m)->{
            res.append(m.getMessage() + "\n");
        });

        return res.toString();
    }

    public void newMessage(String inbox, String message) {
        Message msg = new Message(inbox, message);
        this.messageRepo.save(msg);
    }
}
