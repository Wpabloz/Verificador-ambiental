import javax.swing.*;
import java.awt.*;

public class InicialFrame extends JFrame {
    CardLayout cardLayout = new CardLayout();
    JButton loginButton, cadastroButton;

    public InicialFrame() {
        inicializeComponenetes();
        configurarLayout();
        configurarEventos();
        // Adicionar painéis ao CardLayout

    }

    private void inicializeComponenetes() {
        setTitle("EcoSelo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(Color.decode("#9DB984"));
    }

    private void configurarLayout() {
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.setBackground(Color.decode("#9DB984"));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel titulo = new JLabel("EcoSelo");
        titulo.setFont(new Font("Roboto", Font.BOLD, 32));
        titulo.setForeground(Color.decode("#FFFFFF"));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Seja bem-vindo ao EcoSelo!");
        subtitulo.setFont(new Font("Roboto", Font.PLAIN, 18));
        subtitulo.setForeground(Color.decode("#FFFFFF"));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        Component spacer = Box.createVerticalStrut(40);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Roboto", Font.BOLD, 16));
        loginButton.setForeground(Color.decode("#FFFFFF"));
        loginButton.setBackground(Color.decode("#2E7D32"));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setPreferredSize(new Dimension(300, 40));
        loginButton.setMinimumSize(new Dimension(100, 40));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        Component spacer2 = Box.createVerticalStrut(30);

        cadastroButton = new JButton("Cadastro");
        cadastroButton.setFont(new Font("Roboto", Font.BOLD, 16));
        cadastroButton.setForeground(Color.decode("#FFFFFF"));
        cadastroButton.setBackground(Color.decode("#2E7D32"));
        cadastroButton.setFocusPainted(false);
        cadastroButton.setBorderPainted(false);
        cadastroButton.setPreferredSize(new Dimension(300, 40));
        cadastroButton.setMinimumSize(new Dimension(100, 40));
        cadastroButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cadastroButton.setAlignmentX(Component.CENTER_ALIGNMENT);



        backgroundPanel.add(titulo);
        backgroundPanel.add(subtitulo);
        backgroundPanel.add(spacer);
        backgroundPanel.add(loginButton);
        backgroundPanel.add(spacer2);
        backgroundPanel.add(cadastroButton);

        add(backgroundPanel);
    }

    private void configurarEventos() {

        cadastroButton.addActionListener(e -> {
            JDialog cadastroOpcaoDialog = new JDialog(this, "Você é uma empresa ou um cliente?", true);
            cadastroOpcaoDialog.setLayout(new FlowLayout());
            cadastroOpcaoDialog.add(new JLabel("Escolha uma opção:"));
            JComboBox comboBox = new JComboBox<String>(new String[]{"Empresa", "Cliente"});
            cadastroOpcaoDialog.add(comboBox);
            JButton confirmarButton = new JButton("Confirmar");

            cadastroOpcaoDialog.setSize(300, 150);
            cadastroOpcaoDialog.setLocationRelativeTo(this);


            confirmarButton.addActionListener(event -> {
                if (comboBox.getSelectedItem().equals("Empresa")) {
                    CadastroEFrame cadastroEmpresaFrame = new CadastroEFrame();
                    cadastroEmpresaFrame.setVisible(true);
                    cadastroOpcaoDialog.dispose();
                    this.dispose();
                } else if (comboBox.getSelectedItem().equals("Cliente")) {
                    CadastroCFrame cadastroClienteFrame = new CadastroCFrame();
                    cadastroClienteFrame.setVisible(true);
                    cadastroOpcaoDialog.dispose();
                    this.dispose();
                }
            });
            cadastroOpcaoDialog.add(confirmarButton);
            cadastroOpcaoDialog.setVisible(true);
        });

        loginButton.addActionListener(e -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            this.dispose();
        });



    }


}
