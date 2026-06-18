
package com.skills.hub.controller;


import com.skills.hub.model.SkillPack;
import com.skills.hub.service.SkillPackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
=========================================================
WHAT IS THIS FILE?
Handles skill pack (course) operations
=========================================================
*/
@Controller
public class SkillPackController {

    private final SkillPackService packService;

    public SkillPackController(SkillPackService packService) {
        this.packService = packService;
    }

   
    @GetMapping("/packs")
    public String viewPacks(@RequestParam(required = false) String search, Model model) {
        
        List<SkillPack> list;

        if (search != null && !search.isBlank()) {
            
            list = packService.searchPacks(search);  
        } else {
           
            list = packService.getAllPacks();
        }
        model.addAttribute("packs", list);
         return "packs";
    }
 @GetMapping("/add-pack")
    public String showAddPackPage() {
       
        return "add-pack";
    }

   
    @PostMapping("/add-pack")
    public String addPack(@ModelAttribute SkillPack pack) {
       
        packService.addSkillPack(pack);

        
        return "redirect:/packs";
    }

    @GetMapping("/delete-pack/{id}")
    public String deletePack(@PathVariable Long id) {
       
        packService.deleteSkillPack(id);

        
        return "redirect:/packs";
    }

    public SkillPackService getPackService() {
        return packService;
    }
}



