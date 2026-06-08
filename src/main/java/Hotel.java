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

    public void utworzRezerwacje() {
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