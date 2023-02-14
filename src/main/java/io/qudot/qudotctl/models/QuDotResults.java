package io.qudot.qudotctl.models;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@RegisterForReflection
public class QuDotResults {
    public enum Status {RUNNING, FINISHED}
    private List<QuDotResult> results;
    private long numResults;
    private String jobId;
    private Status status;

    public QuDotResults() { }
    public QuDotResults(List<QuDotResult> results) {
        this.results = results;
        this.numResults = results.size();
    }

    public List<QuDotResult> getResults() {
        return results;
    }

    public long getNumResults() {
        return numResults;
    }

    public void setResults(List<QuDotResult> results) {
        this.results = results;
    }

    public void setNumResults(long numResults) {
        this.numResults = numResults;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
