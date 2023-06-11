import java.util.ArrayList;

public class Graf {
    private final Algoritmy algoritmy;
    private final ArrayList<Vrchol> vrcholy;
    private final ArrayList<Hrana> hrany;
    private int pocetVrcholov;
    private int pocetHran;

    public Graf() {
        this.vrcholy = new ArrayList<>();
        this.hrany = new ArrayList<>();
        this.pocetVrcholov = 0;
        this.pocetHran = 0;

        this.algoritmy = new Algoritmy();
    }

    public void tok() {
        this.algoritmy.tok(this.vrcholy, this.hrany);
    }

    public void cpm() {
        this.algoritmy.cpm(this.vrcholy, this.hrany);
    }

    public void najdiNajlacnejsiuKostru() {
        this.algoritmy.najdiKostru(true, this.vrcholy, this.hrany);
    }

    public void najdiNajdrahsiuKostru() {
        this.algoritmy.najdiKostru(false, this.vrcholy, this.hrany);
    }

    public void najdiNajkratsiuCestuLabelCorrect(String zaciatok) {
        Vrchol zaciatocny = this.getVrchol(zaciatok);
        this.algoritmy.najkratsiaCestaLabelCorrect(zaciatocny, this.vrcholy, this.hrany);
    }

    public void najdiNajkratsiuCestuLabelSet(String zaciatok) {
        Vrchol zaciatocny = this.getVrchol(zaciatok);
        this.algoritmy.najkratsiaCestaLabelSet(zaciatocny, this.vrcholy, this.hrany);
    }

    public void najdiNajkratsiuCestuLabelSet(String zaciatok, String koniec) {
        Vrchol zaciatocny = this.getVrchol(zaciatok);
        Vrchol konecny = this.getVrchol(koniec);
        this.algoritmy.najkratsiaCestaLabelSet(zaciatocny, konecny, this.vrcholy, this.hrany);
    }

    public void najdiNajkratsiuCestuDjikstra(String zaciatok, String koniec) {
        Vrchol zaciatocny = this.getVrchol(zaciatok);
        Vrchol konecny = this.getVrchol(koniec);
        this.algoritmy.najkratsiaCestaDijkstra(zaciatocny, konecny, this.vrcholy, this.hrany);
    }

    public void najdiNajkratsiuCestu(String zaciatok, String koniec) {
        Vrchol zaciatocny = this.getVrchol(zaciatok);
        Vrchol konecny = this.getVrchol(koniec);
        this.algoritmy.najkratsiaCesta(zaciatocny, konecny, this.vrcholy, this.hrany);
    }

    public Vrchol pridajVrchol(String nazov) {
        Vrchol vrchol = new Vrchol(nazov);
        this.vrcholy.add(vrchol);
        this.pocetVrcholov++;
        return vrchol;
    }

    public void pridajHranu(Vrchol pociatocny, Vrchol koncovy, int cenaHrany) {
        Hrana hrana = new Hrana(pociatocny, koncovy, cenaHrany);
        pociatocny.pridajVychadzajuci(hrana);
        koncovy.pridajVchadzajuci(hrana);
        this.hrany.add(hrana);
        this.pocetHran++;
    }

    public void pridajHranuToku(Vrchol pociatocny, Vrchol koncovy, int cenaHrany, int tok) {
        Hrana hrana = new Hrana(pociatocny, koncovy, cenaHrany, tok);
        pociatocny.pridajVychadzajuci(hrana);
        koncovy.pridajVchadzajuci(hrana);
        this.hrany.add(hrana);
        this.pocetHran++;
    }

    public int getPocetVrcholov() {
        return this.pocetVrcholov;
    }

    public int getPocetHran() {
        return this.pocetHran;
    }

    public Vrchol getVrchol (String nazov) {
        for (Vrchol vrchol : this.vrcholy) {
            if (vrchol.getNazov().equals(nazov)) {
                return vrchol;
            }
        }
        return null;
    }

    public Vrchol getVrchol(Vrchol find) {
        for (Vrchol vrchol : this.vrcholy) {
            if (vrchol.getNazov().equals(find.getNazov())) {
                return vrchol;
            }
        }
        return null;
    }

    public ArrayList <Hrana> getHrany() {
        return this.hrany;
    }

    public ArrayList<Vrchol> getVrcholy () {
        return this.vrcholy;
    }


    public void reprezentaciaMnozinyHranAVrcholov() {
        System.out.println("Vrcholy :");
        for (Vrchol vrchol : this.vrcholy) {
            System.out.print(vrchol.getNazov() + " ");
        }
        System.out.println();
        System.out.println("Hrany :");
        for (Hrana hrana : this.hrany) {
            System.out.println(hrana.getPociatocny().getNazov() + " " + hrana.getKoncovy().getNazov());
        }
    }

    public void reprezentaciaMaticaOhodnoteniHran() {

        System.out.print("+");
        System.out.println("-----+".repeat(this.pocetVrcholov + 1));

        System.out.print("|  -  |");
        for (Vrchol vrchol : this.vrcholy) {
            System.out.printf("  %s  |", vrchol.getNazov());
        }

        System.out.println();

        for (Vrchol pociatocny : this.vrcholy) {
            System.out.print("|");
            System.out.println("-----+".repeat(this.pocetVrcholov + 1));
            System.out.printf("|  %s  |", pociatocny.getNazov());
            for (Vrchol koncovy : this.vrcholy) {
                if (pociatocny == koncovy) {
                    System.out.print("  -  |");
                } else if (pociatocny.maKHranu(koncovy)) {
                    System.out.printf("  %d  |", pociatocny.getKHrana(koncovy).getCenaHrany());
                } else {
                    System.out.print("  -  |");
                }
            }

            System.out.println();
        }

        System.out.print("+");
        System.out.println("-----+".repeat(this.pocetVrcholov + 1));
    }

    public void reprezentaciaMaticaPrilahlosti() {

        System.out.print("+");
        System.out.println("-----+".repeat(this.pocetVrcholov + 1));

        System.out.print("|  -  |");
        for (Vrchol vrchol : this.vrcholy) {
            System.out.printf("  %s  |", vrchol.getNazov());
        }

        System.out.println();

        for (Vrchol pociatocny : this.vrcholy) {
            System.out.print("|");
            System.out.println("-----+".repeat(this.pocetVrcholov + 1));
            System.out.printf("|  %s  |", pociatocny.getNazov());
            for (Vrchol koncovy : this.vrcholy) {
                if (pociatocny == koncovy) {
                    System.out.print("  -  |");
                } else if (pociatocny.maKHranu(koncovy)) {
                    System.out.printf("  %d  |", 1);
                } else {
                    System.out.print("  -  |");
                }
            }

            System.out.println();
        }

        System.out.print("+");
        System.out.println("-----+".repeat(this.pocetVrcholov + 1));
    }

    public void reprezentaciaZoznamomVrcholovOkoliaKazdehoVrchola() {

        System.out.print("+");
        System.out.println("-----+".repeat(this.pocetVrcholov + 1));

        for (Vrchol vrchol : this.vrcholy) {
            System.out.print("|V+(" + vrchol.getNazov() + "):");
            for (int i = 0; i < vrchol.getPocetVychadzajucichHran(); i++) {
                System.out.print("  " + vrchol.getKHrana(i).getKoncovy().getNazov() + "  ");
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.print("+");
        System.out.println("-----+".repeat(this.pocetVrcholov + 1));

    }

    public void reprezentaciaIncidencnouMaticouVrcholovAHran() {

        System.out.print("+");
        System.out.println("-----+".repeat(this.pocetHran + 1));

        System.out.print("|  -  |");
        for (Hrana hrana : this.hrany) {
            System.out.printf("{%s,%s}|", hrana.getPociatocny().getNazov(), hrana.getKoncovy().getNazov());
        }

        System.out.println();

        for (Vrchol vrchol : this.vrcholy) {
            System.out.print("|");
            System.out.println("-----+".repeat(this.pocetHran + 1));
            System.out.printf("|  %s  |", vrchol.getNazov());
            for (Hrana hrana : this.hrany) {
                if ((hrana.getPociatocny() == vrchol) || (hrana.getKoncovy() == vrchol)) {
                    System.out.print("  1  |");
                } else {
                    System.out.print("  -  |");
                }
            }
            System.out.println();
        }

        System.out.print("+");
        System.out.println("-----+".repeat(this.pocetHran + 1));
    }


}
