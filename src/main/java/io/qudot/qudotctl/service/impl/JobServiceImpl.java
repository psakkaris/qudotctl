package io.qudot.qudotctl.service.impl;

import io.qudot.qudotctl.service.JobService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.UUID;

@ApplicationScoped
public class JobServiceImpl implements JobService {
    private static final Logger LOG = Logger.getLogger(JobServiceImpl.class);
    @ConfigProperty(name = "QUDOT_HOME")
    String qudotHome;

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
}
