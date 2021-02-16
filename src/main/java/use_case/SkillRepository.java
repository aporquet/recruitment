package use_case;

import common.dto.SkillFullDto;

import java.util.List;

public interface SkillRepository {

    List<SkillFullDto> getSkills();

    boolean insertSkill(String skill);

}
