package com.codeicus.challenge.service;

import com.codeicus.challenge.dto.TaskDTO;
import com.codeicus.challenge.dto.UpdateTaskDTO;
import com.codeicus.challenge.exception.BusinessException;
import com.codeicus.challenge.exception.ServerException;
import com.codeicus.challenge.model.Operation;
import com.codeicus.challenge.model.Result;
import com.codeicus.challenge.model.Task;
import com.codeicus.challenge.model.TaskLog;
import com.codeicus.challenge.queue.sender.MessageSender;
import com.codeicus.challenge.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.codeicus.challenge.exception.supplier.ExceptionSupplier.notFoundExceptionSupplier;

@Service
public class TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);
    private static final String MSG_UPDATE_OPERATION_SUCCESSFUL = "Update operation successful";
    private static final String MSG_CREATE_OPERATION_SUCCESSFUL = "Create operation successful";
    private static final String ATTEMPTING_TO_UPDATE_ENTITY = "Attempting to update entity {}";
    private static final String ATTEMPTING_TO_CREATE_ENTITY = "Attempting to create entity {}";
    private static final String FINDING_TASK_BY_ID = "Finding task by id {}";
    private static final String SEARCHING_FOR_ALL_THE_TASKS = "Searching for all the tasks";
    private static final String MSG_DELETE_OPERATION_SUCCESSFUL = "Successful delete of task with id=";

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private MessageSender messageSender;

    @Transactional(readOnly = true)
    public Iterable<Task> findAll() {
        LOGGER.info(SEARCHING_FOR_ALL_THE_TASKS);
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Task findById(Long id) {
        LOGGER.info(FINDING_TASK_BY_ID, id);
        return taskRepository.findById(id).orElseThrow(notFoundExceptionSupplier());
    }

    @Transactional
    public Task create(TaskDTO taskDTO) {
        LOGGER.info(ATTEMPTING_TO_CREATE_ENTITY, taskDTO);

        Task created = saveEntity(new Task(taskDTO), Operation.CREATE);
        messageSender.sendTaskLogMessage(new TaskLog(Optional.of(created.getId()), Operation.CREATE, Result.OK, MSG_CREATE_OPERATION_SUCCESSFUL));
        return created;
    }

    @Transactional
    public Task update(UpdateTaskDTO updateTaskDTO) {
        LOGGER.info(ATTEMPTING_TO_UPDATE_ENTITY, updateTaskDTO);

        Task toUpdate = taskRepository.findById(updateTaskDTO.getId()).orElseThrow(notFoundExceptionSupplier());
        toUpdate.update(updateTaskDTO);

        Task saved = saveEntity(toUpdate, Operation.UPDATE);
        messageSender.sendTaskLogMessage(new TaskLog(Optional.of(saved.getId()), Operation.UPDATE, Result.OK, MSG_UPDATE_OPERATION_SUCCESSFUL));
        return saved;
    }

    private Task saveEntity(Task toSave, Operation operation) {
        Optional<Long> taskIdToSaveInLog = Operation.CREATE == operation ? Optional.empty() : Optional.of(toSave.getId());

        try {
            return taskRepository.save(toSave);
        } catch(DataAccessException e) {
            throw new BusinessException(e, new TaskLog(taskIdToSaveInLog, operation, Result.ERROR, e.getMessage()));
        } catch(Exception e) {
            throw new ServerException(e, new TaskLog(taskIdToSaveInLog, operation, Result.ERROR, e.getMessage()));
        }

    }

    @Transactional
    public void delete(Long id) {
        try {
            taskRepository.deleteById(id);
        } catch(DataAccessException e) {
            throw new BusinessException(e, new TaskLog(Optional.empty(), Operation.DELETE, Result.ERROR, e.getMessage()));
        } catch(Exception e) {
            throw new ServerException(e, new TaskLog(Optional.empty(), Operation.DELETE, Result.ERROR, e.getMessage()));
        }
        messageSender.sendTaskLogMessage(new TaskLog(Optional.empty(), Operation.DELETE, Result.OK, MSG_DELETE_OPERATION_SUCCESSFUL + id));
    }
}
