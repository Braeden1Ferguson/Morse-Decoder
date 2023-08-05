package cmsc256;


public class MorseCharacter implements Comparable<MorseCharacter> {
    char character;
    String code;

    public char getLetter() {
        return character;
    }

    public String getCode() {
        return code;
    }

    public void setLetter(char aCharacter) {
        character = aCharacter;
    }
    public MorseCharacter() {
        this.code = "";
        this.character = '\0';
    }


    public MorseCharacter(char aCharacter, String aCode) {
        this.character = aCharacter;
        this.code = aCode;
    }



    public String toString() {
        return Character.toString(character);
    }







    public boolean equals(MorseCharacter other) throws IllegalArgumentException{
        boolean result = true;//if everything is equal this will never change
        if (code.equals(null) || other.getCode().equals(null)) {//checking if either values are null
            throw new IllegalArgumentException();
        }
        else {
            //getting the strings
            String currentCode = code;
            String otherCode = other.getCode();
            //if they aren't the same length we know they aren't equal
            if (currentCode.length() == otherCode.length()) {
                for (int k = 0; k < code.length() - 1; k++) {
                    if (code.charAt(k) != other.getCode().charAt(k)) {//only changes to false if not equal
                        result = false;
                    }
                }
            } else {result = false;}
        }
        return result;
    }

    public int compareTo(MorseCharacter other) throws IllegalArgumentException {

        //if either value is null
        if (other == null) {
            throw new IllegalArgumentException();
        } else {

            String currentCode = this.code;
            //if they are equal automatically return 0
            String otherCode = other.getCode();
            if (currentCode.equals(otherCode)) {
                return 0;
            }

            int result = 0;
            int currentIndex = 0;
            //while result hasn't changed we check if the index is beyond the length of the string
            while (result == 0 && currentIndex < currentCode.length() && currentIndex < otherCode.length()) {
                //just because I didn't feel like putting in a second else if statement

                //if they are equal we instantly get out of this index
                if (currentCode.charAt(currentIndex) == otherCode.charAt(currentIndex)) {
                    result = 0;

                    //since our current node is a ".", the and we are comparing it to "-", current is smaller
                } else if (currentCode.charAt(currentIndex) == '.' && otherCode.charAt(currentIndex) == '-') {
                    result = -1;
                } else {
                    result = 1;
                }
                //increase our index
                currentIndex++;
            }
            //this is lowkey repeated code but if they are equal return 0
            if (result == 0 && currentCode.length() == otherCode.length()) {
                return result;
            }
            //if we got out due to the length of the strings being different we return the result so far if it's changed
            if (result != 0 || currentCode.length() == otherCode.length()) {
                return result;
            } else {
                //if everything is the same up to the end of one string, so if the larger string
                //is our current, we should always get a positive value
                if (currentCode.length() - otherCode.length() > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }
}
