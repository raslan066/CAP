package com.comcast.stringinator.model;

public class StringinatorResult {
    private final String input;
    private final Integer length;
    private final MostOccurredChars mostOccurredChars;
    private boolean isPalindrome;

    public StringinatorResult(String input, Integer length, MostOccurredChars mostOccurredChars) {
        this.input = input;
        this.length = length;
        this.mostOccurredChars = mostOccurredChars;
    }

    public Integer getLength() {
        return length;
    }

    public String getInput() {
        return this.input;
    }

    public MostOccurredChars getMostOccurredChars() {
        return mostOccurredChars;
    }

    public boolean isPalindrome() {
        return isPalindrome;
    }

    public void setPalindrome(boolean palindrome) {
        isPalindrome = palindrome;
    }

}
