import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
    Wisielec wisielec = new Wisielec();
    wisielec.randomWordFromFile();
    do {
        wisielec.guessChar();
        wisielec.showWord();
    }while(!wisielec.isGameEnd());

    }
}