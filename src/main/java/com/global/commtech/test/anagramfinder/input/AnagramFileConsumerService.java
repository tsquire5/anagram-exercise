package com.global.commtech.test.anagramfinder.input;

import com.global.commtech.test.anagramfinder.dto.WordDTO;
import com.global.commtech.test.anagramfinder.output.AnagramOutputService;
import com.global.commtech.test.anagramfinder.transformation.AnagramGroupingService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class AnagramFileConsumerService {
    public void consume(String filename, AnagramGroupingService anagramGroupingService, AnagramOutputService anagramOutputService) throws IOException {
        try (var inputStream = new FileInputStream(filename);
             var scanner = new Scanner(inputStream, Charset.defaultCharset())) {
            var previousWordSize = 1;
            var wordsOfSameSize = new ArrayList<WordDTO>();
            while (scanner.hasNext()) {
                var word = scanner.next();
                if (word.length() != previousWordSize) {
                    transformAndOutputWords(anagramGroupingService, anagramOutputService, wordsOfSameSize);
                    wordsOfSameSize = new ArrayList<>();
                }
                wordsOfSameSize.add(new WordDTO(word));
                previousWordSize = word.length();
            }
            transformAndOutputWords(anagramGroupingService, anagramOutputService, wordsOfSameSize);
        }
    }

    private void transformAndOutputWords(AnagramGroupingService anagramGroupingService, AnagramOutputService writer, ArrayList<WordDTO> wordsOfSameSize) {
        var transformed = anagramGroupingService.group(wordsOfSameSize);
        if (!transformed.isEmpty()) {
            writer.output(transformed);
        }
    }

}
