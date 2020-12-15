package at.fhv.mne8893.decoder;

import at.fhv.mne8893.binarytree.TreeNode;
import at.fhv.mne8893.binarytree.TreePrinter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TreeDecoder {
    private TreeNode<String> root;

    public TreeDecoder() {
        generateDecoderTree(10);
    }

    public TreeDecoder(int seed) {
        generateDecoderTree(seed);
    }

    public static TreeDecoder getSampleDecoder() {
        TreeDecoder treeDecoder = new TreeDecoder();
        treeDecoder.root = new TreeNode<>("");
        var node2 = new TreeNode<>("E");
        var node3 = new TreeNode<>("");
        treeDecoder.root.setChildRight(node2);
        treeDecoder.root.setChildLeft(node3);

        var node4 = new TreeNode<>("T");
        var node5 = new TreeNode<>("");
        node3.setChildLeft(node4);
        node3.setChildRight(node5);

        var node6 = new TreeNode<>("N");
        var node7 = new TreeNode<>("");
        node5.setChildRight(node6);
        node5.setChildLeft(node7);

        var node8 = new TreeNode<>("S");
        var node9 = new TreeNode<>("I");
        node7.setChildRight(node9);
        node7.setChildLeft(node8);

        return treeDecoder;
    }

    private void shuffleArray(int seed, String[] values) {
        Random random = new Random(seed);
        List<String> list = Arrays.asList(values);
        Collections.shuffle(list, random);
        list.toArray(values);
    }

    private void generateDecoderTree(int seed) {
        String[] strings = new String[26];

        for (int i = 0; i < strings.length; i++) {
            char val = 'A';
            val += i;
            strings[i] = new String(new char[]{val});
        }

        shuffleArray(seed, strings);

        boolean attacheLeft = true;
        root = new TreeNode<>("");

        TreeNode<String> currentNode = root;
        for (int i = 0; i < strings.length - 1; i++) {
            var leftNode = new TreeNode<>(strings[i]);
            var rightNode = new TreeNode<>(strings[i]);

            currentNode.setChildLeft(leftNode);
            currentNode.setChildRight(rightNode);

            if (i == strings.length - 2)
                return;

            if (attacheLeft)
                currentNode = leftNode;
            else
                currentNode = rightNode;

            attacheLeft = !attacheLeft;
        }
    }

    public void print() {
        TreePrinter.printComplete(root, System.out);
    }

    public String decode(String values) {
        // 1011010011
        char[] chars = values.toCharArray();
        StringBuilder builder = new StringBuilder();

        TreeNode<String> currentNode = root;
        for (char dir : chars) {
            if (currentNode.isLeave()) {
                builder.append(currentNode.getValue());
                currentNode = root;
            }
            if (dir == '1')
                currentNode = currentNode.getChildLeft();
            else
                currentNode = currentNode.getChildRight();
        }
        if (currentNode.isLeave()) {
            builder.append(currentNode.getValue());
        }

        return builder.toString();
    }
}
