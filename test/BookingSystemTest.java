import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class BookingSystemTest {

    private BookingSystem bookingSystem;

    @BeforeEach
    public void setUp() {
        bookingSystem = new BookingSystem();
    }

    @Test
    public void testBookingSystemExists() {
        assertNotNull(bookingSystem);
    }

    @Test
    public void testAppointmentInitializationStatus() {
        Physiotherapist physio = new Physiotherapist(1, "Dr. Alice", "Sports Injury");
        Patient patient = new Patient("John Doe", "123 Main St", "1234567890");
        Treatment treatment = new Treatment("Physio Session", "2025-04-25 10:00");
        Appointment appointment = new Appointment(101, physio, patient, treatment);

        assertEquals("Booked", appointment.getStatus());
    }

    @Test
    public void testAppointmentCancelUpdatesStatus() {
        Appointment appointment = createSampleAppointment();
        appointment.cancel();
        assertEquals("Cancelled", appointment.getStatus());
    }

    @Test
    public void testPhysioAddAndRetrieveWeeklyTreatments() {
        Physiotherapist physio = new Physiotherapist(2, "Dr. Smith", "Orthopedic");
        List<Treatment> week1Treatments = Arrays.asList(
            new Treatment("Massage", "2025-05-01 09:00"),
            new Treatment("Stretching", "2025-05-01 10:00")
        );

        physio.addWeeklyTreatments(1, week1Treatments);
        List<Treatment> retrieved = physio.getTreatmentsForWeek(1);

        assertEquals(2, retrieved.size());
    }

    @Test
    public void testPatientIdAutoIncrement() {
        Patient p1 = new Patient("Alice", "Addr 1", "111");
        Patient p2 = new Patient("Bob", "Addr 2", "222");

        assertNotEquals(p1.getId(), p2.getId());
    }

    @Test
    public void testPreventDuplicateWeeklyTreatmentAddition() {
        Physiotherapist physio = new Physiotherapist(3, "Dr. Laura", "Rehab");
        List<Treatment> treatments = Collections.singletonList(
            new Treatment("Rehab", "2025-05-02 14:00")
        );

        physio.addWeeklyTreatments(2, treatments);
        physio.addWeeklyTreatments(2, treatments); 

        List<Treatment> retrieved = physio.getTreatmentsForWeek(2);
        assertEquals(1, retrieved.size()); 
    }

    // Helper method for cleaner test code
    private Appointment createSampleAppointment() {
        Physiotherapist physio = new Physiotherapist(1, "Dr. Alice", "Sports Injury");
        Patient patient = new Patient("John Doe", "123 Main St", "1234567890");
        Treatment treatment = new Treatment("Physio Session", "2025-04-25 10:00");
        return new Appointment(101, physio, patient, treatment);
    }
}
