
public class Appointment {
    private int bookingId;
    private Physiotherapist physio;
    private Patient patient;
    private Treatment treatment;
    private String status;

    public Appointment(int bookingId, Physiotherapist physio, Patient patient, Treatment treatment) {
        this.bookingId = bookingId;
        this.physio = physio;
        this.patient = patient;
        this.treatment = treatment;
        this.status = "Booked";
    }
    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }
    
    public int getBookingId() {
        return bookingId;
    }

    public Physiotherapist getPhysio() {
        return physio;
    }

    public Patient getPatient() {
        return patient;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public String getStatus() {
        return status;
    }

    public void cancel() {
        this.status = "Cancelled";
    }

    public void attend() {
        this.status = "Attended";
    }
}
