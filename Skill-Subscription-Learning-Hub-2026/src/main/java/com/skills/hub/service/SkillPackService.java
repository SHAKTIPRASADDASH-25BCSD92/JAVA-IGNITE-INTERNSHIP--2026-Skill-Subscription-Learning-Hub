package com.skills.hub.service;

import com.skills.hub.model.SkillPack;

import java.util.List;

public interface SkillPackService {

    SkillPack addSkillPack(SkillPack pack);

    List<SkillPack> getAllPacks();

    SkillPack updateSkillPack(SkillPack pack);

    void deleteSkillPack(Long id);
    List<SkillPack> searchPacks(String keyword);
}



