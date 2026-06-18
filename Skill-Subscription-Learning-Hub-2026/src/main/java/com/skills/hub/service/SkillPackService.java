package com.skills.hub.service;

import com.skills.hub.model.SkillPack;

import java.util.List;

public interface SkillPackService {

    SkillPack addSkillPack(SkillPack pack);

    List<SkillPack> getAllPacks();

    SkillPack updateSkillPack(SkillPack pack);

    void deleteSkillPack(Long id);
}

package com.skills.hub.service;

import com.skills.hub.model.SkillPack;
import com.skills.hub.repository.SkillPackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillPackServiceImpl implements SkillPackService {

    private final SkillPackRepository skillPackRepository;

    public SkillPackServiceImpl(SkillPackRepository skillPackRepository) {
        this.skillPackRepository = skillPackRepository;
    }

    @Override
    public SkillPack addSkillPack(SkillPack pack) {
        return skillPackRepository.save(pack);
    }

    @Override
    public List<SkillPack> getAllPacks() {
        return skillPackRepository.findAll();
    }

    @Override
    public SkillPack updateSkillPack(SkillPack pack) {
        return skillPackRepository.save(pack);
    }

    @Override
    public void deleteSkillPack(Long id) {
        skillPackRepository.deleteById(id);
    }
}
