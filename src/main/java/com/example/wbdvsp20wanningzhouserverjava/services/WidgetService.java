package com.example.wbdvsp20wanningzhouserverjava.services;
import com.example.wbdvsp20wanningzhouserverjava.models.Widget;


import java.util.*;
import java.util.stream.Collectors;


public class WidgetService {

    Map<String, List<Widget>> widgetList = new HashMap<>();


    public Widget createWidget(Widget newWidget){
        String tid = newWidget.getTopicId();
        List<Widget> widgets = widgetList.get(tid);
        if (widgetList.containsKey(tid)){
            newWidget.setOrder(widgets.size());
            widgets.add(newWidget);
            widgetList.put(tid, widgets );
            return newWidget;
        }
        else{
            List<Widget> empty = new ArrayList<Widget>();
            newWidget.setOrder(0);
            empty.add(newWidget);
            widgetList.put(tid, empty);
            return newWidget;
        }
    }


    public List<Widget> findAllWidgets() {
        List<Widget> allWidgets =
                widgetList.values().stream().flatMap(List::stream).collect(Collectors.toList());
        return allWidgets;

    }

    public List<Widget> findWidgetsForTopic(String topicId){
        if(widgetList.containsKey(topicId)){
            return widgetList.get(topicId);
        }
        else{
            return new ArrayList<Widget>();
        }
    }


    public int deleteWidget(String wid){
        for (List<Widget> widgets: widgetList.values()){
            for (int i = 0; i < widgets.size(); i ++){
                if (widgets.get(i).getId().equals(wid)){
                    widgets.remove(i);
                }
                // after deleting, orders of widgets that are after this widget will be changed,
                // so need to minus one for the orders since they are pushed one back
                for (int j = i; j < widgets.size(); j++){
                    widgets.get(i).setOrder(widgets.get(j).getOrder() -1 );
                }
            }
        }
        return 1;
    }



    public int updateWidget(String wid, Widget updatedWidget) {
        for (List<Widget> widgets : widgetList.values()) {
            for (int i = 0; i < widgets.size(); i++) {
                if (widgets.get(i).getId().equals(wid)) {
                    widgets.set(i, updatedWidget);
                    return 1;
                }
            }
        }
        return 0;
    }





    public int upWidget(Widget widget){
        String tid = widget.getTopicId();
        List<Widget> widgets = widgetList.get(tid);
        if (widgetList.containsKey(tid)){
            for (int i = 0; i < widgets.size(); i++){
                Widget w = widgets.get(i);
                if (w.getId().equals(widget.getId())){
                    if (i != 0){
                        widgetList.get(tid).get(i).setOrder(i - 1);
                        widgetList.get(tid).get(i - 1).setOrder(i);
                        Collections.swap(widgetList.get(tid), i, i-1);
                        return 1;

                    }
                }

            }
        }
        return 0;
    }


    public int downWidget(Widget widget){
        String tid = widget.getTopicId();
        List<Widget> widgets = widgetList.get(tid);
        if (widgetList.containsKey(tid)){
            for (int i = 0; i < widgets.size(); i++){
                Widget w = widgets.get(i);
                if (w.getId().equals(widget.getId())){
                    if (i != widgets.size()-1){
                        widgetList.get(tid).get(i).setOrder(i+1);
                        widgetList.get(tid).get(i+1).setOrder(i);
                        Collections.swap(widgetList.get(tid), i, i+1);
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    public Widget findWidgetById(String wid){
        for(List<Widget> widgets: widgetList.values()){
            for (int i = 0; i < widgets.size(); i++){
                if (widgets.get(i).getId().equals(wid)){
                    return widgets.get(i);
                }
            }

        }
        return null;
    }
}