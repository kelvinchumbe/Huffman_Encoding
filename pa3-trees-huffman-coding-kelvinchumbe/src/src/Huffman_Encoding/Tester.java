package Huffman_Encoding;

import java.util.ArrayList;

import static Huffman_Encoding.Huffman_Encoder.*;

public class Tester {
    public static void main(String[] args) throws Exception {

        //Read Test File and Store message in as a String variable. Print message to screen and write it to file
        String message = read_file("C:/Users/Kelvin Kinda/IdeaProjects/pa3-trees-huffman-coding-kelvinchumbe/src/src/Huffman_Encoding/test_input");
        System.out.println(message);
        write_file(message, "C:/Users/Kelvin Kinda/IdeaProjects/pa3-trees-huffman-coding-kelvinchumbe/src/src/Huffman_Encoding/KelvinChumbe_TestInput.txt");


        //Generate a frequency table from the message. Store the character frequencies as an arraylist of nodes. Write the frequency table to file amd print to screen
        ArrayList<Node> huffman_subtree = generate_frequency_table(message);
        write_freqtable_file(huffman_subtree, "C:/Users/Kelvin Kinda/IdeaProjects/pa3-trees-huffman-coding-kelvinchumbe/src/src/Huffman_Encoding/KelvinChumbe_FrequencyTable.txt");

        System.out.println(huffman_subtree);

        //create a huffman tree from the frequcny table generated
        Huffman_Tree huffman_tree = generate_huffman_tree(huffman_subtree);

        //Create empty codetable as an arraylist of String arrays.
        ArrayList<String[]> code_table = new ArrayList<>();

        //Initialize the code before traversing the tree and generating the codes recursively
        String code = "";

        //Update the generated code table and write to file
        code_table = generate_code_table(huffman_tree.getRoot(), code, code_table);
        write_codetable_file(code_table, "C:/Users/Kelvin Kinda/IdeaProjects/pa3-trees-huffman-coding-kelvinchumbe/src/src/Huffman_Encoding/KelvinChumbe_CodeTable.txt");

        //Encode the message based on the code table. Print the encoded message to screen and write to file
        String encoded = encode_message(code_table, message);
        System.out.println(encoded);
        write_file(encoded, "C:/Users/Kelvin Kinda/IdeaProjects/pa3-trees-huffman-coding-kelvinchumbe/src/src/Huffman_Encoding/KelvinChumbe_EncodedMessage.txt");


        System.out.println("_______________________________________");

        ArrayList<Node> freq_table = readfile_generate_freqtable("C:/Users/Kelvin Kinda/IdeaProjects/pa3-trees-huffman-coding-kelvinchumbe/src/src/Huffman_Encoding/frequency_table_input.txt");
//        System.out.println(freq_table);

        Huffman_Tree tree2 = generate_huffman_tree(freq_table);
        ArrayList<String[]> code_table2 = new ArrayList<>();

        String secret_encoded = read_file("C:/Users/Kelvin Kinda/IdeaProjects/pa3-trees-huffman-coding-kelvinchumbe/src/src/Huffman_Encoding/secret_message.txt");

        String code2 = "";

        code_table2 = generate_code_table(tree2.getRoot(), code2, code_table2);

//        System.out.println(secret_encoded);
//
//        String secret_decoded = decode_message(tree2.getRoot(), secret_encoded);
//        System.out.println(secret_decoded);
//        write_file(secret_decoded, "C:/Users/Kelvin Kinda/IdeaProjects/pa3-trees-huffman-coding-kelvinchumbe/src/src/Huffman_Encoding/KelvinChumbe_ClassDecodedMessage.txt");
//

        String secret_encoded_message = read_file("C:/Users/Kelvin Kinda/IdeaProjects/pa3-trees-huffman-coding-kelvinchumbe/src/src/Huffman_Encoding/secret_message.txt");
        String second_decoded_message = decode_message(huffman_tree.getRoot(), secret_encoded_message);
        System.out.println(second_decoded_message);
        write_file(second_decoded_message, "C:/Users/Kelvin Kinda/IdeaProjects/pa3-trees-huffman-coding-kelvinchumbe/src/src/Huffman_Encoding/KelvinChumbe_ClassDecodedMessage.txt");

    }
}
