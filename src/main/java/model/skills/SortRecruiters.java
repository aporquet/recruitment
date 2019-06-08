package model.skills;

import common.dto.RecruiterDto;

import java.util.*;

import static java.util.stream.Collectors.toMap;

class SortRecruiters {

    private Map<RecruiterDto, Integer> unSortedRecruiters;

    public SortRecruiters (Map<RecruiterDto, Integer> unSortedRecruiters){
        this.unSortedRecruiters = unSortedRecruiters;
    }

    Set<RecruiterDto> sort (){
        Map<RecruiterDto, Integer> collect = unSortedRecruiters
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        return collect.keySet();
    }

}

