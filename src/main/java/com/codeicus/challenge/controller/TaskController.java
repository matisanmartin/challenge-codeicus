package com.codeicus.challenge.controller;

import com.codeicus.challenge.dto.TaskDTO;
import com.codeicus.challenge.dto.UpdateTaskDTO;
import com.codeicus.challenge.model.Operation;
import com.codeicus.challenge.model.Task;
import com.codeicus.challenge.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController(value = "/tasks")
public class TaskController extends AbstractController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public Iterable<Task> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable("id") Long id) {
        return taskService.findById(id);
    }

    @PostMapping
    @ResponseBody
    public Task create(@Valid @RequestBody TaskDTO task, BindingResult bindingResult) {
        validateRequest(bindingResult, Operation.CREATE);
        return taskService.create(task);
    }

    @PutMapping
    @ResponseBody
    public Task update(@Valid @RequestBody UpdateTaskDTO task, BindingResult bindingResult) {
        validateRequest(bindingResult, Operation.UPDATE);
        return taskService.update(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        taskService.delete(id);
    }
}
