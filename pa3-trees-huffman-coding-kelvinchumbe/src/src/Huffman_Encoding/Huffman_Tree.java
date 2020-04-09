package Huffman_Encoding;

public class Huffman_Tree {
    Node root;

    public Huffman_Tree(){
        root = null;
    }

    public Node getRoot(){
        return this.root;
    }

    public void setRoot(Node newroot){
        this.root = newroot;
    }
}
