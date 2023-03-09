package com.global.commtech.test.anagramfinder.output;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnagramOutputService {
    public void output(List<String> words) {
        words.forEach(System.out::println);
    }
}
