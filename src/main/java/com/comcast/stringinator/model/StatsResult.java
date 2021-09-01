package com.comcast.stringinator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class StatsResult {
    private Map<String, Integer> inputs;
    @JsonProperty("most_popular_inputs")
    private Set<String> mostPopular;
    @JsonProperty("longest_input_received")
    private Set<String> longestInputReceived;
    private Set<String> palindromes;

    public StatsResult() {
    }

    public StatsResult(Map<String, Integer> inputs, Set<String> mostPopular,
                       Set<String> longestInputReceived, Set<String> palindromes) {
        this.inputs = inputs;
        this.mostPopular = mostPopular;
        this.longestInputReceived = longestInputReceived;
        this.palindromes = palindromes;
    }

    public Map<String, Integer> getInputs() {
        return inputs;
    }

    public Set<String> getMostPopular() {
        return mostPopular;
    }

    public void setMostPopular(Set<String> mostPopular) {
        this.mostPopular = mostPopular;
    }

    public Set<String> getLongestInputReceived() {
        return longestInputReceived;
    }

    public void setLongestInputReceived(Set<String> longestInputReceived) {
        this.longestInputReceived = longestInputReceived;
    }

    public Set<String> getPalindromes() {
        return palindromes;
    }

    public void setPalindromes(Set<String> palindromes) {
        this.palindromes = palindromes;
    }
}
