package com.example.wbdvsp20wanningzhouserverjava.controllers;

import com.example.wbdvsp20wanningzhouserverjava.models.Topic;
import com.example.wbdvsp20wanningzhouserverjava.models.Widget;
import com.example.wbdvsp20wanningzhouserverjava.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TopicController {

    @Autowired
    TopicService topicService;

    @GetMapping("/api/topics")
    public List<Topic> findAllTopics(){
        return topicService.findAllTopics();
    }

    @GetMapping("/api/topics/{topicId}")
    public Topic findTopicById(
            @PathVariable("topicId") Integer tid){
        return topicService.findTopicById(tid);
    }

    @GetMapping("/api/lessons/{lessonId}/topics")
    public List<Topic> findTopicsForLesson(
            @PathVariable("lessonId") String lessonId) {
        return topicService.findTopicsForLesson(lessonId);
    }

    @PostMapping("/api/lessons/{lessonId}/topics")
    public Topic createTopic(
            @PathVariable("lessonId") String lessonId,
            @RequestBody Topic newTopic) {
        return topicService.createTopic(lessonId, newTopic);
    }

    @DeleteMapping("/api/topics/{topicId}")
    public int deleteTopic(
            @PathVariable("topicId") Integer tid) {
        return topicService.deleteTopic(tid);
    }

    @PutMapping("/api/topics/{topicId}")
    public int updateTopic(
            @PathVariable("topicId") Integer tid,
            @RequestBody Topic updatedTopic) {
        return topicService.updateTopic(tid, updatedTopic);
    }
}
