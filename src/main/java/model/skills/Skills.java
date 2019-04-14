package model.skills;

import java.util.List;

class Skills {
    private List<String> keySkills;
    private List<String> otherSkills;

    Skills(List<String> keySkills, List<String> otherSkills) {
        this.keySkills = keySkills;
        this.otherSkills = otherSkills;
    }

    List<String> getKeySkills() {
        return keySkills;
    }

    List<String> getOtherSkills() {
        return otherSkills;
    }
}
