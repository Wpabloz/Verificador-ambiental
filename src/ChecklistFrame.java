import javax.swing.*;
import java.awt.*;

public class ChecklistFrame extends JFrame {
    JButton btnContinuar;
    JScrollPane scrollPane;
    JPanel mainPanel;

    public ChecklistFrame(TipoSelo selo){
        inicializarComponentes();
        configurarLayout(selo);
        configurarEventos();
    }

    private void inicializarComponentes() {
        setTitle("EcoSelo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        getContentPane().setBackground(Color.decode("#9DB984"));
    }

    private void configurarLayout(TipoSelo selo) {
        setLayout(new BorderLayout());

        scrollPane = new JScrollPane();
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

        JLabel tituloLabel = new JLabel("EcoSelo");
        tituloLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        tituloLabel.setForeground(Color.decode("#2E7D32"));

        JLabel subtituloLabel = new JLabel("Verifique se você está em ordem com os requisitos do selo ");
        subtituloLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        subtituloLabel.setHorizontalAlignment(JLabel.CENTER);
        subtituloLabel.setForeground(Color.decode("#2E7D32"));

        containerPanel.add(tituloLabel);
        containerPanel.add(subtituloLabel);

        Component rigidArea = Box.createRigidArea(new Dimension(0, 20));
        containerPanel.add(rigidArea);

        containerPanel.add(criarCheckboxPerguntas(selo));

        btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Roboto", Font.BOLD, 18));
        btnContinuar.setBackground(Color.decode("#2E7D32"));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));


        containerPanel.add(btnContinuar);

        mainPanel.add(containerPanel, BorderLayout.CENTER);

        add(scrollPane.add(mainPanel), BorderLayout.CENTER);
    }

    private void configurarEventos() {

        btnContinuar.addActionListener(e -> {
            // Lógica para continuar o processo
            // Por exemplo, você pode abrir uma nova janela ou realizar outras ações
            // Neste exemplo, vamos apenas exibir uma mensagem de confirmação
            JOptionPane.showMessageDialog(this, "Checklist concluído!");
            DashboardFrame dashboardFrame = new DashboardFrame();
            dispose();
        });
    }

    private JPanel criarCheckboxPerguntas(TipoSelo selo) {
        JPanel checklistPanel = new JPanel();
        checklistPanel.setLayout(new BoxLayout(checklistPanel, BoxLayout.Y_AXIS));
        checklistPanel.setBackground(Color.WHITE);

        String[] perguntas;

        switch (selo) {
            case POBREZA:
                perguntas = new String[]{
                        "Como sua empresa contribui para erradicar a pobreza?",
                        "Quais ações sociais sua empresa apoia?",
                        "Há programas internos de apoio a comunidades vulneráveis?",
                        "Sua empresa investe em qualificação profissional de pessoas carentes?",
                        "Como sua empresa combate a desigualdade econômica?"
                };
                break;

            case FOME:
                perguntas = new String[]{
                        "Sua empresa apoia iniciativas contra a fome?",
                        "Quais políticas de segurança alimentar sua empresa adota?",
                        "Como reduz o desperdício de alimentos?",
                        "Há parcerias com bancos de alimentos?",
                        "Sua empresa investe em programas nutricionais?"
                };
                break;

            case SAUDE:
                perguntas = new String[]{
                        "Como sua empresa promove a saúde dos colaboradores?",
                        "Há programas de bem-estar ou prevenção?",
                        "Como lida com segurança no ambiente de trabalho?",
                        "Sua empresa promove campanhas de vacinação?",
                        "Quais políticas de saúde pública sua empresa apoia?"
                };
                break;

            case EDUCACAO:
                perguntas = new String[]{
                        "Sua empresa investe em educação de colaboradores?",
                        "Há programas de capacitação profissional?",
                        "Quais parcerias educacionais a empresa mantém?",
                        "Apoia projetos sociais educacionais?",
                        "Como promove o desenvolvimento educacional na comunidade?"
                };
                break;

            case IGUALDADE:
                perguntas = new String[]{
                        "Como sua empresa promove a igualdade de gênero?",
                        "Existem políticas para equidade salarial?",
                        "Como combate a discriminação no ambiente de trabalho?",
                        "A empresa promove lideranças femininas?",
                        "Quais iniciativas de diversidade são aplicadas?"
                };
                break;

            case AGUA:
                perguntas = new String[]{
                        "Como sua empresa utiliza recursos hídricos de forma sustentável?",
                        "Existem políticas de reuso de água?",
                        "Como minimiza o impacto hídrico nas operações?",
                        "A empresa investe em tratamento de água?",
                        "Quais parcerias mantém para preservação de mananciais?"
                };
                break;

            case ENERGIA:
                perguntas = new String[]{
                        "Sua empresa utiliza energia renovável?",
                        "Como promove a eficiência energética?",
                        "Há políticas de redução de consumo de energia?",
                        "A empresa investe em inovação energética?",
                        "Quais ações adota para mitigar impactos ambientais relacionados à energia?"
                };
                break;

            case TRABALHO:
                perguntas = new String[]{
                        "Como sua empresa garante condições dignas de trabalho?",
                        "Há políticas de desenvolvimento profissional?",
                        "Como promove a segurança ocupacional?",
                        "Apoia a geração de emprego local?",
                        "Como incentiva práticas trabalhistas justas?"
                };
                break;

            case INDUSTRIA:
                perguntas = new String[]{
                        "Sua empresa investe em inovação industrial?",
                        "Como promove infraestrutura sustentável?",
                        "Quais ações para reduzir impactos industriais negativos?",
                        "Há políticas de desenvolvimento tecnológico?",
                        "Como apoia pequenos produtores ou indústrias locais?"
                };
                break;

            case DESIGUALDADE:
                perguntas = new String[]{
                        "Como combate desigualdades dentro da empresa?",
                        "Há políticas para inclusão social?",
                        "Apoia programas de acesso igualitário a oportunidades?",
                        "Como promove diversidade cultural?",
                        "Quais ações direcionadas a grupos vulneráveis?"
                };
                break;

            case CIDADES:
                perguntas = new String[]{
                        "Sua empresa colabora com o desenvolvimento urbano sustentável?",
                        "Como promove a mobilidade sustentável?",
                        "Participa de ações para reduzir riscos urbanos?",
                        "Como gerencia resíduos nas operações urbanas?",
                        "Apoia projetos comunitários em áreas urbanas?"
                };
                break;

            case CONSUMO:
                perguntas = new String[]{
                        "Como promove padrões sustentáveis de consumo?",
                        "Há políticas para reduzir desperdício?",
                        "Como incentiva o consumo consciente entre clientes?",
                        "Quais ações de reciclagem sua empresa adota?",
                        "Como reduz a pegada ecológica dos produtos?"
                };
                break;

            case CLIMA:
                perguntas = new String[]{
                        "Como sua empresa contribui para a mitigação das mudanças climáticas?",
                        "Há políticas para redução de emissões de carbono?",
                        "Como promove a resiliência climática nas operações?",
                        "Apoia ações de reflorestamento?",
                        "Como monitora os riscos climáticos?"
                };
                break;

            case OCEANOS:
                perguntas = new String[]{
                        "Sua empresa atua na preservação de ecossistemas aquáticos?",
                        "Como evita a poluição dos recursos hídricos?",
                        "Há parcerias para proteção da vida marinha?",
                        "Como reduz o impacto ambiental em ambientes aquáticos?",
                        "Quais políticas para uso sustentável de recursos marinhos?"
                };
                break;

            case BIODIVERSIDADE:
                perguntas = new String[]{
                        "Como promove a preservação de ecossistemas terrestres?",
                        "Há políticas para proteção da biodiversidade?",
                        "Como reduz o impacto ambiental das operações?",
                        "Participa de ações para reflorestamento?",
                        "Como evita a degradação do solo?"
                };
                break;

            case PAZ:
                perguntas = new String[]{
                        "Como promove a cultura de paz na empresa?",
                        "Há políticas contra assédio e violência no ambiente de trabalho?",
                        "Como apoia instituições democráticas?",
                        "Participa de ações para promoção dos direitos humanos?",
                        "Como garante justiça e igualdade dentro da empresa?"
                };
                break;

            case PARCERIAS:
                perguntas = new String[]{
                        "Sua empresa participa de parcerias para o desenvolvimento sustentável?",
                        "Como colabora com outras organizações em prol dos ODS?",
                        "Há alianças estratégicas para alcançar objetivos comuns?",
                        "Como compartilha boas práticas com outras empresas?",
                        "Participa de redes ou fóruns relacionados aos ODS?"
                };
                break;

            default:
                System.out.println("ODS não reconhecido.");
                perguntas = new String[]{
                        "Pergunta padrão 1",
                        "Pergunta padrão 2",
                        "Pergunta padrão 3",
                        "Pergunta padrão 4",
                        "Pergunta padrão 5"
                };
                break;
        }

        for(String pergunta : perguntas){
            JCheckBox checkBox = new JCheckBox(pergunta);
            checkBox.setBackground(Color.WHITE);
            checkBox.setForeground(Color.BLACK);
            checkBox.setFont(new Font("Roboto", Font.PLAIN, 25));
            checkBox.setSelected(false);
            checklistPanel.add(checkBox);

        }

        return checklistPanel;
    }
}
