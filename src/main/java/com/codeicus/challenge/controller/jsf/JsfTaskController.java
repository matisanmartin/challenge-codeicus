package com.codeicus.challenge.controller.jsf;

import com.codeicus.challenge.dto.TaskDTO;
import com.codeicus.challenge.dto.UpdateTaskDTO;
import com.codeicus.challenge.model.Task;
import com.codeicus.challenge.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("jsfTaskController")
@Scope("session")
public class JsfTaskController extends JsfController {

    private static final String INVOKING_ACTION_FIND_ALL = "Invoking action findAll";
    private static final String INVOKING_ACTION_FIND_TASK_BY_ID_WITH_ID = "Invoking action findTaskById with idSearch ";
    private static final String INVOKING_ACTION_UPDATE_TASK = "Invoking action updateTask";
    private static final String INVOKING_ACTION_CREATE_TASK = "Invoking action createTask";
    private static final String INVOKING_ACTION_DELETE_TASK_BY_ID_WITH_ID = "Invoking action deleteTaskById with idSearch ";

    private TaskDTO createTaskDTO = new TaskDTO();
    private UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();
    private Task task;
    private Iterable<Task> tasks;
    private Long idSearch;
    private Long idDelete;

    @Autowired
    private TaskService taskService;

    public void findAll() {
        accept(p -> tasks = taskService.findAll(), null, INVOKING_ACTION_FIND_ALL);
    }

    public void findTaskById() {
        accept(p -> updateTaskDTO = new UpdateTaskDTO(taskService.findById(p)), idSearch, INVOKING_ACTION_FIND_TASK_BY_ID_WITH_ID + idSearch);
    }

    public void createTask() {
        accept(id -> task = taskService.create(createTaskDTO), null, INVOKING_ACTION_CREATE_TASK);

    }

    public void updateTask() {
        accept(p -> task = taskService.update(updateTaskDTO), updateTaskDTO.getId(), INVOKING_ACTION_UPDATE_TASK);
    }

    public void deleteTaskById() {
        accept(p -> taskService.delete(p), idDelete, INVOKING_ACTION_DELETE_TASK_BY_ID_WITH_ID + idDelete);
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

    public Long getIdSearch() {
        return idSearch;
    }

    public void setIdSearch(Long idSearch) {
        this.idSearch = idSearch;
    }

    public Long getIdDelete() {
        return idDelete;
    }

    public void setIdDelete(Long idDelete) {
        this.idDelete = idDelete;
    }
}