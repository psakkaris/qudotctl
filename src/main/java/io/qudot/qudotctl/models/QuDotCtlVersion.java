package io.qudot.qudotctl.models;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class QuDotCtlVersion {
    private String versionHash;

    public String getVersionHash() {
        return versionHash;
    }

    public void setVersionHash(String versionHash) {
        this.versionHash = versionHash;
    }
}
