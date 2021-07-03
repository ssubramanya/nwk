package com.legion.kickstart.Controller;

import com.google.cloud.firestore.DocumentSnapshot;
import com.legion.kickstart.DatabaseEntities.Component;
import com.legion.kickstart.DatabaseEntities.User;
import com.legion.kickstart.DatabaseService.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/entities")
public class EntityController {

    @Autowired
    private DatabaseService dbService;

    @PostMapping("/component")
    public String saveComponent(@RequestBody Component component) throws ExecutionException, InterruptedException {
        dbService.setCOLLECTION_NAME("Components");
        return dbService.saveEntity(component);
    }

    @GetMapping("/component/{componentId}")
    public Component getComponent(@PathVariable String componentId) throws ExecutionException, InterruptedException {
        dbService.setCOLLECTION_NAME("Components");
        DocumentSnapshot document = dbService.getEntity(componentId);
        Component requiredComponent = null;
        if(document.exists()) {
            requiredComponent = document.toObject(Component.class);
        }
        return requiredComponent;
    }

    @DeleteMapping("/component/{componentId}")
    public String deleteComponent(@PathVariable String componentId) throws ExecutionException, InterruptedException {
        dbService.setCOLLECTION_NAME("Components");
        return dbService.deleteEntity(componentId);
    }

    @PostMapping("/user")
    public String saveUser(@RequestBody User userData) throws ExecutionException, InterruptedException {
        dbService.setCOLLECTION_NAME("User");
        return dbService.saveEntity(userData);
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable String userId) throws ExecutionException, InterruptedException {
        dbService.setCOLLECTION_NAME("User");
        DocumentSnapshot document = dbService.getEntity(userId);
        User requiredComponent = null;
        if(document.exists()) {
            requiredComponent = document.toObject(User.class);
        }
        return requiredComponent;
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable String userId) throws ExecutionException, InterruptedException {
        dbService.setCOLLECTION_NAME("User");
        return dbService.deleteEntity(userId);
    }

}
