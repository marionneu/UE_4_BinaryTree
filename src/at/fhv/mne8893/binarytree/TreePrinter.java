package at.fhv.mne8893.binarytree;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreePrinter {
    public static <T> void printComplete(TreeNode<T> root, OutputStream output) {
        List<List<String>> lines = new ArrayList<>();
        List<TreeNode<T>> level = new ArrayList<>();
        List<TreeNode<T>> next = new ArrayList<>();
        PrintStream printStream = new PrintStream(output);

        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

            nn = 0;

            for (TreeNode<T> n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = "";
                    if (n.getValue() != null)
                        aa = n.getValue().toString();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getChildLeft());
                    next.add(n.getChildRight());

                    if (n.getChildLeft() != null) nn++;
                    if (n.getChildRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<TreeNode<T>> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int pieceSize = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(pieceSize / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (line.get(j) != null) c = '└';
                        }
                    }
                    printStream.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < pieceSize - 1; k++) {
                            printStream.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            printStream.print(j % 2 == 0 ? " " : "─");
                        }
                        printStream.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            printStream.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                printStream.println();
            }

            // print line of numbers
            for (String f : line) {

                if (f == null) f = "";
                int gap1 = (int) Math.ceil(pieceSize / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(pieceSize / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    printStream.print(" ");
                }
                printStream.print(f);
                for (int k = 0; k < gap2; k++) {
                    printStream.print(" ");
                }
            }
            printStream.println();

            pieceSize /= 2;
        }
    }

    public static <T> void printPreOrder(TreeNode<T> root, OutputStream output) {

        PrintStream printStream = new PrintStream(output);

        printPreOrder(root, printStream);
    }

    private static <T> void printPreOrder(TreeNode<T> root, PrintStream ps) {

        if (null == root) {
            return;
        }


        ps.print(root.getValue().toString() + ";");
        printPreOrder(root.getChildLeft(), ps);
        printPreOrder(root.getChildRight(), ps);


    }

    public static <T> void printPostOrder(TreeNode<T> root, OutputStream output) {

        PrintStream printStream = new PrintStream(output);

        printPostOrder(root, printStream);
    }

    private static <T> void printPostOrder(TreeNode<T> root, PrintStream ps) {

        if (null == root) {
            return;
        }


        printPostOrder(root.getChildLeft(), ps);
        printPostOrder(root.getChildRight(), ps);
        ps.print(root.getValue() +";");


    }

    public static <T> void printInOrder(TreeNode<T> root, OutputStream output) {

        PrintStream printStream = new PrintStream(output);

        printInOrder(root, printStream);
    }

    private static <T> void printInOrder(TreeNode<T> root, PrintStream ps) {

        if (null == root) {
            return;
        }
        printInOrder(root.getChildLeft(), ps);
        ps.print(root.getValue() + ";");
        printInOrder(root.getChildRight(), ps);
    }

    public static <T> void printLevelByLevel(TreeNode<T> root, OutputStream output) {
        PrintStream printStream = new PrintStream(output);
        Queue<TreeNode<T>> queue = new LinkedList<>();

        if(null == root){
            return;
        }
       queue.add(root);

        while (true){
            int nodeCount = queue.size();
            if(nodeCount == 0){
                break;
            }
            while (nodeCount > 0){
                TreeNode<T> node = queue.peek();
                printStream.print(node.getValue() +";");
                queue.remove();
                if(node.getChildLeft() != null){
                    queue.add(node.getChildLeft());
                }
                if(node.getChildRight() != null){
                    queue.add(node.getChildRight());
                }
                nodeCount--;

            }
        }



        }
    }



