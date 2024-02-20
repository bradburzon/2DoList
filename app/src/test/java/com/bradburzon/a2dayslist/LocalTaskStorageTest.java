package com.bradburzon.a2dayslist;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalTaskStorageTest {
    private Map<String, Task> startingData;

    @Before
    public void setUp() throws Exception {
        startingData = new HashMap<>();
        startingData.put("1", new Task("1", "name", 1, TaskStatus.CREATED));
    }

    @Test
    public void givenNonEmptyStartingDataWhenListIsCalledThenReturnStartingData() {
        TaskStorage storage = new LocalTaskStorage(startingData);

        List<Task> list = storage.list();

        assertEquals(new ArrayList<>(startingData.values()), list);
    }

    @Test
    public void givenNewTaskWhenAddIsCalledThenStoreTheTask() {
        TaskStorage storage = new LocalTaskStorage(new HashMap<String, Task>());

        storage.add(new Task("1", "name", 1, TaskStatus.CREATED));
        List<Task> list = storage.list();

        assertEquals(new ArrayList<>(startingData.values()), list);
    }

    @Test
    public void givenNonEmptyStartingDataWhenDeleteIsCalledThenRemoveTaskFromStorage() {
        TaskStorage storage = new LocalTaskStorage(startingData);

        Task actual = storage.delete("1");
        List<Task> list = storage.list();

        assertEquals(0, list.size());
        assertEquals( new Task("1", "name", 1, TaskStatus.CREATED), actual);
    }

    @Test
    public void givenNonEmptyStartingDataWhenGetByIdIsCalledThenReturnMatchingTask() {
        TaskStorage storage = new LocalTaskStorage(startingData);

        Task actual = storage.getById("1");;

        assertEquals( new Task("1", "name", 1, TaskStatus.CREATED), actual);
    }

    @Test
    public void givenNonEmptyStartingDataWhenUpdateIsCalledThenUpdateMatchingTask() {
        TaskStorage storage = new LocalTaskStorage(startingData);

        storage.update("1", new Task("1", "updatedName", 1, TaskStatus.CREATED));
        Task actual = storage.getById("1");

        assertEquals( new Task("1", "updatedName", 1, TaskStatus.CREATED), actual);
    }

    @Test
    public void givenNonEmptyStartingDataWhenSortIsCalledThenSortTheData() {
        TaskStorage storage = new LocalTaskStorage(startingData);
        storage.add(new Task("1", "Zame", 1, TaskStatus.CREATED));
        storage.add(new Task("2", "Name", 2, TaskStatus.CREATED));

        storage.sort(new TaskSorter.NameSort());
        Task actual = storage.list().get(0);

        assertEquals( new Task("2", "Name", 2, TaskStatus.CREATED), actual);
    }
}