package model.skills;

import common.CandidateDto;
import common.RecruiterDto;

import java.util.List;

public class SkillsChecker {
    private CandidateDto candidateDto;
    private List<RecruiterDto> recruiters;

    public SkillsChecker(CandidateDto candidate, List<RecruiterDto> recruiters) {

        this.candidateDto = candidate;
        this.recruiters = recruiters;
    }

    public List<RecruiterDto> getTechnicallyCompetentRecruiters() {
        Candidate candidate = new Candidate(candidateDto.getCandidateSkills(), candidateDto.getExperienceYears());
        for (RecruiterDto recruiterDto: recruiters){
            Recruiter recruiter = new Recruiter(recruiterDto.getRecruiterSkills(), recruiterDto.getExperienceYears());
            if(!recruiter.canTest(candidate)){
                recruiters.remove(recruiter);
            }
        }
        return recruiters;
    }
}
