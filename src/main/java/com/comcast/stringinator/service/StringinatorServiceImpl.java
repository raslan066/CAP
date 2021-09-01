package com.comcast.stringinator.service;

import java.io.*;
import java.util.*;

import com.comcast.stringinator.model.MostOccurredChars;
import com.comcast.stringinator.model.StatsResult;
import com.comcast.stringinator.model.StringinatorInput;
import com.comcast.stringinator.model.StringinatorResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StringinatorServiceImpl implements StringinatorService {

    private Map<String, Integer> seenStrings;
    private Set<String> mostPopular;
    private Integer mostPopularCount;
    private Set<String> longestInputs;
    private Integer longestInputLength;
    private Set<String> palindromes;
    Logger logger = LoggerFactory.getLogger(StringinatorServiceImpl.class);;


    public StringinatorServiceImpl() {
        StatsResult statsResult = readStatsFromFile();
        seenStrings = statsResult.getInputs();
        mostPopular = statsResult.getMostPopular();
        Iterator<String> iterator = mostPopular.iterator();
        mostPopularCount = iterator.hasNext() ? seenStrings.getOrDefault(iterator.next(),0) : 0;
        longestInputs = statsResult.getLongestInputReceived();
        iterator = longestInputs.iterator();
        longestInputLength = iterator.hasNext() ? iterator.next().length() : 0;
        palindromes = statsResult.getPalindromes();
    }

    @Override
    public StringinatorResult stringinate(StringinatorInput input) {

        String inputTxt = input.getInput();
        logger.info("LOG => *****Input text is " + inputTxt + "******");

        seenStrings.compute(inputTxt, (k, v) -> (v == null) ? 1 : v + 1);
        mostPopularCount = updateList(inputTxt, mostPopularCount, seenStrings.get(inputTxt), mostPopular);
        longestInputLength = updateList(inputTxt, longestInputLength, inputTxt.length(), longestInputs);

        MostOccurredChars mostOccurredChars = this.getMostOccurredChars(inputTxt);
        StringinatorResult result = new StringinatorResult(input.getInput(), input.getInput().length(), mostOccurredChars);
        result.setPalindrome(checkPalindrome(inputTxt));
        return result;
    }

    @Override
    public StatsResult stats()  {
        logger.info("LOG => *****Setting up Stats ******");
        StatsResult resuts =  new StatsResult(seenStrings, mostPopular, longestInputs, palindromes);
        saveStatsToFile(resuts);
        return resuts;
    }

    private MostOccurredChars getMostOccurredChars(String input) {
        logger.info("LOG => *****Setting up MostOccurredChars ******");
        int highest = 0;
        Set<Character> mostChars = new HashSet<>();
        Map<Character, Integer> chars = new HashMap<>();
        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            if (Character.isLetterOrDigit(c)) {
                chars.put(c, chars.getOrDefault(c, 0) + 1);
                int count = chars.get(c);
                highest = updateList(c, highest, count, mostChars);
            }
        }
        return new MostOccurredChars(mostChars, highest);
    }

    private <T> int updateList(T input, int highestValue, int newValue, Set<T> list) {
        if (highestValue < newValue) {
            highestValue = newValue;
            list.clear();
            list.add(input);
        } else if (newValue == highestValue) {
            list.add(input);
        }
        return highestValue;
    }

    private boolean checkPalindrome(String input) {
        logger.info("LOG => *****Checking for Palindrome  ******");
        if (input.equals(new StringBuilder(input).reverse().toString())) {
            palindromes.add(input);
            return true;
        }
        return false;
    }
    private void saveStatsToFile(StatsResult result) {
        logger.info("LOG => ***** Writing to JSON File  ******");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            mapper.writeValue(new File("./Stats.json"),result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StatsResult readStatsFromFile(){
        logger.info("LOG => ***** Loading ORM  ******");
        ObjectMapper mapper = new ObjectMapper();
        StatsResult statsResult = new StatsResult(new HashMap<>(), new HashSet<>(),
                new HashSet<>(), new HashSet<>());
        try {
            statsResult = mapper.readValue(new File("./Stats.json"),StatsResult.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statsResult;
    }


}
