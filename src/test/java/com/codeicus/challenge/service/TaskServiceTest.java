package com.codeicus.challenge.service;


import com.codeicus.challenge.dto.TaskDTO;
import com.codeicus.challenge.dto.UpdateTaskDTO;
import com.codeicus.challenge.exception.BusinessException;
import com.codeicus.challenge.exception.NotFoundException;
import com.codeicus.challenge.model.Action;
import com.codeicus.challenge.model.Status;
import com.codeicus.challenge.model.Task;
import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    //CREATE
    @Test
    public void testCreatedTaskHasStatusNotRunning() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription("task 1");
        taskDTO.setAction("LOG_INFO");

        Task created = taskService.create(taskDTO);

        assertTrue(created.getId() != null);
        assertTrue(created.getDescription().equals(taskDTO.getDescription()));
        assertTrue(created.getStatus() == Status.NOT_RUNNING);
        assertTrue(created.getAction() == Action.valueOf(taskDTO.getAction()));
        taskService.delete(created.getId());
    }

    @Test(expected = BusinessException.class)
    public void testTaskWithNoDescriptionThrowsExceptionWhenCreating() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setAction("LOG_INFO");

        Task createad = taskService.create(taskDTO);
    }

    //UPDATE
    @Test
    public void testWhenUpdatingCreatedTaskStatusActionAndDescriptionAreUpdated() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription("task 1");
        taskDTO.setAction("LOG_INFO");

        Task created = taskService.create(taskDTO);

        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();
        updateTaskDTO.setId(created.getId());
        updateTaskDTO.setStatus("FINISHED");
        updateTaskDTO.setAction("LOG_DEBUG");
        updateTaskDTO.setDescription("another description");


        Task updated = taskService.update(updateTaskDTO);

        assertTrue(updated.getId().equals(updateTaskDTO.getId()));
        assertTrue(updated.getStatus() == Status.valueOf(updateTaskDTO.getStatus()));
        assertTrue(updated.getAction() == Action.valueOf(updateTaskDTO.getAction()));
        assertTrue(updated.getDescription().equals(updateTaskDTO.getDescription()));

        taskService.delete(updated.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testWhenUpdatingANonExistingTaskExceptionIsThrown() {
        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();
        updateTaskDTO.setId(new Random().nextLong());
        updateTaskDTO.setStatus("FINISHED");
        updateTaskDTO.setAction("LOG_DEBUG");
        updateTaskDTO.setDescription("test");

        Task updated = taskService.update(updateTaskDTO);
    }

    @Test
    public void testACreatedTaskIsFoundWhenSearchingById() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription("task 1");
        taskDTO.setAction("LOG_INFO");

        Task created = taskService.create(taskDTO);

        Task found = taskService.findById(created.getId());

        assertTrue(found.getId().equals(created.getId()));
        taskService.delete(found.getId());
    }

    @Test
    public void testCreate3EntitiesAndFindAllReturnsAllOfThem() {
        TaskDTO taskDTO1 = new TaskDTO();
        taskDTO1.setDescription("task 1");
        taskDTO1.setAction("LOG_INFO");

        TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setDescription("task 2");
        taskDTO2.setAction("LOG_DEBUG");

        TaskDTO taskDTO3 = new TaskDTO();
        taskDTO3.setDescription("task 3");
        taskDTO3.setAction("LOG_ERROR");

        Task created1 = taskService.create(taskDTO1);
        Task created2 = taskService.create(taskDTO2);
        Task created3 = taskService.create(taskDTO3);

        Iterable<Task> all = taskService.findAll();

        Assert.assertEquals(3, Iterables.size(all));
    }

    //DELETE
    @Test(expected = NotFoundException.class)
    public void testADeletedEntityIsNotFound() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription("task 1");
        taskDTO.setAction("LOG_INFO");

        Task created = taskService.create(taskDTO);
        taskService.delete(created.getId());

        Task found = taskService.findById(created.getId());
    }

    @Test(expected = BusinessException.class)
    public void testDeleteAnAlreadyDeletedEntityExceptionIsThrown() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription("task 1");
        taskDTO.setAction("LOG_INFO");

        Task created = taskService.create(taskDTO);
        taskService.delete(created.getId());
        taskService.delete(created.getId());
    }
}