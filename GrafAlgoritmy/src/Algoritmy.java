import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 14. 3. 2022 - 23:47
 *
 * @author user
 */
public class Algoritmy {

    public Algoritmy() {
    }

    public void tok (ArrayList<Vrchol> vrcholy, ArrayList<Hrana> hrany) {
        Vrchol zdroj = null;
        Vrchol ustie = null;
        for (Vrchol vrchol : vrcholy) {
            if (vrchol.getPocetVychadzajucichHran() == 0) {
                ustie = vrchol;
            }
            if (vrchol.getPocetVchadzajucichHran() == 0) {
                zdroj = vrchol;
            }
        }
        for (Hrana hrana : hrany) {
            System.out.println(hrana.getPociatocny().getNazov() + " " + hrana.getKoncovy().getNazov() + " " + hrana.getCenaHrany() + " " + hrana.getTok());
        }

        System.out.println(ustie.getNazov());
        System.out.println(zdroj.getNazov());

    }

    public void cpm (ArrayList<Vrchol> vrcholy, ArrayList<Hrana> hrany) {

        ArrayList<Integer> ceny = new ArrayList<>();
        try {

            Scanner scanner = new Scanner(new File("src/inputs/input5a.txt"));
            while (scanner.hasNextLine()) {
                ceny.add(Integer.parseInt(scanner.nextLine()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Vrchol vrchol : vrcholy) {
            vrchol.setCenaCinnosti(ceny.get(Integer.parseInt(vrchol.getNazov()) - 1));
        }
        // Priradenie ohodnotenie vrcholov

        /*System.out.println("ocenenie vrcholov");
        for (Vrchol vrchol : vrcholy) {
            System.out.println(vrchol.getNazov() + " " + vrchol.getCenaCinnosti());
        }
        System.out.println();
        System.out.println();*/


        ArrayList<Vrchol> mono = new ArrayList<>();
        for (Vrchol vrchol : vrcholy) {
            vrchol.setPocetVchadzajucichHranChangeable(vrchol.getPocetVchadzajucichHran());
            vrchol.setOcislovany(false);
            vrchol.setZaciatok(0);
            vrchol.setKoniec(0);
            vrchol.setKriticka(false);
        }

        while (true) {
            Vrchol riadiaci = null;
            for (Vrchol v : vrcholy) {
                if ((v.getPocetVchadzajucichHranChangeable() == 0) && (!v.isOcislovany())) {
                    riadiaci = v;
                }
            }

            if (vrcholy.size() == mono.size()) {
                break;
            }

            if (riadiaci != null) {
                riadiaci.setOcislovany(true);
                mono.add(riadiaci);
                for (int i = 0; i < riadiaci.getPocetVychadzajucichHran(); i++) {
                    riadiaci.getKHrana(i).getKoncovy().lowerPocetVchadzajucichHranChangeable();
                }
            }
        }       // Monotone ocislovanie

        System.out.println("Monotonne");
        for (Vrchol vrchol : mono) {
            System.out.print(vrchol.getNazov() + " ");
        }
        System.out.println();




        int dlzkaPrace = 0;
        for (Vrchol vrchol : mono) {
            //System.out.print(vrchol.getNazov() + " ");
            for (int i = 0; i < vrchol.getPocetVchadzajucichHran(); i++) {
                if (vrchol.getVHrana(i).getPociatocny().getZaciatok() > vrchol.getZaciatok()) {
                    vrchol.setZaciatok(vrchol.getVHrana(i).getPociatocny().getZaciatok());
                }
            }
            vrchol.setZaciatok(vrchol.getZaciatok() + vrchol.getCenaCinnosti());
            //System.out.println(vrchol.getNazov() + " " + vrchol.getZaciatok());
            if (vrchol.getZaciatok() > dlzkaPrace) {
                dlzkaPrace = vrchol.getZaciatok();
            }
        }

        //System.out.println();
        System.out.println("Dlzka prace je " + dlzkaPrace + ".");
        System.out.println();

        ArrayList<Vrchol> monoR = new ArrayList<>(mono);
        Collections.reverse(monoR);

        for (Vrchol vrchol : monoR) {
            //System.out.print(vrchol.getNazov() + " ");
            vrchol.setKoniec(dlzkaPrace);
            for (int i = 0; i < vrchol.getPocetVychadzajucichHran(); i++) {
                if (vrchol.getKHrana(i).getKoncovy().getKoniec() < vrchol.getKoniec()) {
                    vrchol.setKoniec(vrchol.getKHrana(i).getKoncovy().getKoniec());
                }
            }
            vrchol.setKoniec(vrchol.getKoniec() - vrchol.getCenaCinnosti());
            //System.out.println(vrchol.getNazov() + " " + vrchol.getKoniec());
            //System.out.print(vrchol.getKoniec() + " ");
            if (vrchol.getZaciatok() - vrchol.getKoniec() - vrchol.getCenaCinnosti() == 0) {
                vrchol.setKriticka(true);
                System.out.print(vrchol.getNazov() + " ");
                System.out.print("-> kriticka cinnost");
                System.out.println();
            }

        }


        Vrchol zaciatok = mono.get(0);
        Vrchol koniec = mono.get(mono.size() - 1);

        Graf novy = new Graf();
        //System.out.println();
        //System.out.println("Kriticke cinnosti:");
        for (Hrana hrana : hrany) {
            if (hrana.getPociatocny().isKriticka() && hrana.getKoncovy().isKriticka()) {
                if (novy.getVrchol(hrana.getPociatocny()) == null) {
                    novy.pridajVrchol(hrana.getPociatocny().getNazov());
                }
                if (novy.getVrchol(hrana.getKoncovy()) == null) {
                    novy.pridajVrchol(hrana.getKoncovy().getNazov());
                }
                novy.pridajHranu(novy.getVrchol(hrana.getPociatocny()), novy.getVrchol(hrana.getKoncovy()), hrana.getCenaHrany());
                //System.out.println(hrana.getPociatocny().getNazov() + " " + hrana.getKoncovy().getNazov() + " " + hrana.getCenaHrany());
            }
        }

        System.out.println();
        //novy.najdiNajkratsiuCestuLabelSet(zaciatok.getNazov(), koniec.getNazov());
    }

    @SuppressWarnings("checkstyle:ModifiedControlVariable")
    public void najdiKostru(boolean lacna, ArrayList<Vrchol> vrcholy, ArrayList<Hrana> hrany) {

        int index;  // Vypocet najnizsieho(najvyssieho) indexu
        if (lacna) {
            index = Integer.MAX_VALUE;
            for (Hrana hrana : hrany) {
                if (hrana.getCenaHrany() < index) {
                    index = hrana.getCenaHrany();
                }
            }
        } else {
            index = 0;
            for (Hrana hrana : hrany) {
                if (hrana.getCenaHrany() > index) {
                    index = hrana.getCenaHrany();
                }
            }
        }

        ArrayList<Hrana> usporiadaneHrany = new ArrayList<>();
        ArrayList<Hrana> pomocneHrany = new ArrayList<>(hrany);

        int nextIndex;
        while (!pomocneHrany.isEmpty()) {
            if (lacna) {
                nextIndex = Integer.MAX_VALUE;
            } else {
                nextIndex = 0;
            }
            for (int i = 0; i < pomocneHrany.size(); i++) {
                Hrana hrana = pomocneHrany.get(i);
                if (pomocneHrany.get(i).getCenaHrany() == index) {
                    pomocneHrany.remove(hrana);
                    usporiadaneHrany.add(hrana);
                    i = i - 1;  //Nepreskoci vymazanu hranu
                } else {
                    if (lacna) {
                        if (hrana.getCenaHrany() < nextIndex) {
                            nextIndex = hrana.getCenaHrany();
                        }
                    } else {
                        if (hrana.getCenaHrany() > nextIndex) {
                            nextIndex = hrana.getCenaHrany();
                        }
                    }
                }
            }
            index = nextIndex;
        }

        Graf graf = new Graf();
        ArrayList<Vrchol> vlozeneVrcholy = new ArrayList<>();
        int t = 0;
        for (Vrchol vrchol : vrcholy) {
            Vrchol input = graf.pridajVrchol(vrchol.getNazov());
            input.setT(t++);
            vlozeneVrcholy.add(input);
        }

        for (Hrana hrana : usporiadaneHrany) {
            if ((graf.getVrchol(hrana.getPociatocny()).getT()) != (graf.getVrchol(hrana.getKoncovy()).getT())) {
                graf.pridajHranu(graf.getVrchol(hrana.getPociatocny()), graf.getVrchol(hrana.getKoncovy()), hrana.getCenaHrany());
                int compare = graf.getVrchol(hrana.getKoncovy()).getT();
                for (Vrchol vrchol : vlozeneVrcholy) {
                    if (vrchol.getT() == compare) {
                        vrchol.setT(graf.getVrchol(hrana.getPociatocny()).getT());
                    }
                }
            }
        }

        //graf.reprezentaciaMaticaOhodnoteniHran();

        int count = 0;
        for (Hrana hrana : graf.getHrany()) {
            count += hrana.getCenaHrany();
        }
        System.out.println(count);



    }

    public void najkratsiaCestaLabelSet(Vrchol zaciatocny, ArrayList<Vrchol> vrcholy, ArrayList<Hrana> hrany) {

        for (Vrchol vrchol : vrcholy) {
            vrchol.setT(Integer.MAX_VALUE);
            vrchol.setX(null);
        }

        ArrayList<Vrchol> skumaneVrcholy = new ArrayList<>();   // Mnozina pre hladnie vrcholov
        Vrchol skumanyVrchol = null;    // Smernik
        zaciatocny.setT(0);
        skumaneVrcholy.add(zaciatocny);

        while (!skumaneVrcholy.isEmpty()) {     // Hlada vsetky cesty -- > v pripade ze by sme hladali len 1 konkretnu cestu mozeme pouzit verziu
                                                // kde sa cyklus zastavi v pripade ze je dosadeny koncovy bod

            for (Vrchol vrchol : skumaneVrcholy) {      // Hladanie najmensieho prvku rozlisuje labelset od labelcorrect
                if (skumanyVrchol == null) {
                    skumanyVrchol = vrchol;
                } else {
                    if (skumanyVrchol.getT() > vrchol.getT()) {     // Dosadenie akehokolvek nizssieho prvku (prvy najdeny)
                        skumanyVrchol = vrchol;
                    }
                }
            }

            skumaneVrcholy.remove(skumanyVrchol);       // Odstrani najdeny prvok zo skumanych vrcholov

            for (int i = 0; i < skumanyVrchol.getPocetVychadzajucichHran(); i++) {      // Prehladanie vsetkych hran a dosadnie novych hodnot
                if (skumanyVrchol.getKHrana(i).getKoncovy().getT() > skumanyVrchol.getT() + skumanyVrchol.getKHrana(i).getCenaHrany()) {
                    skumanyVrchol.getKHrana(i).getKoncovy().setT(skumanyVrchol.getT() + skumanyVrchol.getKHrana(i).getCenaHrany());
                    skumanyVrchol.getKHrana(i).getKoncovy().setX(skumanyVrchol);
                    skumaneVrcholy.add(skumanyVrchol.getKHrana(i).getKoncovy());
                }
            }
            skumanyVrchol = null;   // Odstrani prvok, aby cyklus pre hladanie najnizsieho prvku prebehol
        }

        System.out.println("Cesty z bodu " + zaciatocny.getNazov() + ":");
        for (Vrchol vrchol : vrcholy) {
            if (vrchol.getT() == Integer.MAX_VALUE) {
                System.out.println(vrchol.getNazov() + "(k vrcholu neexistuje cesta)");
            } else {
                System.out.print(vrchol.getNazov() + " cena(" + vrchol.getT() + ") : ");

                StringBuilder cesta = new StringBuilder();
                cesta.append(vrchol.getNazov()).append(" ");
                Vrchol help = vrchol;
                while (help.getX() != null) {
                    help = help.getX();
                    cesta.append(help.getNazov()).append(" ");
                }
                cesta.reverse();
                cesta.replace(0, 1, "");
                System.out.println(cesta);
            }

        }
    }

    public void najkratsiaCestaLabelSet(Vrchol zaciatocny, Vrchol konecny, ArrayList<Vrchol> vrcholy, ArrayList<Hrana> hrany) {

        for (Vrchol vrchol : vrcholy) {
            vrchol.setT(Integer.MAX_VALUE);
            vrchol.setX(null);
        }

        ArrayList<Vrchol> skumaneVrcholy = new ArrayList<>();   // Mnozina pre hladnie vrcholov
        Vrchol skumanyVrchol = null;    // Smernik
        zaciatocny.setT(0);
        skumaneVrcholy.add(zaciatocny);

        while (!skumaneVrcholy.isEmpty()) {     // Hlada vsetky cesty -- > v pripade ze by sme hladali len 1 konkretnu cestu mozeme pouzit verziu
                                                // kde sa cyklus zastavi v pripade ze je dosadeny koncovy bod

            for (Vrchol vrchol : skumaneVrcholy) {      // Hladanie najmensieho prvku rozlisuje labelset od labelcorrect
                if (skumanyVrchol == null) {
                    skumanyVrchol = vrchol;
                } else {
                    if (skumanyVrchol.getT() > vrchol.getT()) {     // Dosadenie akehokolvek nizssieho prvku (prvy najdeny)
                        skumanyVrchol = vrchol;
                    }
                }
            }

            skumaneVrcholy.remove(skumanyVrchol);       // Odstrani najdeny prvok zo skumanych vrcholov
            if (skumanyVrchol == konecny) {
                break;
            }

            for (int i = 0; i < skumanyVrchol.getPocetVychadzajucichHran(); i++) {      // Prehladanie vsetkych hran a dosadnie novych hodnot
                if (skumanyVrchol.getKHrana(i).getKoncovy().getT() > skumanyVrchol.getT() + skumanyVrchol.getKHrana(i).getCenaHrany()) {
                    skumanyVrchol.getKHrana(i).getKoncovy().setT(skumanyVrchol.getT() + skumanyVrchol.getKHrana(i).getCenaHrany());
                    skumanyVrchol.getKHrana(i).getKoncovy().setX(skumanyVrchol);
                    skumaneVrcholy.add(skumanyVrchol.getKHrana(i).getKoncovy());
                }
            }
            skumanyVrchol = null;   // Odstrani prvok, aby cyklus pre hladanie najnizsieho prvku prebehol
        }

        System.out.println("Cesta z bodu " + zaciatocny.getNazov() + " do " + konecny.getNazov());
        StringBuilder cesta = new StringBuilder();
        if (konecny.getT() == Integer.MAX_VALUE) {
            System.out.println("Neexistuje cesta");
        } else {
            Vrchol vrchol = konecny;
            cesta.append(vrchol.getNazov()).append(" ");
            while (vrchol.getX() != null) {
                vrchol = vrchol.getX();
                cesta.append(vrchol.getNazov()).append(" ");
            }
            cesta.reverse();
            cesta.replace(0, 1, "");
            System.out.println(cesta);
        }
        this.reset(vrcholy);
    }

    public void najkratsiaCestaLabelCorrect(Vrchol zaciatocny, ArrayList<Vrchol> vrcholy, ArrayList<Hrana> hrany) {

        for (Vrchol vrchol : vrcholy) {
            vrchol.setT(Integer.MAX_VALUE);
            vrchol.setX(null);
        }

        ArrayList<Vrchol> skumaneVrcholy = new ArrayList<>();
        Vrchol skumanyVrchol = null;
        zaciatocny.setT(0);
        skumaneVrcholy.add(zaciatocny);

        while (!skumaneVrcholy.isEmpty()) {     // Hlada vsetky cesty -- > v pripade ze by sme hladali len 1 konkretnu cestu mozeme pouzit verziu
            // kde sa cyklus zastavi v pripade ze je dosadeny koncovy bod

            skumanyVrchol = skumaneVrcholy.get(0);  // Hladanie najmensieho prvku rozlisuje labelset od labelcorrect
            skumaneVrcholy.remove(skumanyVrchol);

            for (int i = 0; i < skumanyVrchol.getPocetVychadzajucichHran(); i++) {
                if (skumanyVrchol.getKHrana(i).getKoncovy().getT() > skumanyVrchol.getT() + skumanyVrchol.getKHrana(i).getCenaHrany()) {
                    skumanyVrchol.getKHrana(i).getKoncovy().setT(skumanyVrchol.getT() + skumanyVrchol.getKHrana(i).getCenaHrany());
                    skumanyVrchol.getKHrana(i).getKoncovy().setX(skumanyVrchol);
                    skumaneVrcholy.add(skumanyVrchol.getKHrana(i).getKoncovy());
                }
            }
        }

        System.out.println("Cesty z bodu " + zaciatocny.getNazov() + ":");
        for (Vrchol vrchol : vrcholy) {
            if (vrchol.getT() == Integer.MAX_VALUE) {
                System.out.println(vrchol.getNazov() + "(k vrcholu neexistuje cesta)");
            } else {
                System.out.print(vrchol.getNazov() + " cena(" + vrchol.getT() + ") : ");

                StringBuilder cesta = new StringBuilder();
                cesta.append(vrchol.getNazov()).append(" ");
                Vrchol help = vrchol;
                while (help.getX() != null) {
                    help = help.getX();
                    cesta.append(help.getNazov()).append(" ");
                }
                cesta.reverse();
                cesta.replace(0, 1, "");
                System.out.println(cesta);
            }

        }
    }

    public void najkratsiaCestaDijkstra(Vrchol zaciatocny, Vrchol konecny, ArrayList<Vrchol> vrcholy, ArrayList<Hrana> hrany) {
        for (Vrchol vrchol : vrcholy) {
            vrchol.setT(Integer.MAX_VALUE);
            vrchol.setDefT(false);
            vrchol.setX(null);
        }

        zaciatocny.setT(0);
        Vrchol riadiaci = zaciatocny;
        riadiaci.setDefT(true);

        while (riadiaci != konecny) {
            for (int i = 0; i < riadiaci.getPocetVychadzajucichHran(); i++) {
                if (!(riadiaci.getKHrana(i).getKoncovy().isDefT())) {
                    if (riadiaci.getKHrana(i).getKoncovy().getT() > (riadiaci.getT() + riadiaci.getKHrana(i).getCenaHrany())) {
                        riadiaci.getKHrana(i).getKoncovy().setT(riadiaci.getT() + riadiaci.getKHrana(i).getCenaHrany());
                        riadiaci.getKHrana(i).getKoncovy().setX(riadiaci);
                    }
                }
            }

            boolean next = false;   //  Pomocna hodnota ,ktora zisti ak by konecny vrchol nebol dosiahnutelny z pociatocneho vrchola
            for (Vrchol vrchol : vrcholy) {
                if ((vrchol.getT() < Integer.MAX_VALUE) && (!vrchol.isDefT())) {
                    next = true;
                    if (riadiaci.isDefT()) {
                        riadiaci = vrchol;
                    } else {
                        if (vrchol.getT() < riadiaci.getT()) {
                            riadiaci = vrchol;
                        }
                    }
                }
            }
            if (!next) {
                break;
            }
            riadiaci.setDefT(true);
        }

        System.out.println("Cesta z bodu " + zaciatocny.getNazov() + " do " + konecny.getNazov());
        StringBuilder cesta = new StringBuilder();
        if (konecny.getT() == Integer.MAX_VALUE) {
            System.out.println("Neexistuje cesta");
        } else {
            Vrchol vrchol = konecny;
            cesta.append(vrchol.getNazov()).append(" ");
            while (vrchol.getX() != null) {
                vrchol = vrchol.getX();
                cesta.append(vrchol.getNazov()).append(" ");
            }
            cesta.reverse();
            cesta.replace(0, 1, "");
            System.out.println(cesta);
        }
        this.reset(vrcholy);

    }

    public void najkratsiaCesta(Vrchol zaciatocny, Vrchol konecny, ArrayList<Vrchol> vrcholy, ArrayList<Hrana> hrany) {
        for (Vrchol vrchol : vrcholy) {
            vrchol.setT(Integer.MAX_VALUE);
            vrchol.setX(null);
        }

        zaciatocny.setT(0);
        ArrayList<Hrana> skumaneHrany = new ArrayList<>();
        ArrayList<Hrana> hranyKPreskumaniu = new ArrayList<>();
        for (int i = 0; i < zaciatocny.getPocetVychadzajucichHran(); i++) {
            hranyKPreskumaniu.add(zaciatocny.getKHrana(i));
        }

        while (!hranyKPreskumaniu.isEmpty()) {
            skumaneHrany.addAll(hranyKPreskumaniu);
            hranyKPreskumaniu.clear();

            for (Hrana hrana : skumaneHrany) {
                if (hrana.getKoncovy().getT() > hrana.getPociatocny().getT() + hrana.getCenaHrany()) {
                    hrana.getKoncovy().setT(hrana.getPociatocny().getT() + hrana.getCenaHrany());
                    hrana.getKoncovy().setX(hrana.getPociatocny());
                    for (int i = 0; i < hrana.getKoncovy().getPocetVychadzajucichHran(); i++) {
                        hranyKPreskumaniu.add(hrana.getKoncovy().getKHrana(i));
                    }
                }
            }
        }

        System.out.println("Cesta z bodu " + zaciatocny.getNazov() + " do " + konecny.getNazov());
        StringBuilder cesta = new StringBuilder();
        if (konecny.getT() == Integer.MAX_VALUE) {
            System.out.println("Neexistuje cesta");
        } else {
            Vrchol vrchol = konecny;
            cesta.append(vrchol.getNazov()).append(" ");
            while (vrchol.getX() != null) {
                vrchol = vrchol.getX();
                cesta.append(vrchol.getNazov()).append(" ");
            }
            cesta.reverse();
            cesta.replace(0, 1, "");
            System.out.println(cesta);
        }


        this.reset(vrcholy);
    }

    private void reset(ArrayList<Vrchol> vrcholy) {
        for (Vrchol vrchol : vrcholy) {
            vrchol.setT(0);
            vrchol.setX(null);
        }
    }
}
