package com.global.commtech.test.anagramfinder;

import java.io.File;

import com.global.commtech.test.anagramfinder.input.AnagramFileConsumerService;
import com.global.commtech.test.anagramfinder.output.AnagramOutputService;
import com.global.commtech.test.anagramfinder.transformation.AnagramGroupingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class AnagramCommandLineRunner implements CommandLineRunner {

    private AnagramFileConsumerService anagramFileConsumerService;

    private AnagramGroupingService anagramGroupingService;

    private AnagramOutputService anagramFileWriter;

    @Autowired
    public AnagramCommandLineRunner(AnagramFileConsumerService anagramFileConsumerService, AnagramGroupingService anagramGroupingService, AnagramOutputService anagramFileWriter) {
        this.anagramFileConsumerService = anagramFileConsumerService;
        this.anagramGroupingService = anagramGroupingService;
        this.anagramFileWriter = anagramFileWriter;
    }

    @Override
    public void run(final String... args) throws Exception {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");
        var filePath = args[0];
        final File file = new File(filePath);
        Assert.isTrue(file.exists(), filePath + " Does not exist");

        anagramFileConsumerService.consume(filePath, anagramGroupingService, anagramFileWriter);
    }
}
