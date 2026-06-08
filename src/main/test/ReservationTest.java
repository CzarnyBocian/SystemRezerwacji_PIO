import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest
{
    @Test
    void testObliczKoszt_PowinnoPoprawnieWyliczycSume()
    {
        Guest gosc = new Guest("1", "Tomasz", "Kowalski");
        Room pokoj = new Room("67", RoomType.VIP, 2000);

        LocalDate start = LocalDate.of(2026, 6, 1);
        LocalDate end = LocalDate.of(2026, 6, 5);
        Reservation rezerwacja = new Reservation(start, end, gosc, pokoj);

        double wynik = rezerwacja.obliczKoszt();

        assertEquals(8000, wynik, "Koszt za 4 noce po 2000 zl powinien wynosic 8000");
    }
}
