package com.bradburzon.a2dayslist;

import java.util.List;

public interface TaskStorage {
    void add(Task task);
    void delete(String taskId);
    void update(String taskId, Task task);
    Task getById(String taskId);
    List<Task> list();
}
