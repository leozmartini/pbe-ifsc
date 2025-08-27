package ads.pbe.labpsqlspringboot.controllers;

import ads.pbe.labpsqlspringboot.services.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final MessageService messageService;
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(path = "/getAll", produces="application/json")
    public String listAllMessages() {
        return messageService.getAllUsers();
    }

    @GetMapping(path = "/insert", produces="application/json")
    public String inserta() {
        messageService.newMessage("1","um");
        return "ok";
    }
}
