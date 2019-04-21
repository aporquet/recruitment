package use_case;

import common.CandidateDto;

import java.util.UUID;

public interface CandidateRepository {
    CandidateDto getCandidateById(UUID idCandidate);
}
