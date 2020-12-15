package at.fhv.mne8893.binarytree;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Marion
 * @since 18.11.2020
 * @version 1.0
 */

public class TreeFactory {
    /**
     *
     * @param resourcePath File: "testData.txt" Methode loadTreesFromFile liest die Zeilen aus dem File.
     *                     Zeile für Zeile werden in die Liste lines geschrieben
     *                     nodelist ist eine Liste, die die Anzahl der Zeilen erhält -> im runme -> treenodes
     * @return Anzahl der Zeilen - > size 3
     * @throws IOException wird weiter geworfen
     */

    public static List<TreeNode<Integer>> loadTreesFromFile(String resourcePath) throws IOException {
        try {
            List<String> lines = getFileLines(resourcePath);
            List<TreeNode<Integer>> nodeList = new ArrayList<>();
            for (String line : lines) {
                nodeList.add(convertToTree(line));
            }
            return nodeList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param resourcePath File: "testData.txt" Methode loadTreesFromFile liest die Zeilen aus dem File.
     *                     Zeile für Zeile werden in die Liste lines geschrieben
     *                     nodelist ist eine Liste, die die Anzahl der Zeilen erhält -> im runme -> treenodes
     * @return Anzahl der Zeilen - > size 3
     * @throws IOException wird weiter geworfen
     */
    public static List<TreeNode<Integer>> loadTreesFromFileAsBST(String resourcePath) throws IOException {
        try {
            List<String> lines = getFileLines(resourcePath);
            List<TreeNode<Integer>> nodeList = new ArrayList<>();
            for (String line : lines) {
                nodeList.add(convertToBinarySearchTree(line, ";"));
            }
            return nodeList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param resourcePath Zeilen werden aus der Datei gelesen
     * @return readAllLines der Resource
     * @throws Exception "File not Found"
     */

    private static List<String> getFileLines(String resourcePath) throws Exception {
        URL resource = TreeFactory.class.getClassLoader().getResource(resourcePath); //Uniform resource locator
        if (resource == null)
            throw new IOException("File not Found");

        return Files.readAllLines(Path.of(resource.toURI()));
    }



    public static TreeNode<Integer> convertToTree(String dataSet) {
        return convertToTree(dataSet, ";");
    }


    public static TreeNode<Integer> convertToTree(String dataLine, String dividedBy) {
        return convertToTree(getIntegerDataSet(dataLine, dividedBy));
    }

    public static TreeNode<Integer> convertToBinarySearchTree(String dataLine, String dividedBy) {
        return convertToBinarySearchTree(getIntegerDataSet(dataLine, dividedBy), Comparator.comparingInt(i -> i));
    }

    /**
     *
     * @param dataLine die ausgewählte Zeile wird Übergeben und
     * @param dividedBy ";"
     * @return dataLine
     */
    private static Integer[] getIntegerDataSet(String dataLine, String dividedBy) {
        if (dataLine == null || dataLine.isEmpty())
            throw new IllegalArgumentException();

        if (dataLine.trim().isEmpty())
            return null;

        String[] splitData = dataLine.split(dividedBy); //splitData -> ";" wird entfernt
        Integer[] dataSet = new Integer[splitData.length]; // bekommt die Länge von splitData

        for (int i = 0; i < splitData.length; i++) {
            String trim = splitData[i].trim();
            if (trim.isEmpty())
                dataSet[i] = null;
            else
                dataSet[i] = Integer.parseInt(trim); // werte werden als Integer an dataSet übergeben
        }

        return dataSet;
    }

    //so wie werte aus der Arraylist nodes daher kommt, werden sie links und rechts angehängt

    private static <T> TreeNode<T> convertToTree(T[] dataSet) {
        if (dataSet.length == 0)
            return null;
        ArrayList<TreeNode<T>> nodes = new ArrayList<>(dataSet.length);
        for (T val : dataSet) {
            nodes.add(new TreeNode<T>(val)); // nodes werden erzeugt und werte hinein geschrieben
        }

        int count = 1; //startet bei eins, weil 0 ist root
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode<T> currentNode = nodes.get(i); //currentNode wird an die Kinder verteilt

            if (i + count < nodes.size()) {
                TreeNode<T> childLeft = nodes.get(i + count);
                currentNode.setChildLeft(childLeft); // linkes kind wird angehängt
            }
            count++;
            if (i + count < nodes.size()) {
                TreeNode<T> childRight = nodes.get(i + count);
                currentNode.setChildRight(childRight); //rechtes kind wird angehängt
            }

        }

        return nodes.get(0);
    }

    private static <T> TreeNode<T> convertToBinarySearchTree(T[] dataSet, Comparator<T> comp) {
        if (dataSet.length == 0) return null;

        // just append to rootnode as searchtree
        TreeNode<T> root = new TreeNode<>(dataSet[0]);
        for (int i = 1; i < dataSet.length; i++) {
            appendAsSearchTree(root, dataSet[i], comp);
        }

        // insert sorted in array and build tree from array
        /*
        TreeNode<T>[] nodes = new TreeNode[ (int)(dataSet.length* Math.pow(2, dataSet.length))];
         nodes[1]  = new TreeNode<T>(dataSet[0]);
        for (int i = 1; i < dataSet.length; i++) {
            TreeNode<T> node = new TreeNode<T>(dataSet[i]);
            insertAsSearchTree(nodes, node, comp);
        }


        int count = 1;
        for (int i = 1; i < nodes.length; i++) {
            TreeNode<T> currentNode = nodes[i];

            if (currentNode != null) {
                if (i + count < nodes.length) {
                    TreeNode<T> childLeft = nodes[i + count];
                    currentNode.setChildLeft(childLeft);
                }
                count++;
                if (i + count < nodes.length) {
                    TreeNode<T> childRight = nodes[i + count];
                    currentNode.setChildRight(childRight);
                }
            }

        }


         */
        // return node from array
        // return nodes[1];
        // return root from first method




        return root;

    }

    /*
    private static <T> void insertAsSearchTree(TreeNode<T>[] array, TreeNode<T> node, Comparator<T> comp) {
        boolean inserted = false;
        int i = 1;
        while (!inserted && i < array.length) {
            TreeNode<T> currentNode = array[i];
            if (currentNode != null) {
                if (comp.compare(currentNode.getValue(), node.getValue()) >= 0) {
                    i *= 2;
                } else {
                    i = i * 2 + 1;
                }
            } else {
                array[i]  = node;
                inserted = true;
            }
        }
    }

     */

    private static <T> void appendAsSearchTree(TreeNode<T> root, T value, Comparator<T> comp) {
        if (root == null) return;
        if (comp.compare(value, root.getValue()) > 0) {
            // insert right
            if (root.getChildRight() == null) {
                root.setChildRight(new TreeNode<>(value));
            } else {
                appendAsSearchTree(root.getChildRight(), value, comp);
            }
        } else {
            // insert left
            if (root.getChildLeft() == null) {
                root.setChildLeft(new TreeNode<>(value));
            } else {
                appendAsSearchTree(root.getChildLeft(), value, comp);
            }
        }
    }


}
