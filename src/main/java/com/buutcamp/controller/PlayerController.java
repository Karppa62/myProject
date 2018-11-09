package com.buutcamp.controller;

import com.buutcamp.dao.LineDAO;
import com.buutcamp.dao.PlayerDAO;
import com.buutcamp.dao.PlayerStatsDAO;
import com.buutcamp.entity.Line;
import com.buutcamp.entity.Player;
import com.buutcamp.entity.PlayerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/players/")
public class PlayerController {

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private PlayerStatsDAO playerStatsDAO;

    @Autowired
    private LineDAO lineDAO;

    private List<String> positions = new ArrayList<String>();

    @RequestMapping(value="/")
    public String showAllPlayers(Model model) {

        List<Player> players = playerDAO.getPlayers();

        model.addAttribute("player_list", players);
        model.addAttribute("title", "Players");

        return "players_view";
    }

    @GetMapping("/detailsPlayer")
    public String detailsPlayer(@RequestParam("playerId") int id, Model model) {

        Player player = playerDAO.getPlayer(id);

        model.addAttribute("player", player);
        model.addAttribute("title", player.getFirstName() + " " + player.getLastName());

        return "player_details_view";
    }

    @GetMapping("/addPlayer")
    public String addNewPlayerGET(Model model) {

        model.addAttribute("player", new Player());
        model.addAttribute("title", "Add new player");

        getPositionList();

        model.addAttribute("positions", positions);

        List<Integer> jerseyNumbers = new ArrayList<Integer>();
        List<Player> players = playerDAO.getPlayers();
        List<Integer> usedNumbers = new ArrayList<Integer>();
         for (int i = 0; i < players.size(); i++) {
                usedNumbers.add(players.get(i).getJerseyNumber());
        }
        for (int i = 1; i < 100; i++) {
            if (!usedNumbers.contains(i)) {
                jerseyNumbers.add(i);
            }
        }
        model.addAttribute("jerseyNumbers", jerseyNumbers);
        return "addplayer_view";
    }

    @PostMapping("/savePlayer")
    public String savePlayerPOST(@Valid @ModelAttribute("player") Player player,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("playerSaveError", "Name fields can't be empty");
            model.addAttribute("player", player);

            return "redirect:/players/addPlayer";
        }

        playerDAO.savePlayer(player);

        return "redirect:/players/";
    }

    @GetMapping("/updatePlayer")
    public String updatePlayer(@RequestParam("playerId") int id, Model model) {

        Player player = playerDAO.getPlayer(id);
        model.addAttribute("player", player);
        model.addAttribute("title", "Edit player info");

        getPositionList();

        model.addAttribute("positions", positions);

        List<Integer> jerseyNumbers = new ArrayList<Integer>();
        List<Player> players = playerDAO.getPlayers();

        List<Integer> usedNumbers = new ArrayList<Integer>();
        for (int i = 0; i < players.size(); i++) {
            usedNumbers.add(players.get(i).getJerseyNumber());
        }

        for (int i = 1; i < 100; i++) {
            if (!usedNumbers.contains(i) || i == player.getJerseyNumber()) {
                jerseyNumbers.add(i);
            }
        }
        model.addAttribute("jerseyNumbers", jerseyNumbers);

        return "addplayer_view";
    }

    @GetMapping("/editStats")
    public String editStats(@RequestParam("playerId") int id, Model model) {

        Player player = playerDAO.getPlayer(id);

        PlayerStats playerStats = player.getPlayerStats();

        model.addAttribute("player", player);
        model.addAttribute("playerStats", playerStats);
        model.addAttribute("title", "Edit stats - " + player.getFirstName() + " " + player.getLastName());

        return "player_stats_view";
    }

    @PostMapping("/saveStats")
    public String saveStats(@ModelAttribute("playerStats") PlayerStats playerStats, Model model) {

        playerStatsDAO.savePlayerStats(playerStats);

        return "redirect:/players/";
    }

    @GetMapping("/deletePlayer")
    public String deletePlayer(@RequestParam("playerId") int id) {

        playerDAO.deletePlayer(id);
        return "redirect:/players/";
    }

    @GetMapping("/toggleInjured")
    public String toggleInjured(@RequestParam("playerId") int id) {

        PlayerStats playerStats = playerDAO.getPlayer(id).getPlayerStats();
        playerStats.toggleInjured();

        if (playerStats.isInjured()) {
            lineDAO.removePlayerFromRoster(id);
        }

        playerStatsDAO.savePlayerStats(playerStats);

        return "redirect:/players/";
    }

    @PostMapping("/searchPlayers")
    public String searchPlayers(@RequestParam("searchValue") String searchVal,
            @RequestParam("position") String position, Model model) {

        List<Player> players = playerDAO.searchPlayers(searchVal, position);
        model.addAttribute("player_list", players);
        model.addAttribute("title", "Players");

        return "players_view";
    }

    private void getPositionList() {

        if (positions.size() == 0) {
            //positions.add("G");
            positions.add("D");
            positions.add("F");
        }
    }
}
