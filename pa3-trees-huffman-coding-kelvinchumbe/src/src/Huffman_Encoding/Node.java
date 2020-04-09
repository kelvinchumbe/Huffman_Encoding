package Huffman_Encoding;

public class Node {
    Integer frequency;
    String character;
    Node leftchild;
    Node rightchild;

    public Node(String character, Integer frequency){
        this.frequency = frequency;
        this.character = character;
        this.leftchild = null;
        this.rightchild = null;
    }

    public String toString(){
        return "[" + character + "," + frequency + "]";
    }
}
