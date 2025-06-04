import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChecklistFrame extends JFrame {
    JButton btnContinuar;
    JScrollPane scrollPane;
    JPanel mainPanel, containerPanel;

    public ChecklistFrame(TipoSelo selo, Empresa empresaLogada) {
        inicializarComponentes();
        configurarLayout(selo);
        configurarEventos(empresaLogada, selo);
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

        containerPanel = new JPanel();
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

    private void configurarEventos(Empresa empresaLogada, TipoSelo selo) {
        ArrayList<JCheckBox> selectedCheckboxes = new ArrayList<>();
        btnContinuar.addActionListener(e -> {
            // Lógica para continuar o processo
            Component[] componentes = containerPanel.getComponents();
            for (Component componente : componentes) {
                if (componente instanceof JPanel) {
                    Component[] checkboxes = ((JPanel) componente).getComponents();
                    for (Component checkbox : checkboxes) {
                        if (checkbox instanceof JCheckBox) {
                            JCheckBox checkBox = (JCheckBox) checkbox;
                            if (checkBox.isSelected()) {
                                selectedCheckboxes.add(checkBox);
                            }
                        }
                    }
                }
            }

            double progresso = selectedCheckboxes.size() / 5.0;

            empresaLogada.setProgressoSelo(selo, progresso);
            // Por exemplo, você pode abrir uma nova janela ou realizar outras ações
            // Neste exemplo, vamos apenas exibir uma mensagem de confirmação
            JOptionPane.showMessageDialog(this, "Checklist concluído!");
            DashboardFrame dashboardFrame = new DashboardFrame(empresaLogada);
            dashboardFrame.setVisible(true);
            dispose();
        });
    }

    private JPanel criarCheckboxPerguntas(TipoSelo selo) {
        JPanel checklistPanel = new JPanel();
        checklistPanel.setLayout(new BoxLayout(checklistPanel, BoxLayout.Y_AXIS));
        checklistPanel.setBackground(Color.WHITE);

        String[] perguntas = Selo.perguntasSelo(selo);

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
