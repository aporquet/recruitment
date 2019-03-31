package model;

import java.util.List;

public class Candidate {

    private Skills candidateSkills;

    public Candidate(Skills skillsCandidate) {
        this.candidateSkills = skillsCandidate;
    }

    public List<String> getKeySkills() {
        return candidateSkills.getKeySkills();

    }
}
