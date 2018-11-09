package com.buutcamp.controller;

import com.buutcamp.dao.LineDAO;
import com.buutcamp.dao.PlayerDAO;
import com.buutcamp.entity.Line;
import com.buutcamp.entity.Player;
import com.buutcamp.model.LinePlayers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/roster/")
public class RosterController {

    @Autowired
    private LineDAO lineDAO;

    @Autowired
    private PlayerDAO playerDAO;

    @RequestMapping(value="/")
    public String viewRoster(Model model) {

        model.addAttribute("lines", populateLinePlayersList());
        model.addAttribute("title", "Current roster");

        return "roster_view";
    }

    @GetMapping("/editLine")
    public String editLine(@RequestParam("lineId") int id, Model model) {

        Line line = lineDAO.getLine(id);

        model.addAttribute("current_lines", populateLinePlayersList());
        model.addAttribute("line", line);

        List<Player> players;
        players = playerDAO.getEligiblePlayersByPosition("D");
        model.addAttribute("defenders", players);

        players = playerDAO.getEligiblePlayersByPosition("F");
        model.addAttribute("forwards", players);

        model.addAttribute("title", "Edit " + line.getLineName());

        return "edit_roster_view";
    }

    @PostMapping("/saveLine")
    public String  saveLine(@ModelAttribute("line") Line line) {

        lineDAO.saveLine(line);

        return "redirect:/roster/";
    }

    private List<LinePlayers> populateLinePlayersList() {

        LinePlayers linePlayers;
        List<LinePlayers> lines = new ArrayList<LinePlayers>();
        List<Line> lines_list = lineDAO.getLines();

        int i = 1;
        for (Line line : lines_list) {
            linePlayers = new LinePlayers(
                    i,
                    line.getLineName(),
                    playerDAO.getPlayerName(line.getLD()),playerDAO.getPlayerName(line.getRD()),
                    playerDAO.getPlayerName(line.getLW()),playerDAO.getPlayerName(line.getRW()),
                    playerDAO.getPlayerName(line.getC()));
            lines.add(linePlayers);
            i++;
        }
        return lines;
    }
}
