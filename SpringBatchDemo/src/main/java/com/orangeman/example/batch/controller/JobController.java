package com.orangeman.example.batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangeman.example.batch.jobrunner.JobRunner;

@RestController
@RequestMapping(value = "/run")
public class JobController {

	private JobRunner jobRunner;

    @Autowired
    public JobController(JobRunner jobRunner) {
        this.jobRunner = jobRunner;
    }
    
    
	@RequestMapping("/job")
	public String jobRunner() {
		jobRunner.runBatchJob();
		return String.format("Job CSV2DB submitted successfully.");
	}
	
	
}
