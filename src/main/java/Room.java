public class Room {
    private String numer;
    private RoomType standard;
    private double cenaZaNoc;

    public Room(String numer, RoomType standard, double cenaZaNoc) {
        this.numer = numer;
        this.standard = standard;
        this.cenaZaNoc = cenaZaNoc;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public RoomType getStandard() {
        return standard;
    }

    public void setStandard(RoomType standard) {
        this.standard = standard;
    }

    public double getCenaZaNoc() {
        return cenaZaNoc;
    }

    public void setCenaZaNoc(double cenaZaNoc) {
        this.cenaZaNoc = cenaZaNoc;
    }
}