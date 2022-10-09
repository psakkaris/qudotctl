package io.qudot.qudotctl.models;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class QuDotJob {
    private String jobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
