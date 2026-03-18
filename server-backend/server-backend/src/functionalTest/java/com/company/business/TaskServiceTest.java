package com.company.business;


import com.company.business.Services.TaskService;
import com.company.business.repositories.TaskRepository;
import com.company.business.models.Task;
import com.company.business.models.TaskStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    public void setup() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    public void testCreateTaskSuccessfully() {
        Task task = new Task("Test Task", "Description", TaskStatus.TODO, LocalDateTime.now());

        when(taskRepository.save(task)).thenReturn(task);

        Task created = taskService.createTask(task);

        assertNotNull(created);
        assertEquals("Test Task", created.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testCreateTaskValidationFail() {
        Task task = new Task("", "Description", null, null); // title empty

        Exception exception = assertThrows(RuntimeException.class, () -> taskService.createTask(task));
        assertTrue(exception.getMessage().contains("Title is required"));
        verify(taskRepository, never()).save(any());
    }

    // Add more unit tests for status validation, dueDate checks, etc.
}
