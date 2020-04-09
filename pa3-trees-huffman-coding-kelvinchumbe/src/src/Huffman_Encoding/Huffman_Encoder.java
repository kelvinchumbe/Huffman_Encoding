package Huffman_Encoding;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Huffman_Encoder {
    public static String read_file(String filename) throws IOException {
        StringBuilder message = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String line;

        try {
//            message = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
            while((line = bufferedReader.readLine()) != null) {
               message.append(line );
               message.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(message);
    }

    //Time: Big O(1)
    //Space: Big O(1)
    //Auxillary Space: Big O(1)
    public static void write_file(String text, String path){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Time: Big O(N)
    //Space: Big O(N)
    //Auxillary Space: Big O(1)
    public static ArrayList<Node> generate_frequency_table(String message){
        ArrayList<Node> frequency_table = new ArrayList<>();
        String[] text_char_arr = message.split("");

        for (int i = 0; i < text_char_arr.length; i++) {
            int count = 1;
            boolean exists = false;

            for (int j = 0; j < text_char_arr.length; j++) {
                if (i == j) {

                } else if (text_char_arr[i].equals(text_char_arr[j])) {
                    for (int k = 0; k < frequency_table.size(); k++) {
                        if (frequency_table.get(k).character.equals(text_char_arr[i])) {
                            exists = true;
                            break;
                        }
                    }
                    count++;
                }
            }
            if (!exists) {
                frequency_table.add(new Node(text_char_arr[i], count));
            }
        }

        return frequency_table;
    }

    //Time: Big O(1)
    //Space: Big O(1)
    //Auxillary Space: Big O(1)
    public static ArrayList<Node> readfile_generate_freqtable(String filename){
        ArrayList<Node> freq_table = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));


            String line;
            while((line = reader.readLine()) != null){
                String[] arr = line.split(" ");
                freq_table.add(new Node(arr[0], Integer.parseInt(arr[1])));
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return freq_table;
    }

    //Time: Big O(N^2)
    //Space: Big O(N)
    //Auxillary Space: Big O(1)
    public static Huffman_Tree generate_huffman_tree(ArrayList<Node> frequency_table) {
        Huffman_Tree tree = new Huffman_Tree();

        while (!(frequency_table.size() == 1)) {                                  //condition might need to be updated to 1
            ArrayList<Node> least_freq_node = new ArrayList<>();

            for (int i = 0; i < 2; i++) {

                if (!(frequency_table.size() == 0)) {
                    Integer least = frequency_table.get(0).frequency;

                    for (int j = 0; j < frequency_table.size(); j++) {
                        if (frequency_table.get(j).frequency <= least) {
                            least = frequency_table.get(j).frequency;
                        }
                    }

                    for (int k = 0; k < frequency_table.size(); k++) {
                        if (frequency_table.get(k).frequency.equals(least)) {
                            least_freq_node.add(frequency_table.get(k));
                            frequency_table.remove(k);
                            break;
                        }
                    }
                }
            }
            Node node3 = new Node("", (least_freq_node.get(0).frequency + least_freq_node.get(1).frequency));
            node3.leftchild = least_freq_node.get(0);
            node3.rightchild = least_freq_node.get(1);
            frequency_table.add(node3);

            if(frequency_table.size() == 1){
                tree.setRoot(frequency_table.get(0));
            }
        }
        return tree;
    }
    //Time: Big O(logN)
    //Space: Big O(logN)
    //Auxillary Space: Big O(logN)
    public static ArrayList<String[]> generate_code_table(Node node, String enc, ArrayList<String[]> code_table1){

        if(node.leftchild != null){
            generate_code_table(node.leftchild,enc +"0", code_table1);
        }

        if(node.rightchild != null){
            generate_code_table(node.rightchild, enc + "1", code_table1);
        }

        if(node.leftchild == null && node.rightchild == null){
//            System.out.println(node.character + "," + enc);
            code_table1.add(new String[]{node.character, enc});
        }

        return code_table1;
    }

    //Time: Big O(N)
    //Space: Big O(N)
    //Auxillary Space: Big O(1)
    public static String encode_message(ArrayList<String[]> codetable, String message){
        String[] message_arr = message.split("");
        StringBuilder encoded_message = new StringBuilder();


        for(String charac: message_arr){
            for(int i=0; i < codetable.size(); i++){
                if(charac.equals(codetable.get(i)[0])){
                    encoded_message.append(codetable.get(i)[1]);
                }
            }
        }
        return String.valueOf(encoded_message);
    }

    //Time: Big O(N)
    //Space: Big O(N)
    //Auxillary Space: Big O(1)
    public static String decode_message(Node node, String encoded_message){
        Node current = node;
        StringBuilder decoded_message = new StringBuilder();

        for(char charac : encoded_message.toCharArray()) {
            if(charac == '0'){
                current = current.leftchild;
            }
            else if(charac == '1'){
                current = current.rightchild;
            }
            if(current.leftchild == null && current.rightchild == null){
                decoded_message.append(current.character);
                current = node;
            }
        }
        return String.valueOf(decoded_message);
    }

    public static void write_freqtable_file(ArrayList<Node> freqtable, String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(freqtable.get(0).character + " " + freqtable.get(0).frequency + "\n");
        for(int i=1; i < freqtable.size(); i++){
            if(!(freqtable.get(i).character.equals(" "))){
                fileWriter.append(freqtable.get(i).character + " " + freqtable.get(i).frequency + "\n");
            }
            else if(freqtable.get(i).character.equals(" ")){
                fileWriter.append(freqtable.get(i).character + freqtable.get(i).frequency + "\n");
            }

        }

        fileWriter.close();
    }

    public static void write_codetable_file(ArrayList<String[]> codetable, String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(codetable.get(0)[0] + " " + codetable.get(0)[1] + "\n");
        for(int i=0; i < codetable.size(); i++){
            fileWriter.append(codetable.get(i)[0] + " " + codetable.get(i)[1]);
            fileWriter.append('\n');
        }
        fileWriter.close();
    }
}