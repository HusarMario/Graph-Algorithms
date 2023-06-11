import java.util.ArrayList;

public class Vrchol {
    private final String nazov;
    private final ArrayList<Hrana> vychadazajuceHrany;
    private final ArrayList<Hrana> vchadzajuceHrany;
    private int pocetVychadzajucichHran;
    private int pocetVchadzajucichHran;

    private int pocetVchadzajucichHranChangeable;
    private int zaciatok;
    private int koniec;
    private boolean kriticka;
    private int cenaCinnosti;

    private int t;
    private Vrchol x;
    private boolean defT;
    private boolean ocislovany;


    public boolean isKriticka() {
        return this.kriticka;
    }

    public void setKriticka(boolean kriticka) {
        this.kriticka = kriticka;
    }

    public int getZaciatok() {
        return this.zaciatok;
    }

    public void setZaciatok(int zaciatok) {
        this.zaciatok = zaciatok;
    }

    public int getKoniec() {
        return this.koniec;
    }

    public void setKoniec(int koniec) {
        this.koniec = koniec;
    }


    public int getPocetVchadzajucichHranChangeable() {
        return this.pocetVchadzajucichHranChangeable;
    }

    public void lowerPocetVchadzajucichHranChangeable() {
        this.pocetVchadzajucichHranChangeable--;
    }

    public void setPocetVchadzajucichHranChangeable(int pocetVchadzajucichHranChangeable) {
        this.pocetVchadzajucichHranChangeable = pocetVchadzajucichHranChangeable;
    }

    public int getCenaCinnosti() {
        return this.cenaCinnosti;
    }

    public void setCenaCinnosti(int cenaCinnosti) {
        this.cenaCinnosti = cenaCinnosti;
    }

    public Vrchol(String nazov) {
        this.nazov = nazov;
        this.vychadazajuceHrany = new ArrayList<>();
        this.vchadzajuceHrany = new ArrayList<>();
        this.pocetVychadzajucichHran = 0;
        this.pocetVchadzajucichHran = 0;
        this.ocislovany = true;
        this.cenaCinnosti = 0;
    }

    public String getNazov() {
        return this.nazov;
    }

    public boolean maKHranu(Vrchol koncovyVrchol) {
        for (Hrana hrana : this.vychadazajuceHrany) {
            if (hrana.getKoncovy() == koncovyVrchol) {
                return true;
            }
        }
        return false;
    }

    public Hrana getKHrana(Vrchol koncovyVrchol) {
        for (Hrana hrana : this.vychadazajuceHrany) {
            if ((hrana.getKoncovy()) == koncovyVrchol) {
                return hrana;
            }
        }
        return null;
    }

    public boolean maVHranu(Vrchol vstupnyVrchol) {
        for (Hrana hrana : this.vchadzajuceHrany) {
            if (hrana.getPociatocny() == vstupnyVrchol) {
                return true;
            }
        }
        return false;
    }

    public Hrana getVHrana(Vrchol vstupnyVrchol) {
        for (Hrana hrana : this.vchadzajuceHrany) {
            if (hrana.getPociatocny() == vstupnyVrchol) {
                return hrana;
            }
        }
        return null;
    }

    public Hrana getVHrana(int index) {
        return this.vchadzajuceHrany.get(index);
    }

    public boolean isOcislovany() {
        return this.ocislovany;
    }

    public void setOcislovany(boolean ocislovany) {
        this.ocislovany = ocislovany;
    }

    public Hrana getKHrana(int index) {
        return this.vychadazajuceHrany.get(index);
    }

    public void pridajVchadzajuci(Hrana hrana) {
        this.vchadzajuceHrany.add(hrana);
        this.pocetVchadzajucichHran++;
    }

    public void pridajVychadzajuci(Hrana hrana) {
        this.vychadazajuceHrany.add(hrana);
        this.pocetVychadzajucichHran++;
    }

    public int getPocetVychadzajucichHran() {
        return this.pocetVychadzajucichHran;
    }

    public int getPocetVchadzajucichHran() {
        return this.pocetVchadzajucichHran;
    }

    public int getT() {
        return this.t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public Vrchol getX() {
        return this.x;
    }

    public void setX(Vrchol x) {
        this.x = x;
    }

    public boolean isDefT() {
        return this.defT;
    }

    public void setDefT(boolean defT) {
        this.defT = defT;
    }
}
