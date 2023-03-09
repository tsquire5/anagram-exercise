package com.global.commtech.test.anagramfinder.input;

import com.global.commtech.test.anagramfinder.dto.WordDTO;
import com.global.commtech.test.anagramfinder.output.AnagramOutputService;
import com.global.commtech.test.anagramfinder.transformation.AnagramGroupingService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
class AnagramFileConsumerIntegrationTest {

    @Test
    public void invalidFileThrowsIOException() {
        var target = new AnagramFileConsumerService();
        var groupingService = getMockGrouping();
        var mockWriter = mock(AnagramOutputService.class);
        assertThrows(IOException.class, () -> {
            target.consume("someIncorrectPath", groupingService, mockWriter);
        });
        verify(groupingService, never()).group(any());
        verify(mockWriter, never()).output(any());
    }

    @Test
    public void emptyFileOutputsNothing() throws IOException {
        var target = new AnagramFileConsumerService();
        var groupingService = getMockGrouping();
        var mockWriter = mock(AnagramOutputService.class);
        target.consume("src/test/resources/empty.txt", groupingService, mockWriter);
        verify(groupingService, times(1)).group(new ArrayList<>());
        verify(mockWriter, never()).output(any());
    }

    @Test
    public void validFileWorks() throws IOException {
        var target = new AnagramFileConsumerService();
        var groupingService = getMockGrouping();
        var mockWriter = mock(AnagramOutputService.class);
        target.consume("src/test/resources/example1.txt", groupingService, mockWriter);
        verify(mockWriter, times(1)).output(List.of("abc", "fun", "bac", "fun", "cba", "unf"));
        verify(mockWriter, times(1)).output(List.of("hello"));
    }

    @Test
    public void validFileWorksWithRealTransformation() throws IOException {
        var target = new AnagramFileConsumerService();
        var groupingService = new AnagramGroupingService();
        var mockWriter = mock(AnagramOutputService.class);
        target.consume("src/test/resources/example1.txt", groupingService, mockWriter);
        verify(mockWriter, times(1)).output(List.of("abc,bac,cba", "fun,unf"));
        verify(mockWriter, times(1)).output(List.of("hello"));
    }

    //Just returns whatever you pass to it
    public AnagramGroupingService getMockGrouping() {
        var mock = mock(AnagramGroupingService.class);
        when(mock.group(any())).thenAnswer(t -> {
            List<WordDTO> argument = (List<WordDTO>) t.getArguments()[0];
            return argument.stream().map(WordDTO::toString).collect(Collectors.toList());
        });
        return mock;
    }
}