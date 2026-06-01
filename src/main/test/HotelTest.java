import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class HotelTest
{
    @Test
    void testWyszukajWolne_PowinnoZwrocicWolnePokoje()
    {
        Hotel hotel = new Hotel();
        hotel.getPokoje().add(new Room("67", RoomType.VIP, 2137));

        java.util.List<Room> wolne = hotel.wyszukajWolne(LocalDate.now(), LocalDate.now().plusDays(3));

        assertEquals(1, wolne.size(), "Powinien znalezc 1 wolny pokoj w hotelu");
    }

    @Test
    void testUtworzRezerwacje_PowinnoDodacRezerwacjeDoListy()
    {
        Hotel hotel = new Hotel();

        hotel.utworzRezerwacje();

        assertEquals(1, hotel.getRezerwacje().size(), "Lista rezerwacji powinna zawierac nowa rezerwacje");
    }

    @Test
    void testObliczKoszt_PowinnoPoprawnieWyliczycCene()
    {
        Hotel hotel = new Hotel();

        double koszt = hotel.obliczKoszt();

        assertEquals(600.0, koszt, "Koszt rezerwacji powinien zostac poprawnie obliczony i nie wynosic 0.0");
    }

    @Test
    void testAnuluj_PowinnoZmienicStatusRezerwacjiNaAnulowana()
    {
        Hotel hotel = new Hotel();
        Guest gosc = new Guest("1", "Tomasz", "Kowalski");
        Room pokoj = new Room("67", RoomType.VIP, 2137);

        Reservation rezerwacja = new Reservation(LocalDate.now(), LocalDate.now().plusDays(2), gosc, pokoj);
        hotel.getRezerwacje().add(rezerwacja);

        hotel.anuluj();

        assertEquals(ReservationStatus.ANULOWANA, hotel.getRezerwacje().get(0).getStatus(), "Status rezerwacji powinien zmienic sie na ANULOWANA");
    }
}
