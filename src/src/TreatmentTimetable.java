
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class TreatmentTimetable {
    private Map<Integer, List<Treatment>> weeklyTreatments;  // Week number mapped to list of treatments

    public TreatmentTimetable() {
        weeklyTreatments = new HashMap<>();
    }

    public void addWeeklyTreatments(int week, List<Treatment> treatments) {
        weeklyTreatments.put(week, treatments);
    }

    public List<Treatment> getTreatmentsForWeek(int week) {
        return weeklyTreatments.getOrDefault(week, new ArrayList<>());
    }

    public Map<Integer, List<Treatment>> getWeeklyTreatments() {
        return weeklyTreatments;
    }
}
