package com.bradburzon.a2dayslist.tasks.storage;

import com.bradburzon.a2dayslist.tasks.Task;

import java.util.List;

public interface TaskStorage {

    void add(Task task);

    Task delete(String taskId);

    void update(String taskId, Task task);

    Task getById(String taskId);

    List<Task> list();
}

