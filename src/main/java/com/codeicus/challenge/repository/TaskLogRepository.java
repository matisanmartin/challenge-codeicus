package com.codeicus.challenge.repository;

import com.codeicus.challenge.model.TaskLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskLogRepository extends CrudRepository<TaskLog, Long> {
}
