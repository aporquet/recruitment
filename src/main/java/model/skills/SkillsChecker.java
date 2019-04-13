package model.skills;

import common.CandidateDto;
import common.RecruiterDto;
import common.SkillsDto;

import java.util.*;

public class SkillsChecker {
    private CandidateDto candidateDto;
    private List<RecruiterDto> recruiters;

    public SkillsChecker(CandidateDto candidate, List<RecruiterDto> recruiters) {

        this.candidateDto = candidate;
        this.recruiters = recruiters;
    }

/*    public Set<Recruiter> getTechnicallyCompetentRecruiters() {
        Candidate candidate = new Candidate(candidateDto.getCandidateSkills(), candidateDto.getExperienceYears());
        for (RecruiterDto recruiterDto: recruiters){
            Recruiter recruiter = new Recruiter(recruiterDto.getRecruiterSkills(), recruiterDto.getExperienceYears());
            if(!recruiter.canTest(candidate)){
                recruiters.remove(recruiter);
            }
        }
        Set<Recruiter> sortedRecruiters = sortByOtherSkillsInCommon(candidate, recruiters);
        return sortedRecruiters;
    }*/

/*    private Set<Recruiter> sortByOtherSkillsInCommon(Candidate candidate, List<RecruiterDto> recruiters) {
        //List<RecruiterDto> sortedRecruiters = new ArrayList<>();
        Map<Recruiter, Integer> mapRecruiters = new HashMap<>();
        int i = 0;
        for (RecruiterDto recruiterDto: recruiters){
            Recruiter recruiter = new Recruiter(recruiterDto.getRecruiterSkills(), recruiterDto.getExperienceYears());
            mapRecruiters.put(recruiter, i++);
        }
        SortRecruiters s = new SortRecruiters(mapRecruiters);
        return s.sort();
    }*/

}
