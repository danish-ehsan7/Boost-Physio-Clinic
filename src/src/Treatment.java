import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Treatment {
    private String name;
    private LocalDateTime startDateTime;
    private int durationMinutes; // duration in minutes

    // Constructor with duration
    public Treatment(String name, String dateTime, int durationMinutes) {
        this.name = name;
        this.startDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.durationMinutes = durationMinutes;
    }

    // Constructor without duration (defaults to 60 minutes)
    public Treatment(String name, String dateTime) {
        this(name, dateTime, 60); // Default to 60 minute duration
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return formatDateTime(); // this will be shown in the table UI
    }
    
    public String getDateTime() {
        return formatDateTime();
    }

    private String formatDateTime() {
        // Format day with ordinal indicator (1st, 2nd, 3rd, etc.)
        String day = startDateTime.getDayOfMonth() + getOrdinalSuffix(startDateTime.getDayOfMonth());
        
        // Format the full string
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String startTime = startDateTime.format(timeFormatter);
        String endTime = startDateTime.plusMinutes(durationMinutes).format(timeFormatter);
        
        return String.format("%s %s %s %d, %s-%s",
            startDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.UK),
            day,
            startDateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.UK).toUpperCase(),
            startDateTime.getYear(),
            startTime,
            endTime);
    }

    private String getOrdinalSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }
}