package com.example.wbdvsp20wanningzhouserverjava.repositories;

import com.example.wbdvsp20wanningzhouserverjava.models.Widget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
    @Query("SELECT widget FROM Widget widget")
    public List<Widget> findAllWidgets();

    @Query("SELECT widget FROM Widget widget WHERE widget.id=:wid")
    public Widget findWidgetById(
            @Param("wid") Integer wid);

        @Query("SELECT widget FROM Widget widget WHERE widget.topic.id=:tid")
    public List<Widget> findWidgetsForTopic(
            @Param("tid") int topicId);

        @Query("SELECT COUNT(widget) FROM Widget widget WHERE widget.topic.id=:tid")
    public Integer setWidgetOrder(
            @Param("tid") int topicId);


}
