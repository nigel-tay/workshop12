package workshop12.workshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import workshop12.workshop.model.Image;
import workshop12.workshop.service.RandomNumService;

@Controller
public class RandomNumberGenController {

    @Autowired
    RandomNumService service;

    @GetMapping("/home")
    public String landingPage() {
        return "home";
    }

    @GetMapping("/get")
    public String generateRandomNumber(Model model, HttpServletRequest request) {

        int number = Integer.parseInt(request.getParameter("number"));
        System.out.println("Given number: " + number);

        if (number < 1 || number > 30) {
            String errorMessageString = "Number given: " + number + " is out of range";
            model.addAttribute("errorMessage", errorMessageString);
            return "home";
        }

        // CALLING SERVICE METHOD
        List<Integer> randomNumbers = service.generateRandomNumber(number);

        // Populate image objects
        List<Image> imageList = new ArrayList<Image>();

        for (int num: randomNumbers) {
            imageList.add(new Image(Integer.toString(num), "/images/" + Integer.toString(num) + ".png"));
        }

        model.addAttribute(imageList);

        return "display";
    }
}
