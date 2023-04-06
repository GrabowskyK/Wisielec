import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Wisielec {
    private String wordToGuess;
    private int[] letterAlreadyGuessed;
    private char[] arrayOfCharactersUsed = new char[26];
    private boolean isCharacterExist = false;
    Scanner scan = new Scanner(System.in);


    protected void newWord() {
        System.out.println("Give a new word");
        this.wordToGuess = scan.next();
    }

    protected void randomWordFromFile() throws FileNotFoundException {
        List<String> list = new ArrayList<String>();
        File file = new File("words.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) { //read from file to list
            String line = scan.nextLine();
            list.add(line);
        }

        double wordFromList = Math.floor(Math.random() * list.size()); //Random word from list
        this.wordToGuess = list.get((int) wordFromList).toUpperCase();

        this.letterAlreadyGuessed = new int[wordToGuess.length()];
    }

    protected void guessChar(){
        String character = "";
        System.out.println("Give the letter: ");
        do {
            character = scan.next();
            if (character.length() > 1){ //Missing checking that character is a number!
                System.out.println("Wrong input! Give one letter!");
            }
        }while(character.length() > 1);

        character = character.toUpperCase();
        char[] soloCharacter = character.toCharArray();
        int valueSoloCharacterInAscii = (int) soloCharacter[0]; //Save to array
        if(arrayOfCharactersUsed[valueSoloCharacterInAscii - 65] != 1) {
            arrayOfCharactersUsed[valueSoloCharacterInAscii - 65] = 1;
            checkInGuessWord(character.charAt(0));
        } else {
            System.out.println("You already used " + character);
            System.out.println("Give anoteher character!");
            guessChar();
        }

    }

    private void checkInGuessWord(char character){
        isCharacterExist = false;
        //System.out.println(character);
        for(int i=0;i<wordToGuess.length();i++) {
            if (wordToGuess.charAt(i) == character) {
                letterAlreadyGuessed[i] = 1;
                isCharacterExist = true;
            }
        }
        if(!isCharacterExist) {
            System.out.println("Mistake!");
            isCharacterExist = false;
        }else
            System.out.println("Exist!");
    }

    protected void showWord(){
        System.out.println("");
        System.out.println("Answear: ");
        for(int i=0;i<wordToGuess.length();i++){
            if(letterAlreadyGuessed[i] == 1){
                System.out.print(wordToGuess.charAt(i) + " ");
            } else {
                System.out.print(" _ ");
            }
        }
        System.out.println("");
    }

    protected boolean isGameEnd() {
        for (int i = 0; i < letterAlreadyGuessed.length; i++) {
            if (letterAlreadyGuessed[i] == 0) {
                return false;
            }
        }
        System.out.println("Gratulacje, wygrałeś!");
        return true;
        }
    }

