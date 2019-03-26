package model;

import java.util.List;

public class Candidate {

    private List<String> skillsCandidate;

    public Candidate(List<String> skillsCandidate) {
        this.skillsCandidate = skillsCandidate;
    }

    public List<String> getSkills() {
        return skillsCandidate;
    }
}
