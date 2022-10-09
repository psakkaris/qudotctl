package io.qudot.qudotctl.service;

import java.io.IOException;

public interface JobService {
    String generateJobId();

    void compileJob(String qudotFile, String jobId) throws IOException;
}
