/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Master
 */
import java.nio.file.*;import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


//import java.util.Scanner;
public class SyllableCounter {
    //get the appropriate file
    public static String readFileAsString(String fileName)throws Exception
  {
    String data;
    data = new String(Files.readAllBytes(Paths.get(fileName)));
    return data;
  }
    
    public static String[] arrayOfWordsAndPronunciations(String letter) throws Exception{
        if (letter.equals("")){
            letter = "2";
        }
        String[] arrayOfWords = readFileAsString("words"+letter.toLowerCase()+".txt").split("\n");
        return arrayOfWords;
    }
    
    public static List DictionaryOfWords() throws Exception{
        
        List <String> dictionary = DictionaryOfWords("");
        return dictionary;
    }
    //magically get every word/sound pair into a single ordered list
    public static List DictionaryOfWords(String letter) throws Exception{
        String[] wordsList = arrayOfWordsAndPronunciations(letter);
        List <String> dictionary = new ArrayList();
        String doubleSpace = "  ";
        for (int i=0; i<(wordsList.length*2); i+=2){
            
            String Key = wordsList[i/2].substring(0, wordsList[i/2].indexOf(doubleSpace ));
            String Value = wordsList[i/2].substring(wordsList[i/2].indexOf(doubleSpace )+2);
            dictionary.add(i, Key);
            dictionary.add(i+1, Value);
        }
    return dictionary;
    }
    //unused, but I'm proud
    public static int SyllableCount(Object word) throws Exception{
        
        List <String> Dictionary = DictionaryOfWords();
        int indexOfWord = Dictionary.indexOf(word);
        Object phoneme = Dictionary.get(indexOfWord+1);
        String value = phoneme.toString();
        int syllableCount = 0;
        for (char i : value.toCharArray() ){
            if(Character.isDigit(i)){
            syllableCount+=1;
            }
        }
        return syllableCount;
    }
    //make the corresponding sounds to the user's word into a list for comparison
    public static List SplitUserSounds(Object word) throws Exception{
        String letter = word.toString().substring(0, 1);
        List <String> Dictionary = DictionaryOfWords(letter);
        int indexOfWord = Dictionary.indexOf(word);
        String [] splitSoundArray = (Dictionary.get(indexOfWord+1)).split(" ");
        List <String> splitSound = Arrays.asList(splitSoundArray);
        System.out.println(splitSound.toString());
        return splitSound;
    }
    //do the same thing again for the words that are being compared from the file
    public static List SplitDictionarySounds(int indexOfWord, String letter) throws Exception{
        List <String> Dictionary = DictionaryOfWords(letter);
        String [] splitSoundArray = (Dictionary.get(indexOfWord+1)).split(" ");
        List <String> splitSound = Arrays.asList(splitSoundArray);
        return splitSound;
    }
    //Determine which sound list is bigger to prevent out of bounds indexing
    public static Boolean dictionaryIsBigger(int userSoundCount, int dictionarySoundCount){
        return userSoundCount<=dictionarySoundCount;
    }
    //filter for alpha chararters at the start of a string
    public static boolean isAlpha(String s)
    {
        if (s == null) {
            return false;
        }
            char c = s.charAt(0);
        return !(!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z'));
    }
    //hard work done here
    public static List RhymingList(String letter, String userWord) throws Exception{
        
        List <String> subDictionary = DictionaryOfWords(letter);
        List <String> rhymingList = new ArrayList();
        List userListOfSounds = SplitUserSounds(userWord.toUpperCase());
        int userSoundCount = userListOfSounds.size();
        
        for (int i = 0; i<subDictionary.size(); i+=2){
            
            List dictionaryListOfSounds = SplitDictionarySounds(i, letter);
            int dictionarySoundCount = dictionaryListOfSounds.size();
            Boolean dictIsBigger = dictionaryIsBigger(userSoundCount, dictionarySoundCount);
            int smallOffset;
            int bigOffset;
            int dictOffset1;
            int dictOffset2;
            int dictOffset3;
            int dictOffset4;
            int userOffset1;
            int userOffset2;
            int userOffset3;
            int userOffset4;
            
            if (dictIsBigger == true && (dictionarySoundCount>1 && userSoundCount>1)){
                bigOffset = dictionarySoundCount-1;
                smallOffset = userSoundCount-1;
                if (smallOffset>=5){
                    dictOffset1 = bigOffset-1;
                    dictOffset2 = bigOffset-2;
                    dictOffset3 = bigOffset-3;
                    dictOffset4 = bigOffset-4;
                    userOffset1 = smallOffset-1;
                    userOffset2 = smallOffset-2;
                    userOffset3 = smallOffset-3;
                    userOffset4 = smallOffset-4;
                }
                else if(smallOffset == 4){
                    dictOffset1 = bigOffset;
                    dictOffset2 = bigOffset-1;
                    dictOffset3 = bigOffset-2;
                    dictOffset4 = bigOffset-3;
                    userOffset1 = smallOffset;
                    userOffset2 = smallOffset-1;
                    userOffset3 = smallOffset-2;
                    userOffset4 = smallOffset-3;
                }
                else if(smallOffset == 3){
                    dictOffset1 = bigOffset;
                    dictOffset2 = bigOffset;
                    dictOffset3 = bigOffset-1;
                    dictOffset4 = bigOffset-2;
                    userOffset1 = smallOffset;
                    userOffset2 = smallOffset;
                    userOffset3 = smallOffset-1;
                    userOffset4 = smallOffset-2;
                }
                else/* if(smallOffset == 2)*/{
                    dictOffset1 = bigOffset;
                    dictOffset2 = bigOffset;
                    dictOffset3 = bigOffset;
                    dictOffset4 = bigOffset-1;
                    userOffset1 = smallOffset;
                    userOffset2 = smallOffset;
                    userOffset3 = smallOffset;
                    userOffset4 = smallOffset-1;
                }
                /*else{
                dictOffset1 = bigOffset;
                dictOffset2 = bigOffset;
                dictOffset3 = bigOffset;
                dictOffset4 = bigOffset;
                userOffset1 = smallOffset;
                userOffset2 = smallOffset;
                userOffset3 = smallOffset;
                userOffset4 = smallOffset;
                }*/
            }
            else if (dictIsBigger == false && (userSoundCount>1 && dictionarySoundCount>1)) {
                bigOffset = userSoundCount-1;
                smallOffset = dictionarySoundCount-1;
                if (smallOffset>=5){
                    dictOffset1 = smallOffset-1;
                    dictOffset2 = smallOffset-2;
                    dictOffset3 = smallOffset-3;
                    dictOffset4 = smallOffset-4;
                    userOffset1 = bigOffset-1;
                    userOffset2 = bigOffset-2;
                    userOffset3 = bigOffset-3;
                    userOffset4 = bigOffset-4;
                }
                else if(smallOffset == 4){
                    dictOffset1 = smallOffset;
                    dictOffset2 = smallOffset-1;
                    dictOffset3 = smallOffset-2;
                    dictOffset4 = smallOffset-3;
                    userOffset1 = bigOffset;
                    userOffset2 = bigOffset-1;
                    userOffset3 = bigOffset-2;
                    userOffset4 = bigOffset-3;
                }
                else if(smallOffset == 3){
                    dictOffset1 = smallOffset;
                    dictOffset2 = smallOffset;
                    dictOffset3 = smallOffset-1;
                    dictOffset4 = smallOffset-2;
                    userOffset1 = bigOffset;
                    userOffset2 = bigOffset;
                    userOffset3 = bigOffset-1;
                    userOffset4 = bigOffset-2;
                }
                else/* if(smallOffset == 2)*/{
                    dictOffset1 = smallOffset;
                    dictOffset2 = smallOffset;
                    dictOffset3 = smallOffset;
                    dictOffset4 = smallOffset-1;
                    userOffset1 = bigOffset;
                    userOffset2 = bigOffset;
                    userOffset3 = bigOffset;
                    userOffset4 = bigOffset-1;
                }
                /*else{
                dictOffset1 = smallOffset;
                dictOffset2 = smallOffset;
                dictOffset3 = smallOffset;
                dictOffset4 = smallOffset;
                userOffset1 = bigOffset;
                userOffset2 = bigOffset;
                userOffset3 = bigOffset;
                userOffset4 = bigOffset;
                }*/
            }
            else if(dictIsBigger == false){
                bigOffset = userSoundCount-1;
                smallOffset = dictionarySoundCount-1;
                dictOffset1 = smallOffset;
                dictOffset2 = smallOffset;
                dictOffset3 = smallOffset;
                dictOffset4 = smallOffset;
                userOffset1 = bigOffset;
                userOffset2 = bigOffset;
                userOffset3 = bigOffset;
                userOffset4 = bigOffset;
            }
            else{
                bigOffset = dictionarySoundCount-1;
                smallOffset = userSoundCount-1;
                dictOffset1 = bigOffset;
                dictOffset2 = bigOffset;
                dictOffset3 = bigOffset;
                dictOffset4 = bigOffset;
                userOffset1 = smallOffset;
                userOffset2 = smallOffset;
                userOffset3 = smallOffset;
                userOffset4 = smallOffset;
            }
                if (dictOffset1>0 
                        && (dictionaryListOfSounds.get(dictOffset1).toString().equals(userListOfSounds.get(userOffset1).toString()))
                        && (dictionaryListOfSounds.get(dictOffset2).toString().equals(userListOfSounds.get(userOffset2).toString()))
                        && (dictionaryListOfSounds.get(dictOffset3).toString().equals(userListOfSounds.get(userOffset3).toString()))
                        && (dictionaryListOfSounds.get(dictOffset4).toString().equals(userListOfSounds.get(userOffset4).toString()))){
                    rhymingList.add(subDictionary.get(i));
            }
            }
        return rhymingList;
    }
    
    public static void GetUserInput() throws Exception{
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("First, we'll need too get the word you want to rhyme:");
        String userWord = keyboard.next();
        System.out.println("Next, we'll need to limit the scope for time."+"\n"+
                "Please enter a single letter to find words that start with that letter."+"\n"+
                "If you want to wait for 3+ hrs, just enter 2, and I'll search all of "+
                "the words I know!");
        String userLetter = keyboard.next();
        if(isAlpha(userWord) && (isAlpha(userLetter)|| userLetter.matches("2")) && userLetter.length() == 1){
        System.out.println(RhymingList(userLetter, userWord));
        }
        else{
            System.out.println("OOf! Try again!\nAlphabetic first characters only this time.");
            GetUserInput();
        }
    }
    
    public static void main(String[] args) {
        
        try {
            GetUserInput();
        } catch (Exception ex) {
            Logger.getLogger(SyllableCounter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
