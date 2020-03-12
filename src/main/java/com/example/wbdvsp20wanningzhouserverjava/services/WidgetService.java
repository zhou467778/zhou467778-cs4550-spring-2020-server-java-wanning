package com.example.wbdvsp20wanningzhouserverjava.services;
import com.example.wbdvsp20wanningzhouserverjava.models.Topic;
import com.example.wbdvsp20wanningzhouserverjava.models.Widget;
import com.example.wbdvsp20wanningzhouserverjava.repositories.TopicRepository;
import com.example.wbdvsp20wanningzhouserverjava.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    TopicRepository topicRepository;


    public Widget createWidget(Integer tid, Widget newWidget){

        Topic selectedTopic = topicRepository.findTopicById(tid);
        Integer widgetOrder = widgetRepository.setWidgetOrder(tid);
        newWidget.setTopic(selectedTopic);
        newWidget.setWidgetorder(widgetOrder);
        return widgetRepository.save(newWidget);

    }


    public List<Widget> findAllWidgets() {
        return widgetRepository.findAllWidgets();

    }

    public List<Widget> findWidgetsForTopic(int topicId){
        return widgetRepository.findWidgetsForTopic(topicId);
    }

    public Widget findWidgetById(Integer wid){
        return widgetRepository.findWidgetById(wid);
    }


    public int deleteWidget(Integer wid){
        widgetRepository.deleteById(wid);
        return 1;
    }



    public int updateWidget(Integer wid, Widget updatedWidget) {
        Widget oldWidget = widgetRepository.findWidgetById(wid);
        oldWidget.setSize(updatedWidget.getSize());
        oldWidget.setTitle(updatedWidget.getTitle());
        oldWidget.setType(updatedWidget.getType());
        oldWidget.setListType(updatedWidget.getListType());
        oldWidget.setUrl(updatedWidget.getUrl());
        widgetRepository.save(oldWidget);
        return 1;
    }

    public int upWidget(Widget widget){
        Topic topic = widget.getTopic();
        Integer widgetOrder = widget.getWidgetorder();
        List<Widget> widgets = topic.getWidgets();
        for (int i = 0; i < widgets.size(); i++){
            Widget w = widgets.get(i);
            if(w.getId().equals(widget.getId())){
                if (i != 0){
                    widgets.get(i).setWidgetorder(widgetOrder - 1);
                    widgets.get(i-1).setWidgetorder(widgetOrder);
                    Collections.swap(widgets, i, i-1);


                    return 1;

                }
            }
        }
        return 0;

    }
//    public int upWidget(Widget widget){
//        String tid = widget.getTopicId();
//        List<Widget> widgets = widgetList.get(tid);
//        if (widgetList.containsKey(tid)){
//            for (int i = 0; i < widgets.size(); i++){
//                Widget w = widgets.get(i);
//                if (w.getId().equals(widget.getId())){
//                    if (i != 0){
//                        widgetList.get(tid).get(i).setOrder(i - 1);
//                        widgetList.get(tid).get(i - 1).setOrder(i);
//                        Collections.swap(widgetList.get(tid), i, i-1);
//                        return 1;
//
//                    }
//                }
//
//            }
//        }
//        return 0;
//    }



//
//    public int downWidget(Widget widget){
//        String tid = widget.getTopicId();
//        List<Widget> widgets = widgetList.get(tid);
//        if (widgetList.containsKey(tid)){
//            for (int i = 0; i < widgets.size(); i++){
//                Widget w = widgets.get(i);
//                if (w.getId().equals(widget.getId())){
//                    if (i != widgets.size()-1){
//                        widgetList.get(tid).get(i).setOrder(i+1);
//                        widgetList.get(tid).get(i+1).setOrder(i);
//                        Collections.swap(widgetList.get(tid), i, i+1);
//                        return 1;
//                    }
//                }
//            }
//        }
//        return 0;
//    }


}