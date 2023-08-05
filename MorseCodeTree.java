

package cmsc256;
/*Braeden Ferguson
 *04/15/2023
 *Project06
 *CMSC256-901
 *Creating a tree using morse characters and finding different ways to transverse said tree
 */
import bridges.base.BSTElement;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;
import java.util.Scanner;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class MorseCodeTree {


    private BSTElement<String, MorseCharacter> root;

    public MorseCodeTree() {
        this.root = null;
    }

    public MorseCodeTree(BSTElement<String, MorseCharacter> root) {
        this.root = root;
    }

    public BSTElement<String, MorseCharacter> getRoot() {
        return this.root;
    }

    public void setRoot(BSTElement<String, MorseCharacter> root) {
        this.root = root;
    }



    public int getHeight(BSTElement<String, MorseCharacter> root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = getHeight(root.getLeft());
            int rightHeight = getHeight(root.getRight());

            return 1 + Math.max(leftHeight, rightHeight);
        }
    }

    public int getNumberOfNodes() {
        return getNumberOfNodes(this.root);
    }

    public void add(BSTElement<String, MorseCharacter> node) {
        String code = node.getKey();
        MorseCharacter character = node.getValue();

        BSTElement<String, MorseCharacter> current = root;
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (c == '.') {
                if (current.getLeft() == null) {
                    BSTElement<String, MorseCharacter> leftChild = new BSTElement<>("", "", new MorseCharacter());
                    current.setLeft(leftChild);
                }
                current = current.getLeft();
            } else if (c == '-') {
                if (current.getRight() == null) {
                    BSTElement<String, MorseCharacter> rightChild = new BSTElement<>("", "", new MorseCharacter());
                    current.setRight(rightChild);
                }
                current = current.getRight();
            }
        }

        current.setKey(code);
        current.setValue(character);
    }

    private int getNumberOfNodes(BSTElement<String, MorseCharacter> node) {
        if (node == null) {
            return 0;
        }
        return getNumberOfNodes(node.getLeft()) + getNumberOfNodes(node.getRight()) + 1;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void clear() {
        this.root = null;
    }


    public String encoded (Character character) throws IllegalArgumentException {
        if (character == null) {
            throw new IllegalArgumentException();
        }
        BSTElement currentNode = new BSTElement();
        currentNode = root;
        Stack nodeStack = new Stack();

        while (currentNode.getValue() != character) {
            if (currentNode.getLeft() != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeft();
            } else if (currentNode.getRight() != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.getRight();
            }
            else {
                nodeStack.pop();
                if (currentNode.getLeft() != null) {
                    nodeStack.pop();
                }
            }
        }
        if (currentNode.getKey() != null) {
            return currentNode.getKey().toString();
        } else {
            return null;
        }

    }

        public String encode (Character character) throws IllegalArgumentException{
        if (character == null) {
            throw new IllegalArgumentException();
        }
        try {
            switch (Character.toUpperCase(character)) {
                case 'A':
                    return ".-";
                case 'B':
                    return "-...";
                case 'C':
                    return "-.-.";
                case 'D':
                    return "-..";
                case 'E':
                    return ".";
                case 'F':
                    return "..-.";
                case 'G':
                    return "--.";
                case 'H':
                    return "....";
                case 'I':
                    return "..";
                case 'J':
                    return ".---";
                case 'K':
                    return "-.-";
                case 'L':
                    return ".-..";
                case 'M':
                    return "--";
                case 'N':
                    return "-.";
                case 'O':
                    return "---";
                case 'P':
                    return ".--.";
                case 'Q':
                    return "--.-";
                case 'R':
                    return ".-.";
                case 'S':
                    return "...";
                case 'T':
                    return "-";
                case 'U':
                    return "..-";
                case 'V':
                    return "...-";
                case 'W':
                    return ".--";
                case 'X':
                    return "-..-";
                case 'Y':
                    return "-.--";
                case 'Z':
                    return "--..";
                case '1':
                    return ".----";
                case '2':
                    return "..---";
                case '3':
                    return "...--";
                case '4':
                    return "....-";
                case '5':
                    return ".....";
                case '6':
                    return "-....";
                case '7':
                    return "--...";
                case '8':
                    return "---..";
                case '9':
                    return "----.";
                case '0':
                    return "-----";
                case '\'':
                    return ".----.";
                case '_':
                    return "..--.-";
                case '?':
                    return "..--..";
                case '-':
                    return "-....-";
                case ':':
                    return "-.-.-.";
                case '.':
                    return "--..--";
                case ';':
                    return "---...";
                case '!':
                    return ".-.-.-";
                case ',':
                    return "--..--";
                case '\"':
                    return ".-..-.";
            } }catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException();

            }
            return"";
        }



    public char decode(String code) throws IllegalArgumentException{
        if (code == null) {
            throw new IllegalArgumentException();
        }
        BSTElement<String, MorseCharacter> current = this.root;
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (c == '.') {
                current = current.getLeft();
            } else if (c == '-') {
                current = current.getRight();
            }
        }
        return current.getValue().getLetter();
    }

    public String inOrderTraversal(BSTElement<String, MorseCharacter> node) throws IllegalArgumentException{
            StringBuilder traversal = new StringBuilder();
            if (node == null) {
                return traversal.toString();
            }
            traversal.append(inOrderTraversal(node.getLeft()));
            traversal.append(node.getValue().getLetter()).append(" ");
            traversal.append(inOrderTraversal(node.getRight()));
            for (int k = 0; k < traversal.length(); k++) {
                if (traversal.charAt(k) == '\0') {
                    traversal.deleteCharAt(k);
                }
            }
            String finalString = "";
            for (int i = 0; i < traversal.length(); i++) {
                if (traversal.charAt(i) != ' ') {
                    finalString += traversal.charAt(i);
                }
            }
                return finalString;

    }

    public String levelOrderTraversal(BSTElement<String, MorseCharacter> node) {
        StringBuilder traversal = new StringBuilder();
        if (node == null) {
            return traversal.toString();
        }
        Queue<BSTElement<String, MorseCharacter>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            BSTElement<String, MorseCharacter> current = queue.poll();
            traversal.append(current.getValue().getLetter()).append(" ");
            if (current.getLeft() != null) {
                queue.offer(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.offer(current.getRight());
            }
        }
        return traversal.toString();
    }

    public String preOrderTraversal(BSTElement<String, MorseCharacter> node) {
        if (node == null) {
            return "";
        }
        String output = "";
        output += node.getValue().getLetter() + " ";
        output += preOrderTraversal(node.getLeft());
        output += preOrderTraversal(node.getRight());
        return output;
    }

    public MorseCharacter findMorseCharacter(String morse) {
        if (morse == null || morse.isEmpty()) {
            return null;
        }
        BSTElement<String, MorseCharacter> current = root;
        for (int i = 0; i < morse.length(); i++) {
            char c = morse.charAt(i);
            if (c == '.') {
                current = current.getLeft();
            } else if (c == '-') {
                current = current.getRight();
            } else {
                // Invalid Morse code character
                return null;
            }
            if (current == null) {
                // Reached a leaf node without finding a Morse character
                return null;
            }
        }
        return current.getValue();
    }
public static void main(String[] args) throws RateLimitException, IOException {
    Bridges bridges = new Bridges(6, "fergusonba", "757857963875");
    BSTElement<String, MorseCharacter> root = new BSTElement<>("Root", "", new MorseCharacter());
    MorseCodeTree tree = new MorseCodeTree(root);

    File file = new File("codeFile.dat");
    Scanner input = new Scanner(file);
    while (input.hasNextLine()) {
        String line = input.nextLine();
        String letter = line.substring(0, 1);
        String morseString = "";
        line = line.substring(2, line.length());
        while (line.isEmpty() != true) {
            morseString += line.charAt(0);
            line = line.substring(1, line.length());
        }
        MorseCharacter newMorseCharacter = new MorseCharacter(letter.charAt(0), morseString);
        BSTElement<String, MorseCharacter> node = new BSTElement<>(String.valueOf(newMorseCharacter.getLetter()), newMorseCharacter.getCode(), newMorseCharacter);
        tree.add(node);
    }
    bridges.setDataStructure(tree.getRoot());
    bridges.visualize();
    input.close();
    System.out.print(tree.inOrderTraversal(tree.getRoot()));
}
}
