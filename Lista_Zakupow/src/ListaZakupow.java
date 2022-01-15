import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.filechooser.*;

public class ListaZakupow {
    private JPanel mainPanel;
    private JPanel cardPanel;
    private JMenuItem mainMenuItem;
    private JMenuItem infoMenuItem;
    private JMenuItem helpMenuItem;
    private JMenuItem exitMenuItem;
    private JPanel menuCard;
    private JPanel inputCard;
    private JPanel loadingCard;
    private JPanel outputCard;
    private JPanel infoCard;
    private JPanel helpCard;
    private JLabel clockLabel;
    private JButton chooseFilesButton;
    private File[] files;
    private ArrayList<String> input;
    CardLayout cl = new CardLayout();

    public ListaZakupow() {
        cardPanel.setLayout(cl);
        inputCard.setBackground(Color.green);
        menuCard.setBackground(Color.yellow);
        infoCard.setBackground(Color.blue);
        helpCard.setBackground(Color.red);
        cardPanel.add(menuCard, "1");
        cardPanel.add(inputCard, "2");
        cardPanel.add(loadingCard, "3");
        cardPanel.add(outputCard, "4");
        cardPanel.add(infoCard, "5");
        cardPanel.add(helpCard, "6");
        cl.show(cardPanel, "1");

        startClock();

        mainMenuItem.addActionListener(e -> cl.show(cardPanel, "1"));
        infoMenuItem.addActionListener(e -> cl.show(cardPanel, "5"));
        helpMenuItem.addActionListener(e -> cl.show(cardPanel, "6"));
        exitMenuItem.addActionListener(e -> System.exit(0));
        chooseFilesButton.addActionListener(e -> {
            chooseFiles();
            try {
                input = LoadFromFile.getInput(files);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
//            for(String s: input) {
//                System.out.println(s);
//            }
        });
    }

    public void startClock() {
        ActionListener updateClockAction = e -> clockLabel.setText(((new Date().toString()).substring(11, 19) + "    "));
        Timer t = new Timer(100, updateClockAction);
        t.start();
    }

    public void chooseFiles() {
        JFileChooser fc = new JFileChooser(new File("Test/"));
        FileFilter txtFileFilter = new FileNameExtensionFilter("Pliki tekstowe","txt");
        fc.setMultiSelectionEnabled(true);
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileFilter(txtFileFilter);
        int r = fc.showOpenDialog(mainPanel);
        if(r == JFileChooser.APPROVE_OPTION) {
            files = fc.getSelectedFiles();
            for(File f: files) {
                System.out.println(f.getName());
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lista Zakupów");
        frame.setContentPane(new ListaZakupow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {

    }
}