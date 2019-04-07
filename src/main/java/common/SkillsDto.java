package common;

import java.util.List;

public class SkillsDto {
    private final List<String> keySkills;

    public SkillsDto(List<String> keySkills) {
        this.keySkills = keySkills;
    }

    public List<String> getKeySkills() {
        return keySkills;
    }

}
