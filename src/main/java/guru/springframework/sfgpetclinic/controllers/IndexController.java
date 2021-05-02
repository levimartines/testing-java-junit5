package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.controllers.exceptions.NotFoundException;

public class IndexController {

    public String index() {
        return "index";
    }

    public String oopsHandler() {
        throw new NotFoundException("Index not found");
    }
}
