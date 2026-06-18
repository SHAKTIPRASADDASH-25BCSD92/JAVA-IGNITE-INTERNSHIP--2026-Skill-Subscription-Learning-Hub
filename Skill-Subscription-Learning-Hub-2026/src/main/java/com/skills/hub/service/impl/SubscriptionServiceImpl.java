
package com.skills.hub.service.impl;

import com.skills.hub.model.Subscription;
import com.skills.hub.model.User;
import com.skills.hub.model.SkillPack;
import com.skills.hub.repository.SubscriptionRepository;
import com.skills.hub.repository.UserRepository;
import com.skills.hub.repository.SkillPackRepository;
import com.skills.hub.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subRepo;
    private final UserRepository userRepo;
    private final SkillPackRepository packRepo;

    public SubscriptionServiceImpl(SubscriptionRepository subRepo,
                                   UserRepository userRepo,
                                   SkillPackRepository packRepo) {
        this.subRepo = subRepo;
        this.userRepo = userRepo;
        this.packRepo = packRepo;
    }

    @Override
    public Subscription subscribe(Long userId, Long packId) {

        // STEP 1: fetch user by id (via repo/service)
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // STEP 2: fetch skill pack by id
        SkillPack skillPack = packRepo.findById(packId)
                .orElseThrow(() -> new RuntimeException("SkillPack not found with ID: " + packId));

        // STEP 3: create new Subscription object
        Subscription subscription = new Subscription();

        // STEP 4: set user + skill pack
        subscription.setUser(user);
        subscription.setSkillPack(skillPack);

        // STEP 5: set start date = today
        subscription.setStartDate(LocalDate.now());

        // STEP 6: set end date = today + 30 days
        subscription.setEndDate(LocalDate.now().plusDays(30));

        // STEP 7: set status = ACTIVE
        subscription.setStatus("ACTIVE");

        // STEP 8: save subscription
        Subscription savedSubscription = subRepo.save(subscription);

        // STEP 9: return subscription
        return savedSubscription;
    }

    @Override
    public List<Subscription> getUserSubscriptions(Long userId) {

        // STEP 1: fetch user subscriptions from DB
        List<Subscription> subscriptions = subRepo.findByUserId(userId);

        // STEP 2: return list
        return subscriptions;
    }

    public SubscriptionRepository getSubRepo() {
        return subRepo;
    }
}
