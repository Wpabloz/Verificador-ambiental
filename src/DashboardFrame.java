import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashboardFrame extends JFrame {
    JPanel mainPanel;
    JButton editarButton, sairButton, tentarButton, verSeloButton;


    public DashboardFrame(Empresa empresaLogada) {
        inicializarComponentes();
        configurarLayout(empresaLogada);
        configurarEventos(empresaLogada);

    }

    private void inicializarComponentes() {
        setTitle("EcoSelo");
        setTitle("EcoSelo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        getContentPane().setBackground(Color.decode("#9DB984"));
    }

    private void configurarLayout(Empresa empresaLogada) {
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#9DB984"));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBackground(Color.WHITE);
        containerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Farei um Card para a Empresa Logada
        JPanel cardEmpresa = new JPanel();
        cardEmpresa.setLayout(new GridLayout(3, 2));
        cardEmpresa.setBackground(Color.WHITE);
        cardEmpresa.setPreferredSize(new Dimension(Integer.MAX_VALUE, 170));
        cardEmpresa.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));
        cardEmpresa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Adicione o nome da empresa aqui
        JLabel nomeEmpresaLabel = new JLabel("Nome da Empresa: " + empresaLogada.getNome());
        nomeEmpresaLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        nomeEmpresaLabel.setForeground(Color.decode("#2E7D32"));
        cardEmpresa.add(nomeEmpresaLabel);

        // CNPJ
        JLabel cnpjLabel = new JLabel("CNPJ: " + empresaLogada.getCnpj());
        cnpjLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        cnpjLabel.setForeground(Color.decode("#2E7D32"));
        cardEmpresa.add(cnpjLabel);

        // Atividade
        JLabel atividadeLabel = new JLabel("Atividade: " + empresaLogada.getAtividade());
        atividadeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        atividadeLabel.setForeground(Color.decode("#2E7D32"));
        cardEmpresa.add(atividadeLabel);

        // Usuário
        JLabel usuarioLabel = new JLabel("Usuário: " + empresaLogada.getUsername());
        usuarioLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        usuarioLabel.setForeground(Color.decode("#2E7D32"));
        cardEmpresa.add(usuarioLabel);

        // Selos
        JLabel selosLabel = new JLabel("Selos: Nenhum");
        selosLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        selosLabel.setForeground(Color.decode("#2E7D32"));
        cardEmpresa.add(selosLabel);

        // Botões sair e editar
        sairButton = new JButton("Sair");
        sairButton.setFont(new Font("Roboto", Font.BOLD, 14));
        sairButton.setForeground(Color.WHITE);
        sairButton.setBackground(Color.decode("#2E7D32"));
        sairButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        editarButton = new JButton("Editar");
        editarButton.setFont(new Font("Roboto", Font.BOLD, 14));
        editarButton.setForeground(Color.WHITE);
        editarButton.setBackground(Color.decode("#2E7D32"));
        editarButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Adicione os botões ao card
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonsPanel.add(editarButton);
        buttonsPanel.add(sairButton);
        cardEmpresa.add(buttonsPanel);

        // espaço rígido
        Component rigidArea = Box.createRigidArea(new Dimension(0, 30));

        // listas de selos


        JPanel selosPanel = new JPanel();
        selosPanel.setLayout(new GridLayout((TipoSelo.values().length % 3) + 1, 3));
        selosPanel.setBackground(Color.WHITE);
        selosPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));

        for (TipoSelo tipoSelo : TipoSelo.values()) {
            JPanel card = criarCardSelos(tipoSelo, empresaLogada);
            selosPanel.add(card);
        }




        containerPanel.add(cardEmpresa);
        containerPanel.add(rigidArea);
        containerPanel.add(new JScrollPane(selosPanel));

        mainPanel.add(containerPanel,BorderLayout.CENTER);

        add(mainPanel);
    }

    private void configurarEventos(Empresa empresaLogada) {

        sairButton.addActionListener(e ->{
            InicialFrame inicialPage = new InicialFrame();
            inicialPage.setVisible(true);
            dispose();
        });

        editarButton.addActionListener(e ->{
            JDialog dialog = new EditarDialog(this, empresaLogada);
            dialog.setVisible(true);


        });




    }

    private JPanel criarCardSelos(TipoSelo tipoSelo, Empresa empresaLogada) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        double progresso = empresaLogada.getProgressoSelo(tipoSelo);
        JLabel selo = new JLabel(tipoSelo.toString() + " - " + (progresso * 100) + "%");
        card.add(selo, BorderLayout.WEST);




        tentarButton = new JButton("Tentar Selo");
        tentarButton.setFont(new Font("Roboto", Font.BOLD, 10));
        tentarButton.setForeground(Color.WHITE);
        tentarButton.setBackground(Color.decode("#2E7D32"));
        tentarButton.setMaximumSize(new Dimension(100, 30));
        tentarButton.setMinimumSize(new Dimension(100, 30));
        tentarButton.setPreferredSize(new Dimension(100, 30));
        tentarButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        tentarButton.addActionListener(e ->{
            ChecklistFrame checklistFrame = new ChecklistFrame(tipoSelo, empresaLogada);
            checklistFrame.setVisible(true);
            dispose();
        });

        verSeloButton = new JButton("Ver Selo");
        verSeloButton.setFont(new Font("Roboto", Font.BOLD, 10));
        verSeloButton.setForeground(Color.WHITE);
        verSeloButton.setBackground(Color.decode("#2E7D32"));
        verSeloButton.setMaximumSize(new Dimension(100, 30));
        verSeloButton.setMinimumSize(new Dimension(100, 30));
        verSeloButton.setPreferredSize(new Dimension(100, 30));
        verSeloButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        verSeloButton.addActionListener(e ->{
            JDialog dialog = criarDialogSelo(tipoSelo, empresaLogada);
            dialog.setVisible(true);
        });


        if(empresaLogada.getSelo(tipoSelo).getProgresso() == 1.0){
            card.add(verSeloButton, BorderLayout.EAST);
        }else{
            card.add(tentarButton, BorderLayout.EAST);
        }


        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));



        return card;
    }


    private JDialog criarDialogSelo(TipoSelo tipoSelo, Empresa empresaLogada) {
        JDialog dialog = new JDialog(this, "Selo " + tipoSelo.toString(), true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel titulo = new JLabel("Certificamos que a empresa " + empresaLogada.getNome() + " conquistos o selo de combate/fomentação a(o) " + tipoSelo.toString() + " do programa de certificação " + "EcoSelo.");
        titulo.setFont(new Font("Roboto", Font.BOLD, 16));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setVerticalAlignment(SwingConstants.CENTER);
        contentPanel.add(titulo, BorderLayout.NORTH);

        JPanel seloDescricaoPanel = new JPanel(new BorderLayout());
        seloDescricaoPanel.setBackground(Color.WHITE);
        seloDescricaoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)
        ));
        JLabel seloNome = new JLabel(tipoSelo.toString());
        seloNome.setFont(new Font("Roboto", Font.BOLD, 16));
        seloNome.setHorizontalAlignment(SwingConstants.CENTER);
        seloNome.setVerticalAlignment(SwingConstants.CENTER);
        seloDescricaoPanel.add(seloNome, BorderLayout.NORTH);

        JLabel seloEmissao = new JLabel("Emissão: " + empresaLogada.getSelo(tipoSelo).getEmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        seloEmissao.setFont(new Font("Roboto", Font.PLAIN, 12));
        seloEmissao.setHorizontalAlignment(SwingConstants.CENTER);
        seloEmissao.setVerticalAlignment(SwingConstants.CENTER);
        seloDescricaoPanel.add(seloEmissao, BorderLayout.CENTER);

        contentPanel.add(seloDescricaoPanel, BorderLayout.CENTER);
        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.setVisible(true);

        return dialog;


    }

}
