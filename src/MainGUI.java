import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class MainGUI extends JDialog {
    private JPanel contentPane;
    private JTextField filePathTextField;
    private JButton chooseFileButton;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton startDijkstraButton;

    private File fileForOperations;

    private MainGUI() {
        setModal(true);
        setContentPane(contentPane);
        filePathTextField.setEditable(false);
        inputTextArea.setEditable(false);
        outputTextArea.setEditable(false);
        inputTextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        outputTextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        initComponents();
        InputStreamReader in = new InputStreamReader(getClass().getResourceAsStream("Dijkstra-Test.txt"));
        this.loadTestFile(in);
        this.startTestDijkstra();
    }

    public static void main(String[] args) {
        setUIFlavour();
        JFrame frame = new JFrame("Dijkstra Test");
        frame.setContentPane(new MainGUI().contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void setUIFlavour() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileForOperations = fileChooser.getSelectedFile();
                    filePathTextField.setText(fileForOperations.getAbsolutePath());
                    StringBuilder sb = new StringBuilder();
                    try {
                        List<String> fileLines = Files.readAllLines(Paths.get(fileForOperations.getPath()));
                        for (String line : fileLines) {
                            sb.append(line).append("\n");
                        }
                        inputTextArea.setText(sb.toString());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        startDijkstraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Scanner sc = new Scanner(fileForOperations);
                    int n = sc.nextInt();
                    int[][] matrix = new int[n][n];
                    for (int i = 0; i < n; i++) {
                        int node = sc.nextInt();
                        int edges = sc.nextInt();
                        for (int j = 0; j < edges; j++) {
                            int adjacentNode = sc.nextInt();
                            int weight = sc.nextInt();
                            matrix[node][adjacentNode] = weight;
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(Dijkstra.doDijkstra(matrix));
                    outputTextArea.setText(sb.toString());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void loadTestFile(InputStreamReader in) {
        filePathTextField.setText("Built in test file from session 7 PDF");
        StringBuilder sb = new StringBuilder();
        Object[] fileLines = new BufferedReader(in).lines().toArray();
        for (Object line : fileLines) {
            sb.append(line).append("\n");
        }
        inputTextArea.setText(sb.toString());
    }

    private void startTestDijkstra() {
        int[][] matrix = new int[][] {
                {0, 10, 5, 0, 0},
                {0, 0, 2, 1, 0},
                {0, 3, 0, 9, 2},
                {0, 0, 0, 0, 4},
                {7, 0, 0, 6, 0}
        };

        StringBuilder sb = new StringBuilder();
        sb.append("This is a test run from session 7 PDF.\n");
        sb.append("Please note that we're looping through every node and treating is as a source\n");
        sb.append("The nodes are given IDs as follows:\n");
        sb.append("S -> 0\n");
        sb.append("T -> 1\n");
        sb.append("Y -> 2\n");
        sb.append("X -> 3\n");
        sb.append("Z -> 4\n\n");
        sb.append(Dijkstra.doDijkstra(matrix));
        outputTextArea.setText(sb.toString());
    }
}
