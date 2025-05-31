import javax.swing.*;
import java.awt.*;

public class PrincipalClienteFrame extends JFrame {
    JTextField searchInput;
    JTextArea listaEmpresa;
    JButton searchButton;

    public PrincipalClienteFrame(){
        inicializarComponentes();
        configurarLayout();
        configurarEventos();


    }

    private void inicializarComponentes() {
        setTitle("EcoSelo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(true);

        //cor de fundo
        getContentPane().setBackground(Color.decode("#9DB984"));
    }

    private void configurarLayout() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#9DB984"));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        searchInput = new JTextField();
        searchInput.setPreferredSize(new Dimension(200, 30));
        searchInput.setFont(new Font("Roboto", Font.PLAIN, 16));
        searchInput.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        searchInput.setBackground(Color.WHITE);
        searchInput.setForeground(Color.BLACK);
        searchInput.setHorizontalAlignment(JTextField.LEFT);

        searchButton = new JButton("Buscar");
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setFont(new Font("Roboto", Font.BOLD, 16));
        searchButton.setBackground(Color.decode("#007BFF"));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 1),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                )
        );

        JPanel searchBar = new JPanel();
        searchBar.setLayout(new BoxLayout(searchBar, BoxLayout.X_AXIS));
        searchBar.add(searchInput);
        searchBar.add(searchButton);
        searchBar.setBackground(Color.WHITE);


        listaEmpresa = new JTextArea();
        listaEmpresa.setEditable(false);
        listaEmpresa.setFont(new Font("Roboto", Font.BOLD, 16));
        listaEmpresa.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listaEmpresa.setBackground(Color.decode("#F5F5F5"));



        mainPanel.add(searchBar, BorderLayout.NORTH);
        mainPanel.add(listaEmpresa, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void configurarEventos() {
    }


}
