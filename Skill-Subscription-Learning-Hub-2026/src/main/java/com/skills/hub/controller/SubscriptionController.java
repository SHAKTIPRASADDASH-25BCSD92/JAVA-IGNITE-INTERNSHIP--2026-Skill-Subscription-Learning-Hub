package com.skills.hub.controller;

import com.skills.hub.service.SubscriptionService;
import com.skills.hub.model.Subscription;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
=========================================================
WHAT IS THIS FILE?
Handles subscription between user and skill pack
=========================================================
*/
@Controller
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/subscribe")
    public String subscribe(@RequestParam Long userId,
                            @RequestParam Long packId) {
        subscriptionService.subscribe(userId, packId);
        return "redirect:/subscriptions/" + userId;
    }

    @GetMapping("/subscriptions/{userId}")
    public String viewSubscriptions(@PathVariable Long userId, Model model) {
        List<Subscription> list = subscriptionService.getUserSubscriptions(userId);
        model.addAttribute("subs", list);
        return "subscriptions";
    }

    public SubscriptionService getSubscriptionService() {
        return subscriptionService;
    }
}
