import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainGUI extends JDialog {
    private JPanel contentPane;
    private JTextField filePathTextField;
    private JButton chooseFileButton;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton startDijkstraIndomieButton;

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
    }

    public static void main(String[] args) {
        setUIFlavour();
        JFrame frame = new JFrame("AVL Dictionary");
        frame.setContentPane(new MainGUI().contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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

        startDijkstraIndomieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //start Dijkstra
            }
        });
    }

    private static void setUIFlavour() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
