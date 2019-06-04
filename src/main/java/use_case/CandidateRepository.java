package use_case;

import common.CandidateDto;
import common.CandidateFullDto;

import java.util.UUID;

public interface CandidateRepository {
    CandidateFullDto getCandidateById(String idCandidate);

    CandidateDto getCandidateByUUIDForSchedule(UUID uuidCandidate);
}
