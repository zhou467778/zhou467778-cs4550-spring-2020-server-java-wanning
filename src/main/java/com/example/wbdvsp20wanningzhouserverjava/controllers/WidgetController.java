package com.example.wbdvsp20wanningzhouserverjava.controllers;
import com.example.wbdvsp20wanningzhouserverjava.models.Widget;
import com.example.wbdvsp20wanningzhouserverjava.services.WidgetService;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    WidgetService service = new WidgetService();

    @PostMapping("/api/widgets")
    public Widget createWidget(
            @RequestBody Widget newWidget) {
        return service.createWidget(newWidget);
    }


    @PostMapping("/api/widgets/up")
    public int upWidget(
            @RequestBody Widget widget){
        return service.upWidget(widget);
    }

    @PostMapping("/api/widgets/down")
    public int downWidget(
            @RequestBody Widget widget){
        return service.downWidget(widget);
    }


    @DeleteMapping("/api/widgets/{widgetId}")
    public int deleteWidget(
            @PathVariable("widgetId") String wid) {
        return service.deleteWidget(wid);
    }

    @PutMapping("/api/widgets/{widgetId}")
    public int updateWidget(
            @PathVariable("widgetId") String wid,
            @RequestBody Widget updatedWidget) {
        return service.updateWidget(wid, updatedWidget);
    }


    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return service.findAllWidgets();
    }

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(
            @PathVariable("tid") String topicId) {
        return service.findWidgetsForTopic(topicId);
    }

    @GetMapping("api/widgets/{widgetId}")
    public Widget findWidgetById(
            @PathVariable("widgetId") String wid){
        return service.findWidgetById(wid);
    }



}