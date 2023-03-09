package com.global.commtech.test.anagramfinder.transformation;

import com.global.commtech.test.anagramfinder.dto.WordDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnagramGroupingService {
    public List<String> group(List<WordDTO> input) {
        var groupedWords = input.parallelStream()
                .distinct()
                .collect(Collectors.groupingBy(WordDTO::lettersInAlphabeticalOrder))
                .values();

        return groupedWords.parallelStream().map(
                w -> w.stream().map(WordDTO::toString).collect(Collectors.joining(","))
        ).collect(Collectors.toList());
    }
}
