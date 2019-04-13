package model.skills;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class SortRecruiters {

    private Map<Recruiter, Integer> unSotedRecruiters;

    public SortRecruiters (Map<Recruiter, Integer> unSotedRecruiters){
        this.unSotedRecruiters = unSotedRecruiters;
    }

/*    Set<Recruiter> sort (){
        Map<Recruiter, Integer> collect = unSotedRecruiters.entrySet()
                .stream()
                .sorted(Map.Entry.<Recruiter, Integer>comparingByValue())
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        return collect.keySet();
    }*/


}

