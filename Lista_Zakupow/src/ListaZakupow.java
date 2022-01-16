import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
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
    private JLabel helpLabel;
    private JButton calculateButton;
    private JScrollPane inputScrollPane;
    private JTextPane inputTextPane;
    private JMenuBar menuBar;
    private JPanel menuPanel;
    private JTextPane outputTextPane;
    private JScrollPane outputScrollPane;

    private File[] files;
    private ArrayList<String> input;
    private ArrayList<String> result;

    public ListaZakupow() {
        CardLayout cl = new CardLayout();
        cardPanel.setLayout(cl);
        cardPanel.add(menuCard, "1");
        cardPanel.add(inputCard, "2");
        cardPanel.add(loadingCard, "3");
        cardPanel.add(outputCard, "4");
        cardPanel.add(infoCard, "5");
        cardPanel.add(helpCard, "6");
        cl.show(cardPanel, "1");

        startClock();
        addIcons();

        mainMenuItem.addActionListener(e -> cl.show(cardPanel, "1"));
        infoMenuItem.addActionListener(e -> {
            infoLabel.setText("""
            <html>
            Aplikacja "Lista zakupów" napisana w języku Java<br><br>
            Autorzy:<br>&emsp;&emsp;Kamil Giziński<br>&emsp;&emsp;Dominik Sigulski<br>&emsp;&emsp;Bartosz Rolnik<br><br>
            Projekt z przedmiotu Programowanie III<br>
            Wydział Matematyki Stosowanej, Informatyka, sem. III, rok 2021/22<br><br>
            "Lista zakupów" to zadanie z konkursu "Algorytmion" z roku 2012,<br>
            które polega na znalezieniu takich kombinacji produktów,<br>
            aby suma ich wartości odpowiadała budżetowi jaki mamy do rozdysponowania.
            </html>""");
            cl.show(cardPanel, "5");
        });
        helpMenuItem.addActionListener(e -> {
            helpLabel.setText("""
                <html>
                Do wyboru z głównego panelu menu mamy następujące opcje:<br><br>
                &emsp;&emsp;1. Menu główne<br>&emsp;&emsp;2. Informacje<br>&emsp;&emsp;3. Pomoc<br>&emsp;&emsp;4. Wyjście<br><br>
                Opcja 1:<br>
                &emsp;&emsp;W menu głównym możemy podjąc decyzję o tym jakie pliki chcemy analizować<br>
                &emsp;&emsp;klikając przycisk "Wybierz pliki". Następnie poprzez naciśnięcie kolejnego przycisku<br>
                &emsp;&emsp;program analizuje dane i zwraca wynik na ekran oraz do pliku.<br>
                Opcja 2:<br>
                &emsp;&emsp;Wyświetlenie informacji na temat zadania, autorów oraz przedmiotu w zakresie którego<br>
                &emsp;&emsp;realizowany był projekt.<br><br>
                Opcja 3:<br>
                &emsp;&emsp;Wyświetlenie informacji pomocniczych na temat opcji zawartych w aplikacji.<br><br>
                Opcja 4:<br>
                &emsp;&emsp;Zakończenie działania aplikacji.<br><br>
                </html>""");
            cl.show(cardPanel, "6");
        });
        exitMenuItem.addActionListener(e -> System.exit(0));
        chooseFilesButton.addActionListener(e -> {
            try {
                chooseFiles();
                displayInput();
                cl.show(cardPanel, "2");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainPanel, ex.getStackTrace(),
                        "Błąd krytyczny", JOptionPane.ERROR_MESSAGE);
            }
        });
        calculateButton.addActionListener(e -> {
            try {
                Calculate c = new Calculate();
                c.calculate(input);
                result = c.getResult();
                for(String s : result) {
                    System.out.println(s);
                }
                displayOutput();
//                cl.show(cardPanel, "3");
                cl.show(cardPanel, "4"); // Tymczasowo przechodzimy do output, dodać loading bar
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainPanel, ex.getStackTrace(),
                        "Błąd krytyczny", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    public void startClock() {
        ActionListener updateClockAction = e -> clockLabel.setText(((new Date().toString()).substring(11, 19) + "    "));
        Timer t = new Timer(100, updateClockAction);
        t.start();
    }

    public void addIcons() {
        menuBar.setBorder(null);
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/main.jpg")));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon main_icon = new ImageIcon(newimg);
        mainMenuItem.setIcon(main_icon);
        imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/info.jpg")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon info_icon = new ImageIcon(newimg);
        infoMenuItem.setIcon(info_icon);
        imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/help.jpg")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon help_icon = new ImageIcon(newimg);
        helpMenuItem.setIcon(help_icon);
        imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/exit.jpg")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon exit_icon = new ImageIcon(newimg);
        exitMenuItem.setIcon(exit_icon);

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
        }
    }

    public void displayInput() throws IOException {
        if(files != null) {
            input = LoadFromFile.getInput(files);
            StringBuilder inputToDisplay = new StringBuilder();
            for(int i = 0; i < files.length; i++) {
                inputToDisplay.append("Plik ").append(files[i].getName()).append(":\n");
                inputToDisplay.append(input.get(i)).append("\n\n");
            }
            inputTextPane.setText(String.valueOf(inputToDisplay));
        }
    }

    public void displayOutput() {
        if(result != null) {
            int counter = 1;
            StringBuilder outputToDisplay = new StringBuilder();
            for(int i = 0; i < files.length; i++) {
                String[] combinations = result.get(i).split(";");
                outputToDisplay.append("Plik ").append(counter).append(" ").append(files[i].getName()).append(":\n");
                for (String combination : combinations) {
                    outputToDisplay.append(combination).append("\n");
                }
                counter++;
                outputToDisplay.append("\n\n");
            }
            System.out.println(outputToDisplay);
            outputTextPane.setText(String.valueOf(outputToDisplay));
        }
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Lista Zakupów");
        frame.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(51,58,65));
        frame.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.white);
        frame.setContentPane(new ListaZakupow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(720, 540);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createUIComponents() {

    }
}