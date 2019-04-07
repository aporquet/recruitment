package model.availability;

import java.time.LocalDateTime;
import java.util.List;

public class RecruiterDto {
    private LocalDateTime availableDate;
    private List<LocalDateTime> recruiterAvailabilities;

    public void setAvailability(LocalDateTime availableDate) {
        this.availableDate = availableDate;
    }

    public LocalDateTime getAvailableDate() {
        return availableDate;
    }

    public List<LocalDateTime> availabilities() {
        return recruiterAvailabilities;
    }

    public void setAvailabilities(List<LocalDateTime> recruiterAvailabilities) {

        this.recruiterAvailabilities = recruiterAvailabilities;
    }

    public List<LocalDateTime> getRecruiterAvailabilities() {
        return recruiterAvailabilities;
    }
}
