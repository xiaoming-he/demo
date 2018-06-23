package com.ming.web.controller;

import com.ming.web.dao.SpittleRepository;
import com.ming.web.domain.Spittle;
import com.ming.web.exception.SpittleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private final static String MAX_LONG_AS_STRING = Long.MAX_VALUE + "";

    public final static String CONTROLLER_SEPARATOR = "/";
    public final static String CONTROLLER_URL = CONTROLLER_SEPARATOR + "spittles";
    public final static String REGISTER_CONTROLLER_URL = CONTROLLER_SEPARATOR + "register";

    public final static String VIEW_REGISTER_FORM = "registerForm";
    public final static String VIEW_SPITTLE = "spittle";

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    public SpittleController() {

    }

    @RequestMapping(method = GET)
    public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
                                  @RequestParam(value = "count", defaultValue = "20") int count) {
        return spittleRepository.findSpittles(max, count);
    }

    @RequestMapping(value = "/{id}", method=GET)
    public String spittle(@PathVariable long id, Model model) {
        Spittle spittle = spittleRepository.findOne(id);
        Optional.ofNullable(spittle).orElseThrow(()-> new SpittleNotFoundException("spittle not found"));
        model.addAttribute(VIEW_SPITTLE, spittle);
        return VIEW_SPITTLE;
    }

    @RequestMapping(value = REGISTER_CONTROLLER_URL, method = GET)
    public String showRegistrationForm() {
        return VIEW_REGISTER_FORM;
    }

    @RequestMapping(value = REGISTER_CONTROLLER_URL, method = POST)
    public String processRegistratio(@Valid Spittle spittle, BindingResult errors) {
        spittleRepository.save(spittle);
        return "redirect:/spittles/" + spittle.getUsername();
    }
}
