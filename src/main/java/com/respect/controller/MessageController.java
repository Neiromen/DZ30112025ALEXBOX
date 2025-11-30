package com.respect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
    private final List<String> messages = new ArrayList<>();

    //curl --location 'localhost:8081/messages'
    @GetMapping("/messages/findBySubstring/{substring}")
    public ResponseEntity<List<String>> getMessages(@PathVariable String substring) {
        List<String> containsStrings = new ArrayList<>();
        for (String s : messages) {
            if (s.contains(substring)) {
                containsStrings.add(s);
            }
        }
        return ResponseEntity.ok(containsStrings);
    }

    //    curl --location 'localhost:8081/messages' \
    //    --header 'Content-Type: application/json' \
    //    --data 'Skipov'
    @PostMapping("/messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }

    //curl --location 'localhost:8081/messages/0' \
    //--data ''
    @GetMapping("/messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(messages.get(index));
    }

    //curl --location --request DELETE 'localhost:8081/messages/0'
    @DeleteMapping("/messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl --location --request PUT 'localhost:8081/messages/0' \
    //--header 'Content-Type: application/json' \
    //--data 'Skipov2'
    @PutMapping("/messages/{index}")
    public ResponseEntity<Void> updateMessage(@PathVariable("index") Integer i, @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    //curl --location 'localhost:8081/messages/search/Skipov'
    @GetMapping("/messages/search/{text}")
    public ResponseEntity<Integer> getFirstFindNeededTextIndex(@PathVariable String text) {
        int foundedIndex = 0;
        for (String s : messages) {
            if (s.equals(text)) {
                return ResponseEntity.ok(foundedIndex);
            }
            foundedIndex++;
        }
        return ResponseEntity.ok(-1);
    }

    //curl --location 'localhost:8081/messages/count'
    @GetMapping("/messages/count")
    public ResponseEntity<Integer> countMessages() {
        return ResponseEntity.ok(messages.size());
    }

    //curl --location 'localhost:8081/messages/1/create' \
    //--header 'Content-Type: application/json' \
    //--data 'Skipov'
    @PostMapping("/messages/{index}/create")
    public ResponseEntity<Void> createMessageWithIndex(@PathVariable int index, @RequestBody String text) {
        messages.add(index, text);
        return ResponseEntity.noContent().build();
    }

    //curl --location --request DELETE 'localhost:8081/messages/search/Skipov'
    @DeleteMapping("/messages/search/{text}")
    public ResponseEntity<Void> deleteWhereTextIsSubstring(@PathVariable String text) {
        int i = 0;
        for (String s : messages) {
            if (s.contains(text)) {
                messages.remove(i);
            }
        }
        return ResponseEntity.noContent().build();
    }


}
