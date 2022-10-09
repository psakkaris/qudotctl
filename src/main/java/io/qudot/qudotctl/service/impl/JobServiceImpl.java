package io.qudot.qudotctl.service.impl;

import io.qudot.qudotctl.service.JobService;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class JobServiceImpl implements JobService {

    public String generateJobId() {
        return UUID.randomUUID().toString();
    }
}
