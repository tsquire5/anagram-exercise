# Anagram Finder
A simple command line utility for finding anagrams in a specified file

## Software required to run this
* Java 17

## Building and Running the tests
```
./gradlew clean build
```

## Running the program
```
./gradlew bootRun --args="example2.txt" 
```
where example2.txt is the text file that we want to search for anagrams

## Big O analysis

Reading the file is done with a scanner so should be O(n) (Linear) with file size. Time taken to find anagrams is proportionate
to the number of words per size of word and will increase linearly with file size. 

## Reasons behind data structures chosen

WordDTO encapsulated the logic of comparing words and checking if they have the same letters (ie an anagram). 

I have used lists as the primary datastructure in the project. They are flexible, readable and allow for use of the steams api. 
Some performance may be gained by switching to arrays, however in modern java the compiler does a lot of optimisations and 
improvement is likely to be minimal if any. 

## What would you do given more time

I would use JMeter to do some analysis of where the program is taking most time, especially with large files. I would also 
use it to measure the difference between using steam and parallelStream. I think parallelStream will increase performance
with large word groupings but its worth measuring. 

Using this insight I might also write an integration tests for non-functional requirements (ie speed). I would have the program
 generates or uses a large file and ensure the correct result is returned in a reasonable time. 

I could also use something like spark to read/transform the files. This has the potential to be much faster for large 
datasets but seemed overkill for this task. 

