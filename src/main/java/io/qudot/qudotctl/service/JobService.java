package io.qudot.qudotctl.service;

import io.qudot.qudotctl.models.QuDotResults;

import java.io.IOException;

public interface JobService {
    String generateJobId();

    void compileJob(String qudotFile, String jobId) throws IOException;

    void submitJob(String jobId);

    QuDotResults getResults(String jobId);
}
