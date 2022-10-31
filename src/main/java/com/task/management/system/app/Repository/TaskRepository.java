package com.task.management.system.app.Repository;

import com.task.management.system.app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    /*@Modifying
    @Query("DELETE Worklogs w WHERE w.worklog.id = ?1")
    void deleteByWorklogsId(int id);*/
}
