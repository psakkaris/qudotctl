package io.qudot.qudotctl.resources;

import io.qudot.qudotctl.models.QuDotCtlVersion;
import io.qudot.qudotctl.models.QuDotJob;
import io.qudot.qudotctl.models.QuDotJobRequest;
import io.qudot.qudotctl.models.QuDotResults;
import io.qudot.qudotctl.service.JobService;
import org.apache.commons.io.IOUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Path("/qudotctl/v1")
public class QuDotCtlResource {
    private static final Logger LOG = Logger.getLogger(QuDotCtlResource.class);

    @ConfigProperty(name = "qudotctl.version")
    String version;
    @ConfigProperty(name = "qudotctl.job.workdir")
    String jobWorkDir;

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
        try {
            String jobId = jobService.generateJobId();
            String jobPath = getJobPath(jobId);
            Files.createDirectories(Paths.get(jobPath));
            String filename = getProgramFilename(jobPath, jobId);

            LOG.infof("Writing program to job directory: %s", filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            byte[] programBytes = Base64.getDecoder().decode(jobRequest.getQuantumProgramBody());
            IOUtils.write(new String(programBytes), writer);
            writer.close();

            LOG.infof("Compiling job: %s, %s", jobId, filename);
            jobService.compileJob(filename, jobId);
            var qudotJob = new QuDotJob();
            qudotJob.setJobId(jobId);

            LOG.infof("Starting job: %s", jobId);
            jobService.submitJob(jobId);
            return Response.ok(qudotJob).build();
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException(e.getMessage());
        }
    }

    @GET
    @Path("/jobs/{jobId}")
    public Response getJob(@PathParam("jobId") String jobId) {
        java.nio.file.Path jobPath = java.nio.file.Path.of(getJobPath(jobId));
        if (!Files.exists(jobPath) || !Files.isDirectory(jobPath)) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "no such job").build();
        }
        QuDotResults results = jobService.getResults(jobId);
        return Response.ok(results).build();
    }


    String getJobPath(String jobId) {
        return String.format("%s/qudotctl/%s", jobWorkDir, jobId);
    }

    String getProgramFilename(String baseDir, String jobId) {
        return String.format("%s/%s.qudot", baseDir, jobId);
    }
}
