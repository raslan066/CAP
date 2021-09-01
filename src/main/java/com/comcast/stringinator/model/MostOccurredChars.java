package com.comcast.stringinator.model;

import java.util.Set;

public class MostOccurredChars {
    Set<Character> chars;
    int count;
    int palindromeText;

    public MostOccurredChars(Set<Character> chars, int count) {
        this.chars = chars;
        this.count = count;
    }

    public Set<Character> getChars() {
        return chars;
    }

    public void setChars(Set<Character> chars) {
        this.chars = chars;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
