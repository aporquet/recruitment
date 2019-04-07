package model.availability;

import java.util.List;

public class AvailableRecruiter {

    private List<RecruiterDto> recruiters;

    public AvailableRecruiter(List<RecruiterDto> recruiters) {
        this.recruiters = recruiters;
    }

    public RecruiterDto getFirstAvailableRecruiter() {
        return recruiters.get(0);
    }
}
