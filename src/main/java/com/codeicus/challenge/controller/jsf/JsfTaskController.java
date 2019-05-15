package com.codeicus.challenge.controller.jsf;

import com.codeicus.challenge.dto.TaskDTO;
import com.codeicus.challenge.dto.UpdateTaskDTO;
import com.codeicus.challenge.model.Task;
import com.codeicus.challenge.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("jsfTaskController")
public class JsfTaskController extends JsfController {

    private static final String INVOKING_ACTION_FIND_ALL = "Invoking action findAll";
    private static final String INVOKING_ACTION_FIND_TASK_BY_ID_WITH_ID = "Invoking action findTaskById with id ";
    private static final String INVOKING_ACTION_UPDATE_TASK = "Invoking action updateTask";
    private static final String INVOKING_ACTION_CREATE_TASK = "Invoking action createTask";
    private static final String INVOKING_ACTION_DELETE_TASK_BY_ID_WITH_ID = "Invoking action deleteTaskById with id ";

    private TaskDTO createTaskDTO = new TaskDTO();
    private UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();
    private Task task;
    private Iterable<Task> tasks;

    @Autowired
    private TaskService taskService;

    public void findAll() {
        accept(p -> {
            tasks = taskService.findAll();
        }, null, INVOKING_ACTION_FIND_ALL);
    }

    public void findTaskById(Long id) {
        accept(p -> {
            task = taskService.findById(p);
            redirect(TASK_XHTML);
        }, id, INVOKING_ACTION_FIND_TASK_BY_ID_WITH_ID + id);
    }

    public void createTask() {
        accept(id -> {
            task = taskService.create(createTaskDTO);
            redirect(TASK_XHTML);
        }, null, INVOKING_ACTION_CREATE_TASK);

    }

    public void updateTask() {
        accept(p -> {
            task = taskService.update(updateTaskDTO);
            redirect(TASK_XHTML);
        }, updateTaskDTO.getId(), INVOKING_ACTION_UPDATE_TASK);
    }

    public void deleteTaskById(Long id) {
        accept(p -> {
            taskService.delete(p);
            redirect(INDEX_XHTML);
        }, id, INVOKING_ACTION_DELETE_TASK_BY_ID_WITH_ID + id);
    }

    public TaskDTO getCreateTaskDTO() {
        return createTaskDTO;
    }

    public void setCreateTaskDTO(TaskDTO createTaskDTO) {
        this.createTaskDTO = createTaskDTO;
    }

    public UpdateTaskDTO getUpdateTaskDTO() {
        return updateTaskDTO;
    }

    public void setUpdateTaskDTO(UpdateTaskDTO updateTaskDTO) {
        this.updateTaskDTO = updateTaskDTO;
    }

    public Iterable<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Iterable<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getText() {
        return "Hello from Spring: " + LocalDateTime.now().toString();
    }
}