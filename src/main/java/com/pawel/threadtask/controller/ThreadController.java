package com.pawel.threadtask.controller;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.pawel.threadtask.service.MyThreadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.pawel.threadtask.enums.InputData;
import com.pawel.threadtask.enums.MyThread;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
public class ThreadController {

    @Autowired
    private MyThreadService myThreadSerice;

    @PostMapping("/tasks")
    public ResponseEntity<String> startNewThread(@RequestBody InputData inputData) throws Exception {
        myThreadSerice.verifyInput(inputData);
        MyThread myThread = new MyThread(inputData.getMin(), inputData.getMax(), inputData.getHowMuchStrings(), inputData.getCharacters());
        myThreadSerice.startNewThread(myThread);
        myThreadSerice.save(myThread);

        JSONObject resp = new JSONObject();
        resp.put("status", myThread.getStatus());
        resp.put("id", myThread.getId());
        return new ResponseEntity<>(resp.toString(), HttpStatus.CREATED);
    }

    // Get All Threads with ID
    @GetMapping("/tasks")
    public ResponseEntity<List<MyThread>> getAllThreads() {
        return new ResponseEntity(myThreadSerice.getAllThreads(), HttpStatus.OK);
    }

    @GetMapping("/tasks/summary")
    public ResponseEntity<String> getSummaryThreads() throws JSONException {
        int runningThreads = myThreadSerice.countRunningThreads();
        int endedThreads = myThreadSerice.countEndedThreads();

        JSONObject resp = new JSONObject();
        resp.put("threads running", runningThreads);
        resp.put("threads ended", endedThreads);
        return new ResponseEntity<>(resp.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Thread> getThreadById(@PathVariable("id") long id) throws Exception {
        return new ResponseEntity(myThreadSerice.getById(id), HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadErrorData(@PathVariable("id") long id) throws Exception {
        return myThreadSerice.createFile(id);
    }

}
