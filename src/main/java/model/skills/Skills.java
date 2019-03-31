package model.skills;

import java.util.List;

class Skills {
    private final List<String> keySkills;

    Skills(List<String> keySkills) {
        this.keySkills = keySkills;
    }

    List<String> getKeySkills() {
        return keySkills;
    }

}
