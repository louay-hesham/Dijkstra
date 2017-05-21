import javax.swing.*;

public class MainGUI extends JDialog {
    private JPanel contentPane;
    private JTextField filePathTextField;
    private JButton chooseFileButton;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton startDijkstraIndomieButton;

    public MainGUI() {
        setContentPane(contentPane);
        setModal(true);
    }


    public static void main(String[] args) {
        MainGUI dialog = new MainGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
