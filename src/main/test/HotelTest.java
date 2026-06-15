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
        Guest gosc = new Guest("1", "Jan", "Kowalski");
        Room pokoj = new Room("101", RoomType.STANDARD, 200);

        hotel.utworzRezerwacje(LocalDate.now(), LocalDate.now().plusDays(2), gosc, pokoj);

        assertEquals(1, hotel.getRezerwacje().size(), "Lista rezerwacji powinna zawierac nowa rezerwacje");
    }

    @Test
    void testUtworzRezerwacje_ZajetyPokoj_PowinnoRzucicWyjatek() {
        Hotel hotel = new Hotel();
        Guest gosc = new Guest("1", "Jan", "Kowalski");
        Room pokoj = new Room("101", RoomType.STANDARD, 200);

        LocalDate start = LocalDate.of(2026, 6, 1);
        LocalDate end = LocalDate.of(2026, 6, 5);
        hotel.utworzRezerwacje(start, end, gosc, pokoj);

        LocalDate startNakladajacy = LocalDate.of(2026, 6, 3);
        LocalDate endNakladajacy = LocalDate.of(2026, 6, 7);

        assertThrows(RoomUnavailableException.class, () -> {
            hotel.utworzRezerwacje(startNakladajacy, endNakladajacy, gosc, pokoj);
        }, "System powinien zablokować overbooking i rzucić RoomUnavailableException");
    }

    @Test
    void testObliczKoszt_PowinnoPoprawnieWyliczycCene()
    {
        Hotel hotel = new Hotel();
        Guest gosc = new Guest("1", "Andrzej", "Kowalski");
        Room pokoj = new Room("101", RoomType.STANDARD, 200.0);

        hotel.utworzRezerwacje(LocalDate.now(), LocalDate.now().plusDays(3), gosc, pokoj);

        double koszt = hotel.obliczKoszt();

        assertEquals(600.0, koszt, "Koszt rezerwacji powinien zostac poprawnie obliczony(600) i nie wynosic 0.0");
    }

    @Test
    void testAnuluj_PowinnoZmienicStatusRezerwacjiNaAnulowana()
    {
        Hotel hotel = new Hotel();
        Guest gosc = new Guest("1", "Tomasz", "Kowalski");
        Room pokoj = new Room("67", RoomType.VIP, 2137);

        Reservation rezerwacja = new Reservation(LocalDate.now(), LocalDate.now().plusDays(2), gosc, pokoj);
        hotel.getRezerwacje().add(rezerwacja);

        hotel.anuluj(rezerwacja);

        assertEquals(ReservationStatus.ANULOWANA, hotel.getRezerwacje().get(0).getStatus(), "Status rezerwacji powinien zmienic sie na ANULOWANA");
    }

    @Test
    void testPokojPojawiaSieWWolnychPoAnulowaniuRezerwacji()
    {
        Hotel hotel = new Hotel();
        Room pokoj = new Room("67", RoomType.VIP, 2000);
        hotel.getPokoje().add(pokoj);

        Guest gosc = new Guest("1", "Tomasz", "Kowalski");
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now().plusDays(3);

        Reservation rezerwacja = new Reservation(start, end, gosc, pokoj);
        hotel.getRezerwacje().add(rezerwacja);

        java.util.List<Room> wolnePrzedAnulowaniem = hotel.wyszukajWolne(start, end);
        assertFalse(wolnePrzedAnulowaniem.contains(pokoj), "Pokój nie powinien być dostępny, gdy rezerwacja jest aktywna");

        hotel.anuluj(rezerwacja);

        java.util.List<Room> wolnePoAnulowaniu = hotel.wyszukajWolne(start, end);
        assertTrue(wolnePoAnulowaniu.contains(pokoj), "Pokój powinien ponownie pojawić się w wynikach wyszukiwania po anulowaniu");
    }
}
