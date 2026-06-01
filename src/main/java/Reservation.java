import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private LocalDate dataOd;
    private LocalDate dataDo;
    private ReservationStatus status;

    private Guest guest;
    private Room room;

    public Reservation(LocalDate dataOd, LocalDate dataDo, Guest guest, Room room) {
        this.dataOd = dataOd;
        this.dataDo = dataDo;
        this.guest = guest;
        this.room = room;
        this.status = ReservationStatus.AKTYWNA;
    }

    public LocalDate getDataOd() {
        return dataOd;
    }

    public void setDataOd(LocalDate dataOd) {
        this.dataOd = dataOd;
    }

    public LocalDate getDataDo() {
        return dataDo;
    }

    public void setDataDo(LocalDate dataDo) {
        this.dataDo = dataDo;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double obliczKoszt()
    {
        long liczbaNocy = ChronoUnit.DAYS.between(this.dataOd, this.dataDo);
        return liczbaNocy * this.room.getCenaZaNoc();
    }
}