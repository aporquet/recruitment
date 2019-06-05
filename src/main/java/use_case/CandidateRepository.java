package use_case;

import common.CandidateDto;
import common.CandidateFullDto;

import java.util.List;
import java.util.UUID;

public interface CandidateRepository {
    CandidateFullDto getCandidate(String idCandidate);

    List<CandidateFullDto> getCandidates();

    CandidateDto getCandidateForSchedule(UUID uuidCandidate);
}
