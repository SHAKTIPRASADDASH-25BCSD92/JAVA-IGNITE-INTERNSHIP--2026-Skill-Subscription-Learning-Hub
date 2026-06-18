package com.skills.hub.service;

import com.skills.hub.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    Subscription subscribe(Long userId, Long packId);

    List<Subscription> getUserSubscriptions(Long userId);
}

