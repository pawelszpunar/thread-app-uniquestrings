package com.pawel.threadtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.pawel.threadtask.repository.ThreadRepository;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import com.pawel.threadtask.enums.InputData;
import com.pawel.threadtask.enums.MyThread;

import java.util.List;
import java.util.Optional;

@Service
public class MyThreadService {

    @Qualifier("taskExecutor")
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ThreadRepository threadRepository;

    public MyThread startNewThread(MyThread myThread) {
        taskExecutor.execute(myThread);
        return myThread;
    }

    public void verifyInput(InputData inputData) throws Exception {
        if(inputData.getMax() < 0 ) {
            throw new Exception("max value must be higher than zero");
        }

        if(inputData.getMin() < 0 ) {
            throw new Exception("min value must be higher than zero");
        }
        if(inputData.getMax() < inputData.getMin()) {
            throw new Exception("min value can't be higher than max value");
        }

        int max = inputData.getMax();
        int min = inputData.getMin();
        int noOfStrings = inputData.getCharacters().length();
        int maxStrings = 0;
        for(int i=min; i <= max; i++) {
            maxStrings = (int) (maxStrings + Math.pow(noOfStrings, i));
        }
        if(inputData.getHowMuchStrings() > maxStrings) {
            throw new Exception("howMuchStrings is too high");
        }
    }

    public MyThread save(MyThread myThread) {
        return threadRepository.save(myThread);
    }

    public List<MyThread> getAllThreads() {
        return threadRepository.findAll();
    }

    public int countRunningThreads() {
        List<MyThread> list = threadRepository.findByStatus("running");
        return list.size();
    }

    public int countEndedThreads() {
        List<MyThread> list = threadRepository.findByStatus("ended");
        return list.size();
    }

    public Optional<MyThread> getById(long id) throws Exception {
        try {
            return threadRepository.findById(id);
        } catch(Exception e) {
            throw new Exception("can't find thread with this id");
        }
    }
}
