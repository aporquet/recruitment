package infra;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DateMapper {

    private int mapHour(int hourInfra, int hourDateTime){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(9, 1);
        map.put(10, 2);
        map.put(11, 3);
        map.put(12, 4);
        map.put(13, 5);
        map.put(14, 6);
        map.put(15, 7);
        map.put(16, 8);
        map.put(17, 9);
        map.put(18, 10);
        map.put(19, 11);
        if (hourDateTime > 0){
            return map.get(hourDateTime);
        }
        else {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue().equals(hourInfra)) {
                    return entry.getKey();
                }
            }
            return 0;
        }
    }

    public InfraDateForm mapDateTimeToInfraDateForm(LocalDateTime dateTime){
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfWeek().getValue();
        int hour = mapHour(0, dateTime.getHour());
        InfraDateForm infraDateForm = new InfraDateForm(month, day, hour);
        return infraDateForm;
    }

    public LocalDateTime mapInfraDateFormToDateTime(InfraDateForm infraDateForm){
        LocalDateTime localDateTime = LocalDateTime.of(2019, infraDateForm.getMonth(), infraDateForm.getDay(), mapHour(infraDateForm.getHour(), 0), 0);
        return localDateTime;
    }

}
