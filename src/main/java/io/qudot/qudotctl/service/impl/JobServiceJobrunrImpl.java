package io.qudot.qudotctl.service.impl;

import io.qudot.qudotctl.service.JobService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.jobrunr.configuration.JobRunr;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.InMemoryStorageProvider;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.UUID;

@ApplicationScoped
public class JobServiceJobrunrImpl implements JobService {
    private static final Logger LOG = Logger.getLogger(JobServiceJobrunrImpl.class);
    @ConfigProperty(name = "QUDOT_HOME")
    String qudotHome;

    JobScheduler jobScheduler;
    @PostConstruct
    public void init() {
        var result = JobRunr.configure()
                .useStorageProvider(new InMemoryStorageProvider())
                .useBackgroundJobServer(2)
                .initialize();

        jobScheduler = result.getJobScheduler();
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

    public void submitJob() {
        BackgroundJob.enqueue(() -> {
            Thread.sleep(20_000);
            System.out.println("Hello from qudotctl!!!");
        });
    }
}
