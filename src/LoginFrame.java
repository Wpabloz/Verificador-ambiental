import javax.security.sasl.AuthenticationException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class LoginFrame extends JFrame {
    JTextField usuarioField, senhaField;
    JButton loginButton = new JButton("Entrar");

    //construtor
    public LoginFrame() {
        inicializeComponenetes();
        configurarLayout();
        configurarEventos();
    }

    private void inicializeComponenetes() {
        setTitle("EcoSelo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(true);

        //cor de fundo
        getContentPane().setBackground(Color.decode("#9DB984"));
    }

    private void configurarEventos() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#9DB984"));

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(Color.WHITE);
        containerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        Dimension containerSize = new Dimension(350, 400);
        containerPanel.setPreferredSize(containerSize);
        containerPanel.setMaximumSize(containerSize);
        containerPanel.setMinimumSize(new Dimension(300, 350));

        // Título do projeto
        JLabel tituloLabel = new JLabel("EcoSelo");
        tituloLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        tituloLabel.setForeground(Color.decode("#2E7D32"));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo
        JLabel subtituloLabel = new JLabel("Sistema de Login");
        subtituloLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        subtituloLabel.setForeground(Color.GRAY);
        subtituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Espaço entre os componentes
        Component rigidArea = Box.createRigidArea(new Dimension(0, 30));

        // Label e campo de usuário
        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usuarioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        usuarioField = new JTextField();
        usuarioField.setFont(new Font("Arial", Font.PLAIN, 14));
        usuarioField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        usuarioField.setPreferredSize(new Dimension(250, 35));
        usuarioField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        // Espaçamento
        Component rigidArea2 = Box.createRigidArea(new Dimension(0, 15));

        // Label e campo de senha
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        senhaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        senhaField = new JPasswordField();
        senhaField.setFont(new Font("Arial", Font.PLAIN, 14));
        senhaField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        senhaField.setPreferredSize(new Dimension(250, 35));
        senhaField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        // Espaçamento
        Component rigidArea3 = Box.createRigidArea(new Dimension(0, 25));

        // Botão de login
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(Color.decode("#9DB984"));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        loginButton.setPreferredSize(new Dimension(250, 40));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //adicionando os componentes
        containerPanel.add(tituloLabel);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        containerPanel.add(subtituloLabel);
        containerPanel.add(rigidArea);
        containerPanel.add(usuarioLabel);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        containerPanel.add(usuarioField);
        containerPanel.add(rigidArea2);
        containerPanel.add(senhaLabel);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        containerPanel.add(senhaField);
        containerPanel.add(rigidArea3);
        containerPanel.add(loginButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 20, 20, 20);

        mainPanel.add(containerPanel, gbc);

        // Adicionar o painel principal ao frame
        add(mainPanel, BorderLayout.CENTER);
    }

    private void configurarLayout() {
        // Configuração do layout do painel principal
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    realizarLogin();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void realizarLogin() throws IOException {
        String usuario = usuarioField.getText().trim();
        String senha = senhaField.getText().trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos.",
                    "Campos obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Aqui você pode implementar a lógica de autenticação
        if (autenticarUsuario(usuario, senha) == "cliente") {
            JOptionPane.showMessageDialog(this,
                    "Login realizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            // Fechar janela de login e abrir próxima tela
            PrincipalClienteFrame principalClienteFrame = new PrincipalClienteFrame();
            principalClienteFrame.setVisible(true);
            dispose();
        }else if (autenticarUsuario(usuario,senha) == "empresa"){
            JOptionPane.showMessageDialog(this,
                    "Login realizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            // Fechar janela de login e abrir próxima tela
            new DashboardFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuário ou senha incorretos.",
                    "Erro de autenticação",
                    JOptionPane.ERROR_MESSAGE);

            // Limpar campos
            senhaField.setText("");
            usuarioField.requestFocus();
        }




    }



    private String autenticarUsuario(String usuario, String senha) throws IOException {
        // Implementar aqui a lógica de autenticação
        // Exemplo simples para demonstração:

        BufferedReader reader = new BufferedReader(new FileReader("cliente.txt"));
        BufferedReader readerEmpresa = new BufferedReader(new FileReader("empresa.txt"));
        String line = reader.readLine();
        String lineEmpresa = readerEmpresa.readLine();


        while (line != null) {
            String[] parts = line.split(",");
            String[] partsEmpresa = lineEmpresa.split(",");
            System.out.println(parts[1]);
            System.out.println(partsEmpresa[1]);
            System.out.println(parts[2]);
            System.out.println(partsEmpresa[2]);
            // Verifica se o usuário e a senha correspondem aos dados do arquivo
            if (parts.length == 5 && usuario.equals(parts[1]) && senha.equals(parts[2])) {
                reader.close();
                return "cliente";
            }else if (partsEmpresa.length == 5 && usuario.equals(partsEmpresa[1]) && senha.equals(partsEmpresa[2])) {
                readerEmpresa.close();
                return "empresa";
            }
                line = reader.readLine();
        }

        return "";
    }


}
