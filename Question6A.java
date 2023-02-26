//6.A


import java.util.*;

public class Question6A {

    private static class Node implements Comparable<Node> {
        char symbol;
        int frequency;
        Node left;
        Node right;

        public Node(char symbol, int frequency) {
            this.symbol = symbol;
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.frequency, other.frequency);
        }
    }

    public static Map<Character, String> encode(String message) {
        // compute frequency of each character in the message
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : message.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // build Huffman tree
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (char symbol : frequencyMap.keySet()) {
            pq.offer(new Node(symbol, frequencyMap.get(symbol)));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.offer(parent);
        }
        Node root = pq.poll();

        // build encoding table
        Map<Character, String> encodingTable = new HashMap<>();
        buildEncodingTable(root, "", encodingTable);

        // encode the message using the encoding table
        Map<Character, String> encodedMessage = new HashMap<>();
        for (char c : message.toCharArray()) {
            encodedMessage.put(c, encodingTable.get(c));
        }

        return encodedMessage;
    }

    private static void buildEncodingTable(Node node, String code, Map<Character, String> encodingTable) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            encodingTable.put(node.symbol, code);
        }
        buildEncodingTable(node.left, code + "0", encodingTable);
        buildEncodingTable(node.right, code + "1", encodingTable);
    }

    public static String decode(Map<Character, String> encodingTable, String encodedMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();
        for (int i = 0; i < encodedMessage.length(); i++) {
            currentCode.append(encodedMessage.charAt(i));
            for (char symbol : encodingTable.keySet()) {
                if (encodingTable.get(symbol).equals(currentCode.toString())) {
                    decodedMessage.append(symbol);
                    currentCode.setLength(0);
                }
            }
        }
        return decodedMessage.toString();
    }

    public static void main(String[] args) {
        String message = "HELLO WORLD";
        Map<Character, String> encodingTable = encode(message);
        System.out.println(encodingTable);
        String encodedMessage = "";
        for (char c : message.toCharArray()) {
            encodedMessage += encodingTable.get(c);
        }
        System.out.println(encodedMessage);
        String decodedMessage = decode(encodingTable, encodedMessage);
        System.out.println(decodedMessage);
    }
}
