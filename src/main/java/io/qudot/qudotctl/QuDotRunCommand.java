package io.qudot.qudotctl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.qudot.qudotctl.models.QuDotResult;
import io.qudot.qudotctl.models.QuDotResults;
import org.apache.commons.io.IOUtils;
//import picocli.CommandLine;

import javax.enterprise.context.Dependent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//@Dependent
//@CommandLine.Command(name = "run")
public class QuDotRunCommand {
//    static final String qudotFilePattern = "%s/%s.%s";
//    static final String QUDOT_LANGUAGE_EXT = "qudot";
//    static final String QUDOT_BYTCODE_EXT = "qudotc";
//    static final String QUDOT_OUTFILE_EXT = "out";
//
////    @CommandLine.Command(name = "bytecode")
//    public void runBytecode(@CommandLine.Option(names = {"-j", "--job-id"}, defaultValue = "quantumman01", description = "optional job id") String jobId) throws IOException, InterruptedException {
//        String qudotBytecode = String.join("\n", IOUtils.readLines(System.in, StandardCharsets.UTF_8));
//
//        Files.createDirectories(Paths.get("./" + jobId));
//        String filename = String.format(qudotFilePattern, jobId, jobId, QUDOT_LANGUAGE_EXT);
//
//        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
//        IOUtils.write(qudotBytecode, writer);
//        writer.close();
//
//        int exitCode = compileAndExecuteQuDot(jobId, filename);
//        var qudotResults = toQuDotResults(jobId);
//        var mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//
//        System.out.println(mapper.writeValueAsString(qudotResults));
//    }
//
//    /**
//     * Compiles a qudot program into bytecode using to qudot compiler qudotc and runs the bytecode on the qudotvm
//     * to obtain results. The qudotvm places the results in a .out file by default
//     *
//     * @param jobId the job id
//     * @param filename  file name of the qudot program file
//     * @return an exit code
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    int compileAndExecuteQuDot(String jobId, String filename) throws IOException, InterruptedException {
//        String compiledFiledName = String.format(qudotFilePattern, jobId, jobId, QUDOT_BYTCODE_EXT);
//        // /bin/sh -c "./qudotc tmpjob01/bell.qudot -o tmpjob01/"
//        var compileCommand = String.format("./qudotc %s -o %s/", filename, jobId);
//        // /bin/sh -c "./qudotvm tmpjob01/bell.qudotc"
//        var vmCommand = String.format("./qudotvm %s", compiledFiledName);
//        String command = String.join(";", compileCommand, vmCommand);
//        //System.out.println("Running: " + command);
//        Process process = new ProcessBuilder("/bin/sh", "-c", command).start();
//
//        return process.waitFor();
//    }
//
//    /**
//     * Reads the QuDotVM output file results and generates a QuDotResults
//     * @param jobId the jobId that generated the results
//     * @return QuDotResults
//     * @throws IOException when file doesn't exist
//     */
//    QuDotResults toQuDotResults(String jobId) throws IOException {
//        String outFilename = String.format(qudotFilePattern, jobId, jobId, QUDOT_OUTFILE_EXT);
//        FileInputStream fis = new FileInputStream(outFilename);
//
//        return toQuDotResults(jobId, fis);
//    }
//
//    /**
//     * Parses QuDotVM output file given an inputstream
//     * @param jobId the jobId
//     * @param inputStream the file to qudotvm output results
//     * @return QuDotResults
//     * @throws IOException on file errors
//     */
//    QuDotResults toQuDotResults(String jobId, InputStream inputStream) throws IOException {
//        var outputLines = IOUtils.readLines(inputStream, StandardCharsets.UTF_8);
//        List<QuDotResult> resultItems = new ArrayList<>();
//        for (int i=1; i < outputLines.size(); i++) {
//            resultItems.add(toQuDotResult(outputLines.get(i)));
//        }
//        var results = new QuDotResults(resultItems);
//        results.setJobId(jobId);
//
//        return results;
//    }
//
//    /**
//     * Convert a csv line from the QuDotVM output file into a QuDotResult.
//     * We assume a single line is of the form:
//     * 11,500842,0.500842
//     *
//     * @param outputLine a single line of csv output
//     * @return QuDotResult
//     */
//    QuDotResult toQuDotResult(String outputLine) {
//        var fields = outputLine.split(",");
//        if (fields.length == 3) {
//            return new QuDotResult(fields[0], Long.parseLong(fields[1]), Double.parseDouble(fields[2]));
//        } else {
//            throw new RuntimeException("Unexpected output format line: " + outputLine);
//        }
//    }
}
