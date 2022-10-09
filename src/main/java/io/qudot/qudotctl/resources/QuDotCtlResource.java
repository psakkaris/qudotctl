package io.qudot.qudotctl.resources;

import io.qudot.qudotctl.models.QuDotCtlVersion;
import io.qudot.qudotctl.models.QuDotJob;
import io.qudot.qudotctl.models.QuDotJobRequest;
import io.qudot.qudotctl.service.JobService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/qudotctl")
public class QuDotCtlResource {

    @ConfigProperty(name = "qudotctl.version")
    String version;

    @Inject
    JobService jobService;

    @GET
    @Path("/version")
    public Response getVersion() {
        var qudotctlVersion = new QuDotCtlVersion();
        qudotctlVersion.setVersionHash(version);

        return Response.ok(qudotctlVersion).build();
    }

    @POST
    @Path("/jobs/submit")
    public Response postQuDotJob(QuDotJobRequest jobRequest) {
        String jobId = jobService.generateJobId();

        var qudotJob = new QuDotJob();
        qudotJob.setJobId(jobId);
        return Response.ok(qudotJob).build();
    }
}
