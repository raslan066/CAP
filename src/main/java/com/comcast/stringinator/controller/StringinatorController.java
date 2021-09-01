package com.comcast.stringinator.controller;

import com.comcast.stringinator.model.StatsResult;
import com.comcast.stringinator.model.StringinatorInput;
import com.comcast.stringinator.model.StringinatorResult;
import com.comcast.stringinator.service.StringinatorService;

import org.hibernate.mapping.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
class StringinatorControllerHtlm {
    @GetMapping("/")
    String home(Model model) {

        List<String> apis = Arrays.asList(new String[]{
                "GET / - You're already here!",
                "POST /stringinate - Get all of the info you've ever wanted about a string. Takes JSON of the following form: {\"input\":\"your-string-goes-here\"}\"",
                "GET /stats - Get statistics about all strings the server has seen, including the longest and most popular strings."
        });
        model.addAttribute("apis",apis);
        return "home";
    }
}

@RestController
public class StringinatorController {

    @Autowired
    private StringinatorService stringinatorService;

//    Logger logger = LoggerFactory.getLogger(StringinatorController.class);

    @GetMapping("/api")
	public String index() {
		return "<pre>\n" +
		"Welcome to the Stringinator 3000 for all of your string manipulation needs.\n" +
		"GET / - You're already here! \n" +
		"POST /stringinate - Get all of the info you've ever wanted about a string. Takes JSON of the following form: {\"input\":\"your-string-goes-here\"}\n" +
		"GET /stats - Get statistics about all strings the server has seen, including the longest and most popular strings.\n" +
		"</pre>";
	}

    @GetMapping(path = "/stringinate", produces = "application/json")
    public StringinatorResult stringinateGet(@RequestParam(name = "input", required = true) String input) {
        StringinatorResult result = stringinatorService.stringinate(new StringinatorInput(input));
        return result;
    }

	@PostMapping(path = "/stringinate", consumes = "application/json", produces = "application/json")
    public StringinatorResult stringinate(@RequestBody StringinatorInput input) {
        StringinatorResult result = stringinatorService.stringinate(input);
        return result;
    }

    @GetMapping(path = "/stats")
    public StatsResult stats() {
        StatsResult result = stringinatorService.stats();
        return result;
    }
}
