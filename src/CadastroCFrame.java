import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CadastroCFrame extends JFrame {
    CardLayout cardLayout = new CardLayout();
    JTextField nomeField, usuarioField, senhaField, cpfField, telefoneField;
    JButton registerButton = new JButton("Cadastrar");

    protected void salvarCliente(String nome, String usuario, String senha, String cpf, String telefone) {
        try (FileWriter fw = new FileWriter("cliente.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            String linha = "Dados do cliente: " + nome + " | " + usuario + " | " + senha + " | " + cpf + " | " + telefone;
            pw.println(linha);

        } catch (IOException erro) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar cliente: " + erro.getMessage());
        }

    }


    





    public CadastroCFrame(){
        inicializarComponentes();
        construirLayout();
        criarEventos();
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

    private void construirLayout() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#9DB984"));

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(Color.WHITE);
        containerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(40, 0, 40, 0)
        ));
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        Dimension containerSize = new Dimension(550, 500);
        containerPanel.setPreferredSize(containerSize);
        containerPanel.setMaximumSize(containerSize);
        containerPanel.setMinimumSize(new Dimension(500, 450));

        //Titulo
        JLabel tituloLabel = new JLabel("EcoSelo");
        tituloLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        tituloLabel.setForeground(Color.decode("#2E7D32"));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Subtitulo
        JLabel subtituloLabel = new JLabel("Cadastro de Cliente");
        subtituloLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        subtituloLabel.setForeground(Color.GRAY);
        subtituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Espaço entre os componentes
        Component rigidArea1 = Box.createRigidArea(new Dimension(0, 10));

        //Nome
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        nomeLabel.setForeground(Color.GRAY);
        nomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //NomeField
        nomeField = new JTextField();
        nomeField.setPreferredSize(new Dimension(100, 30));
        nomeField.setFont(new Font("Roboto", Font.PLAIN, 14));
        nomeField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        nomeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        nomeField.setPreferredSize(new Dimension(150, 35));
        nomeField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        //usuárioLabel
        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        usuarioLabel.setForeground(Color.GRAY);
        usuarioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //usuárioField
        usuarioField = new JTextField();
        usuarioField.setPreferredSize(new Dimension(100, 30));
        usuarioField.setFont(new Font("Roboto", Font.PLAIN, 14));
        usuarioField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        usuarioField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        usuarioField.setPreferredSize(new Dimension(150, 35));
        usuarioField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        //SenhaLabel
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        senhaLabel.setForeground(Color.GRAY);
        senhaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //SenhaField
        senhaField = new JTextField();
        senhaField.setPreferredSize(new Dimension(100, 30));
        senhaField.setFont(new Font("Roboto", Font.PLAIN, 14));
        senhaField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        senhaField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        senhaField.setPreferredSize(new Dimension(150, 35));
        senhaField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        //cpfLabel
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        cpfLabel.setForeground(Color.GRAY);
        cpfLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //CPFField
        cpfField = new JTextField();
        cpfField.setPreferredSize(new Dimension(100, 30));
        cpfField.setFont(new Font("Roboto", Font.PLAIN, 14));
        cpfField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        cpfField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        cpfField.setPreferredSize(new Dimension(150, 35));
        cpfField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        //numeroTelLabel
        JLabel numeroTelLabel = new JLabel("Telefone:");
        numeroTelLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        numeroTelLabel.setForeground(Color.GRAY);
        numeroTelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //numeroTelField
        telefoneField = new JTextField();
        telefoneField.setPreferredSize(new Dimension(100, 30));
        telefoneField.setFont(new Font("Roboto", Font.PLAIN, 14));
        telefoneField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        telefoneField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        telefoneField.setPreferredSize(new Dimension(150, 35));
        telefoneField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        //Botão de cadastro

        registerButton.setFont(new Font("Roboto", Font.BOLD, 14));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.decode("#9DB984"));
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        registerButton.setPreferredSize(new Dimension(250, 40));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //encapsula o botão de cadastro
        JPanel boxInputCadastro = new JPanel();
        boxInputCadastro.setBackground(Color.WHITE);
        boxInputCadastro.setLayout(new GridBagLayout());
        boxInputCadastro.add(registerButton);






        //Formulário de cadastro
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.setBackground(Color.WHITE);
        JPanel boxInputNome = criaBoxInput(nomeField, nomeLabel);
        JPanel boxInputUsuario = criaBoxInput(usuarioField, usuarioLabel);
        JPanel boxInputSenha = criaBoxInput(senhaField, senhaLabel);
        JPanel boxInputCpf = criaBoxInput(cpfField, cpfLabel);
        JPanel boxInputTelefone = criaBoxInput(telefoneField, numeroTelLabel);


        //void
        JPanel boxInputVoid = new JPanel();
        boxInputVoid.setBackground(Color.WHITE);


        //adicionar elementos ao formulário
        formPanel.add(personalizaGridLayout(boxInputNome, boxInputUsuario));
        formPanel.add(personalizaGridLayout(boxInputSenha, boxInputCpf));
        formPanel.add(personalizaGridLayout(boxInputTelefone, boxInputCadastro));


        //adicionar elementos ao painel
        containerPanel.add(tituloLabel);
        containerPanel.add(subtituloLabel);
        containerPanel.add(rigidArea1);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 20, 20, 20);

        containerPanel.add(formPanel, gbc);
        mainPanel.add(containerPanel, gbc);

        // Adicionar o painel principal ao frame
        add(mainPanel, BorderLayout.CENTER);
    }

    private void criarEventos() {

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String usuario = usuarioField.getText();
                String senha = senhaField.getText();
                String cpf = cpfField.getText();
                String telefone = telefoneField.getText();

                if(nome.isEmpty() || usuario.isEmpty() || senha.isEmpty() || cpf.isEmpty() || telefone.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }else{
                    //cria um novo usuário
                    Usuario novoUsuario = new Cliente(nome, usuario, senha, cpf, telefone);
                    System.out.println(novoUsuario);

                    salvarCliente(nome, usuario, senha, cpf, telefone);


                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucess", JOptionPane.INFORMATION_MESSAGE);


                    PrincipalClienteFrame clientePanel = new PrincipalClienteFrame();
                    clientePanel.setVisible(true);
                    dispose();


                }
            }
        });
    }

    private JPanel criaBoxInput(JTextField input, JLabel label){
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        box.add(label);
        box.add(Box.createHorizontalGlue());
        box.add(input);
        return box;
    }

    private Component personalizaGridLayout( JPanel boxInputEsq, Component boxInputDir){
        JPanel gridPanel = new JPanel(new GridLayout(1, 2));
        gridPanel.add(boxInputEsq);
        gridPanel.add(boxInputDir);
        return gridPanel;
    }

}
