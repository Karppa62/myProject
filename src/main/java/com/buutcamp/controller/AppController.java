package com.buutcamp.controller;

import com.buutcamp.dao.LineDAO;
import com.buutcamp.dao.PlayerDAO;
import com.buutcamp.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private LineDAO lineDAO;

    // For testing. Adds data to tables if they are empty
    @PostConstruct
    private void init() {
        playerDAO.addDumbDataPlayers();
        lineDAO.createLines();
    }

    @GetMapping(value = "/")
    public String indexPageGET(Model model) {

        return "redirect:/showLoginPage";
    }


}
