import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CadastroEFrame extends JFrame {
    JTextField nomeField, usuarioField, cnpjField;
    JPasswordField senhaField;
    JButton registerButton;
    JComboBox<String> atividadeComboBox;



    

    public CadastroEFrame(){
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
        JLabel subtituloLabel = new JLabel("Cadastro de Empresas");
        subtituloLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        subtituloLabel.setForeground(Color.GRAY);
        subtituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Espaço entre os componentes
        Component rigidArea1 = Box.createRigidArea(new Dimension(0, 10));

        //Formulário
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.setBackground(Color.WHITE);

        //nomeLabel
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        nomeLabel.setForeground(Color.GRAY);
        nomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //NomeField
        nomeField = new JTextField();
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
        senhaField = new JPasswordField();
        senhaField.setFont(new Font("Roboto", Font.PLAIN, 14));
        senhaField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        senhaField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        senhaField.setPreferredSize(new Dimension(150, 35));
        senhaField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        //CNPJLabel
        JLabel cnpjLabel = new JLabel("CNPJ:");
        cnpjLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        cnpjLabel.setForeground(Color.GRAY);
        cnpjLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //CNPJField
        cnpjField = new JTextField();
        cnpjField.setFont(new Font("Roboto", Font.PLAIN, 14));
        cnpjField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        cnpjField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        cnpjField.setPreferredSize(new Dimension(150, 35));
        cnpjField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        //AtividadeLabel
        JLabel atividadeLabel = new JLabel("Atividade:");
        atividadeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        atividadeLabel.setForeground(Color.GRAY);
        atividadeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //AtividadeComboBox
        String[] opcoesAtividade = new String[Atividade.values().length];
        Atividade[] atividades = Atividade.values();
        for (int i = 0; i < atividades.length; i++) {
            opcoesAtividade[i] = atividades[i].toString();
        }
        atividadeComboBox = new JComboBox<>(opcoesAtividade);
        atividadeComboBox.setFont(new Font("Roboto", Font.PLAIN, 14));
        atividadeComboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        atividadeComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        atividadeComboBox.setPreferredSize(new Dimension(150, 35));

        //registroButton
        registerButton = new JButton("Cadastrar");
        registerButton.setFont(new Font("Roboto", Font.BOLD, 14));
        registerButton.setBackground(Color.decode("#9DB984"));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        registerButton.setPreferredSize(new Dimension(250, 40));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //encapsula o botão de cadastro
        JPanel boxInputRegister = new JPanel();
        boxInputRegister.setBackground(Color.WHITE);
        boxInputRegister.setLayout(new GridBagLayout());
        boxInputRegister.add(registerButton);


        //Criando os Box input
        JPanel nomeBox = criaBoxInput(nomeField, nomeLabel);
        JPanel usuarioBox = criaBoxInput(usuarioField, usuarioLabel);
        JPanel senhaBox = criaBoxInput(senhaField, senhaLabel);
        JPanel cnpjBox = criaBoxInput(cnpjField, cnpjLabel);
        JPanel atividadeBox = criaBoxInput(atividadeComboBox, atividadeLabel);

        // Adicionando os componentes ao formulário
        formPanel.add(personalizaGridLayout(nomeBox, usuarioBox));
        formPanel.add(personalizaGridLayout(senhaBox, cnpjBox));
        formPanel.add(personalizaGridLayout(atividadeBox, boxInputRegister));

        //Adicionar elementos ao containerPanel
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

        registerButton.addActionListener(e -> {

            String nome = nomeField.getText();
            String usuario = usuarioField.getText();
            String senha = senhaField.getText();
            String cnpj = cnpjField.getText();
            Atividade atividade = Atividade.valueOf(atividadeComboBox.getSelectedItem().toString());

            if (nome.isEmpty() || usuario.isEmpty() || senha.isEmpty() || cnpj.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (usuario.length() < 5) {
                JOptionPane.showMessageDialog(null, "O nome de usuário deve ter no mínimo 5 dígitos", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (senha.length() < 8){
                JOptionPane.showMessageDialog(null, "A senha deve ter no mínimo 8 dígitos", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (cnpj.length() != 14) {
                JOptionPane.showMessageDialog(null, "O CNPJ deve ter 14 dígitos", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                Empresa empresa = new Empresa(nome, usuario, senha, cnpj, atividade);

                salvarEmpresa(empresa);




                JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso!");

                JDialog escolhaSelo = new JDialog(this, "Tipo de selo", true);
                escolhaSelo.setSize(400, 150);
                escolhaSelo.setLayout(new FlowLayout());
                escolhaSelo.setLocationRelativeTo(this);

                escolhaSelo.add(new JLabel("Escolha o tipo de selo:"));

                    //ComboBox
                String[] opcoesSelo = new String[TipoSelo.values().length];
                TipoSelo[] selos = TipoSelo.values();
                for (int i = 0; i < selos.length; i++) {
                    opcoesSelo[i] = selos[i].toString();
                }
                JComboBox<String> seloComboBox = new JComboBox<>(opcoesSelo);
                seloComboBox.setFont(new Font("Roboto", Font.PLAIN, 14));
                seloComboBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                seloComboBox.setMinimumSize(new Dimension(Integer.MIN_VALUE, 35));
                seloComboBox.setPreferredSize(new Dimension(100, 35));



                JButton confirmarButton = new JButton("Confirmar");

                confirmarButton.addActionListener(event -> {
                    TipoSelo selo = TipoSelo.valueOf(seloComboBox.getSelectedItem().toString());
                    ChecklistFrame checklistFrame = new ChecklistFrame(selo);
                    checklistFrame.setVisible(true);

                    escolhaSelo.dispose();
                    dispose();
                });

                escolhaSelo.add(seloComboBox);
                escolhaSelo.add(confirmarButton);
                escolhaSelo.setVisible(true);



            }
        });
    }

    private JPanel criaBoxInput(Component input, JLabel label){
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

    protected void salvarEmpresa(Empresa empresa) {
        try (FileWriter fw = new FileWriter("empresa.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            String linha = "Dados da empresa: " + empresa.getNome() + " | " + empresa.getUsername() + " | " + empresa.getSenha() + " | " + empresa.getCnpj() + " | " + empresa.getAtividade();
            pw.println(linha);

        } catch (IOException erro) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar cliente: " + erro.getMessage());
        }

    }

}
