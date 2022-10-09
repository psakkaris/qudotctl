package io.qudot.qudotctl;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuDotCtlRunCommandTest {

//    @Test
//    public void testToQuDotResult() {
//        final QuDotRunCommand runCommand = new QuDotRunCommand();
//        var result = runCommand.toQuDotResult("010,277,.3");
//        assertEquals(result.getState(), "010");
//        assertEquals(result.getFrequency(),277);
//        assertEquals(result.getPercentage(), 0.3);
//
//       assertThrows(RuntimeException.class, () -> {
//           runCommand.toQuDotResult("010,0");
//       });
//    }
//
//    @Test
//    public void testToQuDotResults() throws IOException {
//        final QuDotRunCommand runCommand = new QuDotRunCommand();
//        try (InputStream in = getClass().getResourceAsStream("/quantumman01/quantumman01.out")) {
//            var results = runCommand.toQuDotResults("quantumman01", in);
//            assertEquals(2, results.getNumResults());
//            assertEquals("quantumman01", results.getJobId());
//            var sampleResult = results.getResults().get(0);
//            // 11,500842,0.500842
//            assertEquals("11", sampleResult.getState());
//            assertEquals(500842, sampleResult.getFrequency());
//            assertEquals(.500842, sampleResult.getPercentage());
//        }
//
//        try (InputStream in = getClass().getResourceAsStream("/feynman77/feynman77.out")) {
//            var results = runCommand.toQuDotResults("feynman77", in);
//            assertEquals(11, results.getNumResults());
//            assertEquals("feynman77", results.getJobId());
//            var sampleResult = results.getResults().get(6);
//            // 1101,38250,0.03825
//            assertEquals("1101", sampleResult.getState());
//            assertEquals(38250, sampleResult.getFrequency());
//            assertEquals(.03825, sampleResult.getPercentage());
//        }
//    }
}
