package io.qudot.qudotctl.models;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class QuDotJobRequest {
    private String jobName;
    private QuantumLanguage quantumLanguage;
    // base64 encoding of a quantum program file
    private String quantumProgramBody;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public QuantumLanguage getQuantumLanguage() {
        return quantumLanguage;
    }

    public void setQuantumLanguage(QuantumLanguage quantumLanguage) {
        this.quantumLanguage = quantumLanguage;
    }

    public String getQuantumProgramBody() {
        return quantumProgramBody;
    }

    public void setQuantumProgramBody(String quantumProgramBody) {
        this.quantumProgramBody = quantumProgramBody;
    }
}
