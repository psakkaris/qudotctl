package io.qudot.qudotctl.service.impl;

import io.qudot.qudotctl.service.JobService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class JobServiceExecutorImpl implements JobService {
    private static final Logger LOG = Logger.getLogger(JobServiceExecutorImpl.class);
    private static final int CONCURRENT_JOB_MAX = 5;
    @ConfigProperty(name = "QUDOT_HOME")
    String qudotHome;

    ExecutorService jobExecutorService;

    @PostConstruct
    public void init() {
        // maxes out on 5 concurrent job executions
        jobExecutorService = Executors.newFixedThreadPool(CONCURRENT_JOB_MAX);
    }


    @Override
    public String generateJobId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void compileJob(String qudotFile, String jobId) throws IOException {
        String outdir = String.format("/tmp/qudotctl/%s/", jobId);
        var compileCommand = String.format("%s/qudotc %s -o %s", qudotHome, qudotFile, outdir);
        LOG.infof("executing: %s", compileCommand);
        Process process = new ProcessBuilder("/bin/sh", "-c", compileCommand).start();
        try {
            int exitCode = process.waitFor();
            LOG.infof("qudotc exit code: %s", exitCode);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void submitJob(final String jobId) {

        jobExecutorService.execute(() -> {
            try {
                String compiledFiledName = getCompiledFilename(jobId);
                var vmCommand = String.format("%s/qudotvm %s", qudotHome, compiledFiledName);
                LOG.infof("running: %s", vmCommand);
                Process process = new ProcessBuilder("/bin/sh", "-c", vmCommand).start();
                int exitCode = process.waitFor();
                LOG.infof("qudotvm exit code: %s", exitCode);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String getCompiledFilename(String jobId) {
        return String.format("/tmp/qudotctl/%s/%s.qudotc", jobId, jobId);
    }

}
