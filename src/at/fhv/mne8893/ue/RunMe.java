package at.fhv.mne8893.ue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import at.fhv.mne8893.binarytree.TreeFactory;
import at.fhv.mne8893.binarytree.TreeNode;
import at.fhv.mne8893.binarytree.TreePrinter;
import at.fhv.mne8893.decoder.TreeDecoder;

public class RunMe {
    public static void main(String[] args) throws IOException {
        List<TreeNode<Integer>> treeNodes = TreeFactory.loadTreesFromFile("testData.txt");
        treeNodes.forEach(tree -> TreePrinter.printComplete(tree, System.out));
        System.out.println(treeNodes.get(0).calculateHeight());
        System.out.println(treeNodes.get(1).calculateHeight());

        TreeDecoder sampleDecoder = TreeDecoder.getSampleDecoder();
        System.out.println(sampleDecoder.decode("1011010011"));
        System.out.println(sampleDecoder.decode("11010010010101011"));
        System.out.println(sampleDecoder.decode("1000101111"));


        System.out.println("preorder");
        TreePrinter.printPreOrder(treeNodes.get(0), System.out);
        System.out.println("\r\npostorder");
        TreePrinter.printPostOrder(treeNodes.get(0), System.out);
        System.out.println("\r\ninorder");
        TreePrinter.printInOrder(treeNodes.get(0), System.out);
        System.out.println("\r\nllbyll");
        TreePrinter.printLevelByLevel(treeNodes.get(0),System.out);
        System.out.println("");

        treeNodes = TreeFactory.loadTreesFromFileAsBST("testData.txt");

        TreePrinter.printComplete(treeNodes.get(0), System.out);
        TreePrinter.printComplete(treeNodes.get(1), System.out);
        TreePrinter.printComplete(treeNodes.get(2), System.out);




    }
}
