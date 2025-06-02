import javax.swing.*;
import java.awt.*;

public class EditarDialog extends JDialog {
    private JTextField nomeField, usuarioField, cnpjField;
    private JComboBox<String> atividadeField;
    private JButton salvarButton;

    public EditarDialog(JFrame parent, Empresa empresa) {
        super(parent, "Editar Usuário", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        String[] opcoesAtividade = new String[Atividade.values().length];
        Atividade[] atividades = Atividade.values();
        for (int i = 0; i < atividades.length; i++) {
            opcoesAtividade[i] = atividades[i].toString();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.decode("#9DB984"));

        nomeField = new JTextField(empresa.getNome());
        usuarioField = new JTextField(empresa.getUsername());
        cnpjField = new JTextField(Empresa.removerMascaraCNPJ(empresa.getCnpj()));
        atividadeField = new JComboBox<>(opcoesAtividade);
        Component rigidArea = Box.createRigidArea(new Dimension(0, 10));
        salvarButton = new JButton("Salvar");



        atividadeField.setSelectedItem(empresa.getAtividade().toString());

        panel.add(criarboxInput(new JLabel("Nome"), nomeField));
        panel.add(criarboxInput(new JLabel("Usuário"), usuarioField));
        panel.add(criarboxInput(new JLabel("CNPJ"), cnpjField));
        panel.add(criarboxInput(new JLabel("Atividade"), atividadeField));
        panel.add(rigidArea);
        panel.add(salvarButton);

        add(panel);

        eventos(empresa);
    }

    private void eventos(Empresa empresa) {
        // Lógica para salvar a edição
        salvarButton.addActionListener(e -> {

            if (nomeField.getText().isEmpty() || usuarioField.getText().isEmpty() || cnpjField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (usuarioField.getText().length() < 5) {
                JOptionPane.showMessageDialog(this, "O nome de usuário deve ter pelo menos 5 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (cnpjField.getText().length() != 14) {
                JOptionPane.showMessageDialog(this, "O CPF deve ter 14 dígitos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;

            } else  {
                empresa.setNome(nomeField.getText());
                empresa.setUsername(usuarioField.getText());
                empresa.setCnpj(cnpjField.getText());
                empresa.setAtividade(Atividade.valueOf(atividadeField.getSelectedItem().toString()));

                CadastroEFrame.salvarEmpresa(empresa);

                dispose();
            }





        });
    }

    private JPanel criarboxInput(JLabel label, Component textField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(textField);
        return panel;
    }
}
