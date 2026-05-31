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
        return new ArrayList<>();
    }

    public void utworzRezerwacje() {
    }

    public double obliczKoszt() {
        return 0.0;
    }

    public void anuluj() {
    }

    public List<Room> getPokoje() {
        return pokoje;
    }

    public List<Reservation> getRezerwacje() {
        return rezerwacje;
    }
}