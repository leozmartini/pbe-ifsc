package ads.pbe.labpsqlspringboot.controllers;

import ads.pbe.labpsqlspringboot.models.Message;
import ads.pbe.labpsqlspringboot.services.MessageService;
import ads.pbe.labpsqlspringboot.utils.MessageInput;
import ads.pbe.labpsqlspringboot.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
    private final MessageService messageService;
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(path = "/getInboxMessage/{inbox}", produces="application/json")
    public ResponseEntity<Result> getInboxMessage(@PathVariable("inbox") String inbox) {
        Message message = messageService.pollMessage(inbox);
        if (message == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result("inbox '" + inbox + "' is empty"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new Result(message.getCreation_date() + ": " + message.getMessage()));
    }

    @PostMapping(path = "/newMessage/{inbox}", produces="application/json")
    public ResponseEntity<Result> newMessage(@PathVariable("inbox") String inbox, @RequestBody MessageInput messageInput) {
        if (messageInput.message() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result("'message' is required"));
        }

        messageService.newMessage(inbox, messageInput.message());
        return ResponseEntity.status(HttpStatus.CREATED).body(new Result("Created"));
    }
}
