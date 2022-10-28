package com.pawel.threadtask.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pawel.threadtask.enums.MyThread;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<MyThread, Long>, JpaSpecificationExecutor<MyThread> {
    List<MyThread> findByStatus(String running);
}
