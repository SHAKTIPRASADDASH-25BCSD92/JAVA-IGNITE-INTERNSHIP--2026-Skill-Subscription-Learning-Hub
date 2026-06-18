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

    // ─────────────────────────────────────────────────────────
    // GET /packs — Show all skill packs (with optional search)
    // ─────────────────────────────────────────────────────────
    @GetMapping("/packs")
    public String viewPacks(@RequestParam(required = false) String search, Model model) {
        // STEP 1: Check if search keyword is provided
        List<SkillPack> list;

        if (search != null && !search.isBlank()) {
            
            list = packService.searchPacks(search);  
        } else {
           
            list = packService.getAllPacks();
        }

        // STEP 3: Bind the list to the model so packs.jsp can access it
        model.addAttribute("packs", list);

        // STEP 4: Return the logical view name → resolves to /WEB-INF/views/packs.jsp
        return "packs";
    }

    // ─────────────────────────────────────────────────────────
    // GET /add-pack — Show the add-pack form
    // ─────────────────────────────────────────────────────────
    @GetMapping("/add-pack")
    public String showAddPackPage() {
        // STEP 1: Return the logical view name → resolves to /WEB-INF/views/add-pack.jsp
        return "add-pack";
    }

    // ─────────────────────────────────────────────────────────
    // POST /add-pack — Save a new pack, then redirect
    // ─────────────────────────────────────────────────────────
    @PostMapping("/add-pack")
    public String addPack(@ModelAttribute SkillPack pack) {
        // STEP 1: Pass the bound SkillPack object to the service to validate and save
        packService.addSkillPack(pack);

        // STEP 2: Redirect to /packs after saving
        return "redirect:/packs";
    }

    // ─────────────────────────────────────────────────────────
    // GET /delete-pack/{id} — Delete a pack by ID, then redirect
    // ─────────────────────────────────────────────────────────
    @GetMapping("/delete-pack/{id}")
    public String deletePack(@PathVariable Long id) {
        // STEP 1: Pass the path variable id to the service to delete the pack
        packService.deleteSkillPack(id);

        // STEP 2: Redirect back to /packs after deletion
        return "redirect:/packs";
    }

    public SkillPackService getPackService() {
        return packService;
    }
}

