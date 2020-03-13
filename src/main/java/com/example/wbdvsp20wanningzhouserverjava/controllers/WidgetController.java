package com.example.wbdvsp20wanningzhouserverjava.controllers;
import com.example.wbdvsp20wanningzhouserverjava.models.Widget;
import com.example.wbdvsp20wanningzhouserverjava.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    @Autowired
    WidgetService widgetService;

    @PostMapping("/api/topics/{topicId}/widgets")
    public Widget createWidget(
            @PathVariable("topicId") Integer tid,
            @RequestBody Widget newWidget) {
        return widgetService.createWidget(tid, newWidget);
    }


    @PostMapping("/api/widgets/up")
    public List<Widget> upWidget(
            @RequestBody Widget widget){
        return widgetService.upWidget(widget);
    }

    @PostMapping("/api/widgets/down")
    public List<Widget> downWidget(
            @RequestBody Widget widget){
        return widgetService.downWidget(widget);
    }


    @DeleteMapping("/api/widgets/{widgetId}")
    public int deleteWidget(
            @PathVariable("widgetId") Integer wid) {
        return widgetService.deleteWidget(wid);
    }

    @PutMapping("/api/widgets/{widgetId}")
    public int updateWidget(
            @PathVariable("widgetId") Integer wid,
            @RequestBody Widget updatedWidget) {
        return widgetService.updateWidget(wid, updatedWidget);
    }


    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return widgetService.findAllWidgets();
    }

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(
            @PathVariable("tid") Integer topicId) {
        return widgetService.findWidgetsForTopic(topicId);
    }

    @GetMapping("/api/widgets/{widgetId}")
    public Widget findWidgetById(
            @PathVariable("widgetId") Integer wid){
        return widgetService.findWidgetById(wid);
    }



}