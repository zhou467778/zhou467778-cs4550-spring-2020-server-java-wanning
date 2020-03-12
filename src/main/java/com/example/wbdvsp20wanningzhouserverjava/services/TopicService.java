package com.example.wbdvsp20wanningzhouserverjava.services;

import com.example.wbdvsp20wanningzhouserverjava.models.Topic;
import com.example.wbdvsp20wanningzhouserverjava.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public List<Topic> findAllTopics() {
        return topicRepository.findAllTopics();
    }

    public Topic findTopicById(Integer tid){
        return topicRepository.findTopicById(tid);
    }

    public List<Topic> findTopicsForLesson(String lessonId){
        return topicRepository.findTopicsForLesson(lessonId);
    }

    public Topic createTopic(String lessonId, Topic newTopic){
        newTopic.setLessonId(lessonId);
        return topicRepository.save(newTopic);
    }

    public int deleteTopic(Integer tid){
        topicRepository.deleteById(tid);
        return 1;
    }

    public int updateTopic(Integer tid, Topic updatedTopic){
        Topic oldTopic = topicRepository.findTopicById(tid);
        oldTopic.setTitle(updatedTopic.getTitle());
        topicRepository.save(oldTopic);
        return 1;

    }

}
