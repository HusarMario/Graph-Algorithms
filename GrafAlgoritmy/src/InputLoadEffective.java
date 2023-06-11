
import java.io.*;
import java.util.ArrayList;

/**
 * 14. 3. 2022 - 23:47
 *
 * @author user
 */

public class InputLoadEffective {
    private final BufferedReader bufferedReader;
    private final ArrayList<String> vrcholy;

    public InputLoadEffective(File file) throws FileNotFoundException {
        this.bufferedReader = new BufferedReader(new FileReader(file));
        this.vrcholy = new ArrayList<>();
    }

    public Graf nacitajGraf() throws IOException {
        Graf graf = new Graf();
        Vrchol zac;
        Vrchol kon;

        String line;
        while ((line = this.bufferedReader.readLine()) != null) {
            String [] values = line.split(" ");
            if (!this.vrcholy.contains(values[0])) {
                this.vrcholy.add(values[0]);
                zac = graf.pridajVrchol(values[0]);
            } else {
                zac = graf.getVrchol(values[0]);
            }
            if (!this.vrcholy.contains(values[1])) {
                this.vrcholy.add(values[1]);
                kon = graf.pridajVrchol(values[1]);
            } else {
                kon = graf.getVrchol(values[1]);
            }
            graf.pridajHranu(zac, kon, Integer.parseInt(values[2]));
        }

        return graf;
    }



}

