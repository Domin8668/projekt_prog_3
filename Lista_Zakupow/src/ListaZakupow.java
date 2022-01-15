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
    private JLabel infoLabel;
    private JLabel pomocLabel;
    private File[] files;
    private ArrayList<String> input;
    CardLayout cl = new CardLayout();

    public ListaZakupow() {
        cardPanel.setLayout(cl);
        inputCard.setBackground(Color.green);
        menuCard.setBackground(Color.yellow);
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

        // infoLabel
        infoLabel.setText("<html>"
            + "Aplikacja \"Lista zakupów\" napisana w języku Java<br><br>"
            + "Autorzy:<br>&emsp;&emsp;Kamil Giziński<br>&emsp;&emsp;Dominik Sigulski<br>&emsp;&emsp;Bartosz Rolnik<br><br>"
            + "Projekt z przedmiotu Programowanie III<br>"
            + "Wydział Matematyki Stosowanej, Informatyka, sem. III, rok 2021/22<br><br>"
            + "\"Lista zakupów\" to zadanie z konkursu \"Algorytmion\" z roku 2012,<br>"
            + "które polega na znalezieniu takich kombinacji produktów,<br>"
            + "aby suma ich wartości odpowiadała budżetowi jaki mamy do rozdysponowania."
            + "</html>");

        // pomocLabel
        pomocLabel.setText("<html>"
                + "Do wyboru z głównego panelu menu mamy następujące opcje:<br><br>"
                + "&emsp;&emsp;1. Menu główne<br>&emsp;&emsp;2. Informacje<br>&emsp;&emsp;3. Pomoc<br>&emsp;&emsp;4. Wyjście<br><br>"
                + "Opcja 1:<br>"
                + "&emsp;&emsp;W menu głównym możemy podjąc decyzję o tym jakie pliki chcemy analizować<br>"
                + "&emsp;&emsp;klikając przycisk \"Wybierz pliki\". Następnie poprzez naciśnięcie kolejnego przycisku<br>"
                + "&emsp;&emsp;program analizuje dane i zwraca wynik na ekran oraz do pliku.<br>"
                + "Opcja 2:<br>"
                + "&emsp;&emsp;Wyświetlenie informacji na temat zadania, autorów oraz przedmiotu w zakresie którego<br>"
                + "&emsp;&emsp;realizowany był projekt.<br><br>"
                + "Opcja 3:<br>"
                + "&emsp;&emsp;Wyświetlenie informacji pomocniczych na temat opcji zawartych w aplikacji.<br><br>"
                + "Opcja 4:<br>"
                + "&emsp;&emsp;Zakończenie działania aplikacji.<br><br>"
                + "</html>");
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
        frame.setSize(720, 530);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {

    }
}