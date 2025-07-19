package mrito.api.controller;

/*
 * Author: mrito - bv3044033
 * Primeiro end-point Spring boot
 */
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {
    @RequestMapping(value="/hellower", method = RequestMethod.GET)
    public String hello() {
        return "HELLO MUNDO !!!";
    }

}
