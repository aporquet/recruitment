package model.skills;

import common.RecruiterDto;

import java.util.*;

import static java.util.stream.Collectors.toMap;

class SortRecruiters {

    private Map<RecruiterDto, Integer> unSotedRecruiters;

    public SortRecruiters (Map<RecruiterDto, Integer> unSotedRecruiters){
        this.unSotedRecruiters = unSotedRecruiters;
    }

    Set<RecruiterDto> sort (){
        Map<RecruiterDto, Integer> collect = unSotedRecruiters
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        return collect.keySet();
    }

}

