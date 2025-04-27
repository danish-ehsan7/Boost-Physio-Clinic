import java.util.*;

public class Physiotherapist {
    private int id;
    private String name;
    private String address;
    private String phone;
    private List<String> expertise;
    private List<Map<String, List<Treatment>>> termSchedule; 
    private Map<Integer, List<Treatment>> weeklyTreatments = new HashMap<>();

    public Physiotherapist(int id, String name, String expertise) {
        this.id = id;
        this.name = name;
        this.address = "Not Provided";
        this.phone = "Not Provided";
        this.expertise = new ArrayList<>();
        this.expertise.add(expertise);
        this.termSchedule = new ArrayList<>();
        this.weeklyTreatments = new HashMap<>();

        // Initialize the schedule for each week (4 weeks in total)
        for (int i = 0; i < 4; i++) {
            termSchedule.add(new HashMap<>());
        }
    }

    public void addWeeklyTreatments(int week, List<Treatment> treatments) {
        // Add the treatments for the specified week to the termSchedule map
        if (weeklyTreatments.containsKey(week)) {
            System.out.println("Week " + week + " treatments already added for " + name);
            return; 
        }

        // Add treatments to weeklyTreatments for the week
        weeklyTreatments.put(week, treatments);

        // Also add treatments to termSchedule map (for illustration, treating each treatment type as a key)
        Map<String, List<Treatment>> weekSchedule = termSchedule.get(week - 1);
        for (Treatment treatment : treatments) {
            weekSchedule.putIfAbsent(treatment.getName(), new ArrayList<>());
            weekSchedule.get(treatment.getName()).add(treatment);
        }
    }
    public List<Treatment> getTreatments() {
        List<Treatment> allTreatments = new ArrayList<>();
        for (List<Treatment> weeklyTreatments : this.weeklyTreatments.values()) {
            allTreatments.addAll(weeklyTreatments);
        }
        return allTreatments;
    }
    // Get the treatments for a specific week
    public List<Treatment> getTreatmentsForWeek(int week) {
        return weeklyTreatments.getOrDefault(week, new ArrayList<>());
    }

    // Getter methods
    public List<String> getExpertise() { return expertise; }
    public String getName() { return name; }
    public int getId() { return id; }
    
    @Override
    public String toString() {
        return name;
    }
    public boolean hasTreatment(Treatment treatment) {
        return weeklyTreatments.values().stream()
                .flatMap(List::stream)
                .anyMatch(t -> t.equals(treatment));
    }
}
