package com.orangeman.example.batch.jobrunner;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.orangeman.example.batch.utils.Constants;;

@Component
public class JobRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(JobRunner.class);

	private JobLauncher simpleJobLauncher;
	private Job demo1;

	@Autowired
	public JobRunner(JobLauncher simpleJobLauncher, Job demo1) {
		this.simpleJobLauncher = simpleJobLauncher;
		this.demo1 = demo1;
	}

	@Async
	public void runBatchJob() {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addString(Constants.FILE_NAME_CONTEXT_KEY, "employees.csv");
		jobParametersBuilder.addDate("date", new Date(), true);
		runJob(demo1, jobParametersBuilder.toJobParameters());		
	}

	private void runJob(Job job, JobParameters parameters) {
		// TODO Auto-generated method stub
		try {
			simpleJobLauncher.run(job, parameters);
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			logger.info("Job with fileName={} is already running.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			logger.info("Job with fileName={} wat not restarted.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			logger.info("Job with fileName={} already completed.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			logger.info("Invalid job parameters.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
		}
	}
}
