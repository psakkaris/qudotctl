package com.sakkaris.qudotctl;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine.Command;

@TopCommand
@Command(name = "qudotctl", mixinStandardHelpOptions = true,
        subcommands = {
            QuDotRunCommand.class
})
public class QuDotCtlCommand { }
