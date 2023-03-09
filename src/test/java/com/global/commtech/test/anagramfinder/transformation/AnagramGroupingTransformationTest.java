package com.global.commtech.test.anagramfinder.transformation;

import com.global.commtech.test.anagramfinder.dto.WordDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnagramGroupingTransformationTest {

    @Test
    public void emptyListReturnsEmptyListWhenTransformed() {
        var target = new AnagramGroupingService();
        var result = target.group(new ArrayList<>());
        assertTrue(result.isEmpty());
    }

    @Test
    public void singleElementListJustReturnsSingleElementList() {
        var target = new AnagramGroupingService();
        var result = target.group(WordDTO.listOf("abc"));
        assertEquals(1, result.size());
    }

    @Test
    public void duplicatesRemoved() {
        var target = new AnagramGroupingService();
        var result = target.group(WordDTO.listOf("abc", "abc"));
        assertEquals(List.of("abc"), result);
    }

    @Test
    public void anagramsGrouped() {
        var target = new AnagramGroupingService();
        var result = target.group(WordDTO.listOf("abc", "bac"));
        assertEquals(List.of("abc, bac"), result);
    }

    @Test
    public void complexExampleWorks() {
        var target = new AnagramGroupingService();
        var result = target.group(WordDTO.listOf("abc", "fun", "bac", "fun", "cba", "unf"));
        assertEquals(List.of("abc, bac, cba", "fun, unf"), result);
    }
}