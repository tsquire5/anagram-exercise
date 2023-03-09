package com.global.commtech.test.anagramfinder.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WordDTO{
    private final String characters;
    public WordDTO(String characters){
        this.characters = characters;
    }

    public String lettersInAlphabeticalOrder(){
        var chars = characters.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static List<WordDTO> listOf(String... inputs){
        return Arrays.stream(inputs).map(WordDTO::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return characters;
    }

    @Override
    public int hashCode() {
        return characters.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final WordDTO other = (WordDTO) obj;
            return Objects.equals(other.toString(), characters);
    }
}
