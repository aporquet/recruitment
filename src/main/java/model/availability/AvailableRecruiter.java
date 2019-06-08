package model.availability;

import common.dto.RecruiterDto;

import java.util.List;

public class AvailableRecruiter {

    private List<RecruiterDto> recruiters;

    public AvailableRecruiter(List<RecruiterDto> recruiters) {
        if (recruiters == null || recruiters.isEmpty()) {
            throw new AnyRecruiterAvailableException();
        }
        this.recruiters = recruiters;
    }

    public RecruiterDto getFirstAvailableRecruiter() {
        return recruiters.get(0);
    }
}
