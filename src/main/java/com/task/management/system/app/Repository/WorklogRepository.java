package com.task.management.system.app.Repository;

import com.task.management.system.app.model.Worklogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorklogRepository  extends JpaRepository<Worklogs,Integer> {

    /*@Modifying
    @Query("DELETE Worklogs w WHERE w.task.id = ?1")
    void deleteByTaskId(int id);*/
}
