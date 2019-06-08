package use_case;

import common.dto.CandidateDto;
import common.dto.CandidateFullDto;

import java.util.List;
import java.util.UUID;

public interface CandidateRepository {
    CandidateFullDto getCandidate(UUID uuid);

    List<CandidateFullDto> getCandidates();

    CandidateDto getCandidateForSchedule(UUID uuidCandidate);
}
