package com.example.wbdvsp20wanningzhouserverjava.services;
import com.example.wbdvsp20wanningzhouserverjava.models.Widget;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WidgetService {
    List<Widget> widgetList = new ArrayList<Widget>();


    public Widget createWidget(Widget newWidget) {
        newWidget.setOrder(widgetList.size());
        widgetList.add(newWidget);
        return newWidget;
    }


    public Widget findWidgetById(String wid) {
        for (Widget w : widgetList) {
            if (w.getId().equals(wid)) {
                return w;
            }
        }
        return null;
    }

    public List<Widget> findAllWidgets() {
        return widgetList;
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        List<Widget> results = new ArrayList<Widget>();
        for (Widget w : widgetList) {
            if (w.getTopicId().equals(topicId)) {
                results.add(w);
            }
        }
        return results;
    }

    public int deleteWidget(String wid) {
        widgetList = widgetList.stream()
                .filter(w -> !w.getId().equals(wid)).collect(Collectors.toList());
        return 1;
    }

    public int updateWidget(String wid, Widget updatedWidget) {
        for (int i = 0; i < widgetList.size(); i++) {
            if (widgetList.get(i).getId().equals(wid)) {
                widgetList.set(i, updatedWidget);
                return 1;
            }
        }
        return 0;
    }



    public int upWidget(Widget widget) {
        for (int i = 0; i < widgetList.size(); i++) {
            Widget w = widgetList.get(i);
            if (w.getId().equals(widget.getId())) {
                if (i != 0) {
                    widgetList.get(i).setOrder(i - 1);
                    widgetList.get(i - 1).setOrder(i);
                    Collections.swap(widgetList, i, i-1);
                    return 1;
                }

            }
        }
        return 0;

    }


public int downWidget(Widget widget){
    for (int i = 0; i < widgetList.size(); i++){
        Widget w = widgetList.get(i);
        if (w.getId().equals(widget.getId())){
            if (i != widgetList.size() - 1){
                widgetList.get(i).setOrder(i+1);
                widgetList.get(i+1).setOrder(i);
                Collections.swap(widgetList,i, i+1 );
                return 1;
            }
        }
    }
    return 0;
}
}