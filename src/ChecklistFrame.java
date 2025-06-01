import javax.swing.*;
import java.awt.*;

public class ChecklistFrame extends JFrame {


    public ChecklistFrame(String[] perguntas){
        inicializarComponentes();
        configurarLayout(perguntas);
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

    private void configurarLayout(String[] perguntas) {
        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        JPanel mainPanel = new JPanel();
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

        containerPanel.add(criarCheckboxPerguntas(perguntas));

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Roboto", Font.BOLD, 18));
        btnContinuar.setBackground(Color.decode("#2E7D32"));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));


        containerPanel.add(btnContinuar);

        mainPanel.add(containerPanel, BorderLayout.CENTER);

        add(scrollPane.add(mainPanel), BorderLayout.CENTER);
    }

    private void configurarEventos() {
    }

    private JPanel criarCheckboxPerguntas(String[] perguntas) {
        JPanel checklistPanel = new JPanel();
        checklistPanel.setLayout(new BoxLayout(checklistPanel, BoxLayout.Y_AXIS));
        checklistPanel.setBackground(Color.WHITE);


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
