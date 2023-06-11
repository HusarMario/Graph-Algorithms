public class Hrana {
    private final Vrchol pociatocny;
    private final Vrchol koncovy;
    private final int cenaHrany;
    private int tok;

    public Hrana (Vrchol pociatocny, Vrchol koncovy, int cenaHrany, int tok) {
        this.pociatocny = pociatocny;
        this.koncovy = koncovy;
        this.cenaHrany = cenaHrany;
        this.tok = tok;
    }

    public Hrana(Vrchol pociatocny, Vrchol koncovy, int cenaHrany) {
        this.pociatocny = pociatocny;
        this.koncovy = koncovy;
        this.cenaHrany = cenaHrany;
    }

    public Hrana(Vrchol pociatocny, Vrchol koncovy) {
        this.pociatocny = pociatocny;
        this.koncovy = koncovy;
        this.cenaHrany = 1;
    }

    public Vrchol getPociatocny() {
        return this.pociatocny;
    }

    public Vrchol getKoncovy() {
        return this.koncovy;
    }

    public int getCenaHrany() {
        return this.cenaHrany;
    }

    public void setTok(int tok) {
        this.tok = tok;
    }

    public int getTok() {
        return this.tok;
    }
}
