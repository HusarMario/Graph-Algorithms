import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("src/inputs/inputTok.txt");  // Zmena inputov
        InputToky inputToky = new InputToky(file);


        Graf graf = inputToky.nacitajGraf();
        //Graf graf = inputLoadEffective.nacitajGraf();
        //graf.reprezentaciaMaticaOhodnoteniHran();
        //System.out.println();
        //graf.najdiNajkratsiuCestu("a", "e");
        //System.out.println();
        //graf.najdiNajkratsiuCestuDjikstra("a", "e");
        //System.out.println();
        //graf.najdiNajkratsiuCestuLabelSet("a");
        //System.out.println();
        //graf.najdiNajkratsiuCestuLabelSet("1", "148");
        //graf.najdiNajkratsiuCestuLabelCorrect("a");

        //graf.najdiNajlacnejsiuKostru();
        //graf.najdiNajdrahsiuKostru();

        //graf.cpm();
        graf.tok();
    }
}
