package com.skills.hub.service.impl;

import com.skills.hub.model.SkillPack;
import com.skills.hub.repository.SkillPackRepository;
import com.skills.hub.service.SkillPackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillPackServiceImpl implements SkillPackService {

    private final SkillPackRepository packRepo;

    public SkillPackServiceImpl(SkillPackRepository packRepo) {
        this.packRepo = packRepo;
    }

    @Override
    public SkillPack addSkillPack(SkillPack pack) {
        // STEP 1: validate input
        if (pack == null || pack.getName() == null || pack.getName().isBlank()) {
            throw new IllegalArgumentException("SkillPack or its name must not be null or empty");
        }

        // STEP 2: save to DB
        SkillPack savedPack = packRepo.save(pack);

        // STEP 3: return saved object
        return savedPack;
    }

    @Override
    public List<SkillPack> getAllPacks() {
        // STEP 1: fetch all packs from DB
        List<SkillPack> packs = packRepo.findAll();

        // STEP 2: return list
        return packs;
    }

    @Override
    public SkillPack updateSkillPack(SkillPack pack) {
        // STEP 1: find existing pack by ID
        SkillPack existingPack = packRepo.findById(pack.getId()).orElse(null);

        // STEP 2: if not found → return null
        if (existingPack == null) {
            return null;
        }

        // STEP 3: update fields
        existingPack.setName(pack.getName());
        existingPack.setDescription(pack.getDescription());

        // STEP 4: save updated pack
        SkillPack updatedPack = packRepo.save(existingPack);

        // STEP 5: return updated pack
        return updatedPack;
    }

    @Override
    public void deleteSkillPack(Long id) {
        // STEP 1: delete pack by ID
        packRepo.deleteById(id);
    }

    public SkillPackRepository getPackRepo() {
        return packRepo;
    }
}
