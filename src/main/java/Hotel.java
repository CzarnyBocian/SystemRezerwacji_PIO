import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Room> pokoje;
    private List<Reservation> rezerwacje;

    public Hotel() {
        this.pokoje = new ArrayList<>();
        this.rezerwacje = new ArrayList<>();
    }

    private boolean czyZajety(Room pokoj, LocalDate start, LocalDate end) {
        for (Reservation r : rezerwacje) {
            if (r.getRoom().getNumer().equals(pokoj.getNumer())) {
                if (r.getStatus() != ReservationStatus.ANULOWANA) {
                    if (start.isBefore(r.getDataDo()) && end.isAfter(r.getDataOd())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Room> wyszukajWolne(LocalDate start, LocalDate end) {
        List<Room> wolnePokoje = new ArrayList<>();

        for (Room pokoj : pokoje) {
            boolean czyZajety = false;
            for (Reservation rezerwacja : rezerwacje) {

                if (rezerwacja.getRoom().equals(pokoj) && rezerwacja.getStatus() != ReservationStatus.ANULOWANA) {

                    if (start.isBefore(rezerwacja.getDataDo()) && end.isAfter(rezerwacja.getDataOd())) {
                        czyZajety = true;
                        break;
                    }
                }
            }
            if (!czyZajety) {
                wolnePokoje.add(pokoj);
            }
        }
        return wolnePokoje;
    }

    public Reservation utworzRezerwacje(LocalDate start, LocalDate end, Guest guest, Room room) {
        if (czyZajety(room, start, end)) {
            throw new RoomUnavailableException("Pokój o numerze " + room.getNumer() + " jest już zajęty w tym terminie!");
        }

        Reservation nowa = new Reservation(start, end, guest, room);
        rezerwacje.add(nowa);
        return nowa;
    }

    public double obliczKoszt() {
        return 0.0;
    }

    public void anuluj(Reservation rezerwacjaDoAnulowania) {
        if (rezerwacje.contains(rezerwacjaDoAnulowania)) {
            rezerwacjaDoAnulowania.setStatus(ReservationStatus.ANULOWANA);
        }
    }

    public List<Room> getPokoje() {
        return pokoje;
    }

    public List<Reservation> getRezerwacje() {
        return rezerwacje;
    }
}