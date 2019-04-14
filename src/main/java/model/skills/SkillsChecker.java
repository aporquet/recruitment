package model.skills;

import common.CandidateDto;
import common.RecruiterDto;

import java.util.*;
import static java.util.stream.Collectors.toList;

public class SkillsChecker {
    private CandidateDto candidateDto;
    private List<RecruiterDto> recruiters;

    public SkillsChecker(CandidateDto candidate, List<RecruiterDto> recruiters) {
        this.candidateDto = candidate;
        this.recruiters = recruiters;
    }

    public List<RecruiterDto> getTechnicallyCompetentRecruitersSortByOtherSkills() {
        Candidate candidate = new Candidate(candidateDto.getCandidateSkills(), candidateDto.getExperienceYears());
        List<RecruiterDto> competentRecruiter = new ArrayList<>();
        competentRecruiter.addAll(recruiters);
        for (RecruiterDto recruiterDto: recruiters){
            Recruiter recruiter = new Recruiter(recruiterDto.getRecruiterSkills(), recruiterDto.getExperienceYears());
            if(!recruiter.canTest(candidate)){
                competentRecruiter.remove(recruiterDto);
            }
        }
        return sortByOtherSkillsInCommon(candidate, competentRecruiter);
    }

    private List<RecruiterDto> sortByOtherSkillsInCommon(Candidate candidate, List<RecruiterDto> recruiters) {
        if(recruiters.isEmpty()){
            return recruiters;
        }
        Map<RecruiterDto, Integer> mapRecruiters = new HashMap<>();
        for (RecruiterDto recruiterDto: recruiters){
            Recruiter recruiter = new Recruiter(recruiterDto.getRecruiterSkills(), recruiterDto.getExperienceYears());
            int numberOfCommonOtherSkills = candidate.getOtherSkills().stream()
                    .filter(recruiter.getOtherSkills()::contains)
                    .collect(toList()).size();
            mapRecruiters.put(recruiterDto, numberOfCommonOtherSkills);
        }
        SortRecruiters s = new SortRecruiters(mapRecruiters);
        return s.sort().stream().collect(toList());
    }

}
