package com.example.wbdvsp20wanningzhouserverjava.services;
import com.example.wbdvsp20wanningzhouserverjava.models.Topic;
import com.example.wbdvsp20wanningzhouserverjava.models.Widget;
import com.example.wbdvsp20wanningzhouserverjava.repositories.TopicRepository;
import com.example.wbdvsp20wanningzhouserverjava.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.CollationElementIterator;
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
        List<Widget> widgets = selectedTopic.getWidgets();
        newWidget.setTopic(selectedTopic);
//        int newOrder = widgets.isEmpty() ? 0 : widgets.get(widgets.size() - 1).getWidgetorder() + 1;
//        newWidget.setWidgetorder(newOrder);
        newWidget.setWidgetorder(widgets.size());
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
        Widget thisWidget = widgetRepository.findWidgetById(wid);
        Topic topic = thisWidget.getTopic();
        int tid = topic.getId();
        List<Widget> widgets = widgetRepository.findWidgetsForTopic(tid);
        int thisOrder = thisWidget.getWidgetorder();
        widgetRepository.deleteById(wid);

        // when a widget has been deleted, orders of widgets which are after this widget
        // needs to be pushed back 1
        for (int i = thisOrder + 1; i < widgets.size(); i++){
            Widget widget = widgets.get(i);
                 widget.setWidgetorder(widgets.get(i).getWidgetorder() - 1);
                 widgetRepository.save(widget);
            }


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

    public List<Widget> upWidget(Widget widget){
        Topic topic = widget.getTopic();
        int tid = topic.getId();
        List<Widget> widgets = widgetRepository.findWidgetsForTopic(tid);
        for (int i = 0; i < widgets.size(); i++){
            Widget w = widgets.get(i);
            if(w.getId() == widget.getId()) {
                if (i != 0){
                    widget.setWidgetorder(i-1);
                    widgetRepository.save(widget);
                    Widget ww = widgets.get(i-1);
                    ww.setWidgetorder(i);
                    widgetRepository.save(ww);

                }
            }
        }
        return widgetRepository.findWidgetsForTopic(tid);

    }
public List<Widget> downWidget(Widget widget){
        Topic topic = widget.getTopic();
        int tid = topic.getId();
        List<Widget> widgets = widgetRepository.findWidgetsForTopic(tid);
        for (int i = 0; i < widgets.size(); i++){
            Widget w = widgets.get(i);
            if(w.getId() == widget.getId()){
                if (i < widgets.size()-1){
                    Widget ww = widgets.get(i+1);
                    ww.setWidgetorder(i);
                    widgetRepository.save(ww);
                    widget.setWidgetorder(i + 1);
                    widgetRepository.save(widget);


                }
            }
        }
        return widgetRepository.findWidgetsForTopic(tid);
}



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