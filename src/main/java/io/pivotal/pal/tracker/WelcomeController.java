package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    public WelcomeController(@Value("${welcome.message}")
                                      String welcomMessage) {

        this.welcomMessage = welcomMessage;
    }

    private String welcomMessage;

    @GetMapping("/")
    public String sayHello() {
        return welcomMessage;
    }


}
