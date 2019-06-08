package model.skills;

import common.dto.CandidateDto;
import common.dto.RecruiterDto;
import common.dto.SkillsDto;

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
        SkillsDto candidateSkillsDto = candidateDto.getCandidateSkills();
        Skills candidateSkills = new Skills(candidateSkillsDto.getKeySkills(), candidateSkillsDto.getOtherSkills());
        Candidate candidate = new Candidate(candidateSkills, candidateDto.getExperienceYears());
        List<RecruiterDto> competentRecruiter = new ArrayList<>();
        competentRecruiter.addAll(recruiters);
        for (RecruiterDto recruiterDto: recruiters){
            Skills skills = new Skills(recruiterDto.getRecruiterSkills().getKeySkills(), recruiterDto.getRecruiterSkills().getOtherSkills());
            Recruiter recruiter = new Recruiter(skills, recruiterDto.getExperienceYears());
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
            Skills skills = new Skills(recruiterDto.getRecruiterSkills().getKeySkills(), recruiterDto.getRecruiterSkills().getOtherSkills());
            Recruiter recruiter = new Recruiter(skills, recruiterDto.getExperienceYears());
            int numberOfCommonOtherSkills = candidate.getOtherSkills().stream()
                    .filter(recruiter.getOtherSkills()::contains)
                    .collect(toList()).size();
            mapRecruiters.put(recruiterDto, numberOfCommonOtherSkills);
        }
        SortRecruiters s = new SortRecruiters(mapRecruiters);
        return s.sort().stream().collect(toList());
    }

}
