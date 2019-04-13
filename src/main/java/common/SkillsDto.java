package common;

import java.util.List;

public class SkillsDto {
    private List<String> keySkills;
    private List<String> otherSkills;

    public List<String> getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(List<String> keySkills) {
        this.keySkills = keySkills;
    }

    public void setOtherSkills(List<String> otherSkills) {
        this.otherSkills = otherSkills;
    }

    public List<String> getOtherSkills() {
        return otherSkills;
    }
}
