import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrincipalClienteFrame extends JFrame {

    // Cor principal
    private static final Color COR_VERDE = new Color(0x9DB984);
    private static final Color COR_BRANCO = Color.WHITE;

    // Componentes da interface
    private JTextField campoPesquisa;
    private JPanel painelResultados;
    private JScrollPane scrollResultados;

    // Lista de empresas carregadas do arquivo
    private List<String> todasEmpresas;



    public PrincipalClienteFrame() {
        inicializarEmpresas();
        configurarJanela();
        criarComponentes();
        configurarLayout();
        configurarEventos();
    }

    private void inicializarEmpresas() {
        todasEmpresas = new ArrayList<>();
        carregarEmpresasDoArquivo();
    }

    private void carregarEmpresasDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("empresa.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {


                linha = linha.trim();

                String[] parts = new String[5];
                if (!linha.isEmpty()) {
                    parts = linha.split(",");
                }
                if (parts.length == 5) {
                    todasEmpresas.add(parts[0].trim());
                }
            }

            if (todasEmpresas.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "O arquivo empresa.txt está vazio!",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (IOException e) {
            // Se não conseguir ler o arquivo, mostrar erro e criar arquivo de exemplo
            JOptionPane.showMessageDialog(this,
                    "Não foi possível carregar o arquivo empresa.txt.\n" +
                            "Certifique-se de que o arquivo existe no diretório do programa.\n\n" +
                            "Erro: " + e.getMessage(),
                    "Erro ao carregar arquivo",
                    JOptionPane.ERROR_MESSAGE);

            // Adicionar algumas empresas de exemplo para demonstração
            criarEmpresasExemplo();
        }
    }

    private void criarEmpresasExemplo() {
        todasEmpresas.add("Microsoft Corporation");
        todasEmpresas.add("Apple Inc.");
        todasEmpresas.add("Google LLC");
        todasEmpresas.add("Amazon.com Inc.");
        todasEmpresas.add("Meta Platforms Inc.");
        todasEmpresas.add("Tesla Inc.");
        todasEmpresas.add("Netflix Inc.");
        todasEmpresas.add("Adobe Inc.");
        todasEmpresas.add("Oracle Corporation");
        todasEmpresas.add("Salesforce Inc.");

        JOptionPane.showMessageDialog(this,
                "Carregando empresas de exemplo.\n" +
                        "Crie um arquivo 'empresa.txt' com uma empresa por linha.",
                "Usando dados de exemplo",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void configurarJanela() {
        setTitle("Pesquisa de Empresas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setBackground(COR_BRANCO);
    }

    private void criarComponentes() {
        // Campo de pesquisa
        campoPesquisa = new JTextField();
        campoPesquisa.setFont(new Font("Arial", Font.PLAIN, 16));
        campoPesquisa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_VERDE, 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        campoPesquisa.setBackground(COR_BRANCO);

        // Placeholder para o campo de pesquisa
        campoPesquisa.setForeground(Color.GRAY);
        campoPesquisa.setText("Digite o nome da empresa...");

        // Painel para os resultados
        painelResultados = new JPanel();
        painelResultados.setLayout(new BoxLayout(painelResultados, BoxLayout.Y_AXIS));
        painelResultados.setBackground(COR_BRANCO);
        painelResultados.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Scroll para os resultados
        scrollResultados = new JScrollPane(painelResultados);
        scrollResultados.setBorder(BorderFactory.createLineBorder(COR_VERDE, 1));
        scrollResultados.setBackground(COR_BRANCO);
        scrollResultados.getViewport().setBackground(COR_BRANCO);
        scrollResultados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollResultados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        // Painel superior com título e campo de pesquisa
        JPanel painelSuperior = new JPanel(new BorderLayout(10, 10));
        painelSuperior.setBackground(COR_BRANCO);
        painelSuperior.setBorder(new EmptyBorder(20, 20, 10, 20));

        // Título
        JLabel titulo = new JLabel("Pesquisa de Empresas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(COR_VERDE);

        painelSuperior.add(titulo, BorderLayout.NORTH);
        painelSuperior.add(campoPesquisa, BorderLayout.CENTER);

        // Painel central com resultados
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(COR_BRANCO);
        painelCentral.setBorder(new EmptyBorder(0, 20, 20, 20));

        JLabel labelResultados = new JLabel("Resultados:");
        labelResultados.setFont(new Font("Arial", Font.BOLD, 16));
        labelResultados.setForeground(COR_VERDE);
        labelResultados.setBorder(new EmptyBorder(0, 0, 10, 0));

        painelCentral.add(labelResultados, BorderLayout.NORTH);
        painelCentral.add(scrollResultados, BorderLayout.CENTER);

        // Painel inferior com informações do arquivo
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelInferior.setBackground(COR_BRANCO);
        painelInferior.setBorder(new EmptyBorder(0, 20, 10, 20));

        JLabel infoArquivo = new JLabel("Total de empresas carregadas: " + todasEmpresas.size());
        infoArquivo.setFont(new Font("Arial", Font.ITALIC, 12));
        infoArquivo.setForeground(COR_VERDE);

        painelInferior.add(infoArquivo);

        add(painelSuperior, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        // Evento para limpar placeholder
        campoPesquisa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (campoPesquisa.getText().equals("Digite o nome da empresa...")) {
                    campoPesquisa.setText("");
                    campoPesquisa.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (campoPesquisa.getText().isEmpty()) {
                    campoPesquisa.setForeground(Color.GRAY);
                    campoPesquisa.setText("Digite o nome da empresa...");
                }
            }
        });

        // Evento para pesquisa em tempo real
        campoPesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                realizarPesquisa();
            }
        });

        // Mostrar todas as empresas inicialmente
        mostrarTodasEmpresas();
    }

    private void realizarPesquisa() {
        String termoPesquisa = campoPesquisa.getText().trim();

        // Se o campo estiver vazio ou com placeholder, mostrar todas
        if (termoPesquisa.isEmpty() || termoPesquisa.equals("Digite o nome da empresa...")) {
            mostrarTodasEmpresas();
            return;
        }

        // Filtrar empresas que contenham o termo de pesquisa
        List<String> empresasFiltradas = todasEmpresas.stream()
                .filter(empresa -> empresa.toLowerCase().contains(termoPesquisa.toLowerCase()))
                .collect(Collectors.toList());

        atualizarResultados(empresasFiltradas);
    }

    private void mostrarTodasEmpresas() {
        atualizarResultados(todasEmpresas);
    }

    private void atualizarResultados(List<String> empresas) {
        painelResultados.removeAll();

        if (empresas.isEmpty()) {
            JLabel semResultados = new JLabel("Nenhuma empresa encontrada");
            semResultados.setFont(new Font("Arial", Font.ITALIC, 14));
            semResultados.setForeground(Color.GRAY);
            semResultados.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelResultados.add(semResultados);
        } else {
            for (String empresa : empresas) {
                JPanel itemEmpresa = criarItemEmpresa(empresa);
                painelResultados.add(itemEmpresa);
                painelResultados.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }

        painelResultados.revalidate();
        painelResultados.repaint();

        // Voltar ao topo da lista
        SwingUtilities.invokeLater(() -> {
            scrollResultados.getVerticalScrollBar().setValue(0);
        });
    }

    private JPanel criarItemEmpresa(String nomeEmpresa) {
        JPanel item = new JPanel(new BorderLayout());
        item.setBackground(COR_BRANCO);
        item.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_VERDE, 1),
                new EmptyBorder(15, 20, 15, 20)
        ));
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel labelEmpresa = new JLabel(nomeEmpresa);
        labelEmpresa.setFont(new Font("Arial", Font.PLAIN, 14));
        labelEmpresa.setForeground(Color.BLACK);

        // Efeito hover
        item.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarDialogoAvaliacao(nomeEmpresa);
            }
        });

        item.add(labelEmpresa, BorderLayout.WEST);

        return item;
    }

    private void mostrarDialogoAvaliacao(String nomeEmpresa) {
        // Criar um JDialog personalizado
        JDialog dialogoAvaliacao = new JDialog(this, "Avaliação - " + nomeEmpresa, true);
        dialogoAvaliacao.setSize(450, 350);
        dialogoAvaliacao.setLocationRelativeTo(this);
        dialogoAvaliacao.setResizable(false);
        dialogoAvaliacao.setLayout(new BorderLayout());
        dialogoAvaliacao.getContentPane().setBackground(COR_BRANCO);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBackground(COR_BRANCO);
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Avalie a empresa: " + nomeEmpresa);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(COR_VERDE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel de avaliação com estrelas
        JPanel painelEstrelas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelEstrelas.setBackground(COR_BRANCO);

        JLabel labelAvaliacao = new JLabel("Sua avaliação: ");
        labelAvaliacao.setFont(new Font("Arial", Font.PLAIN, 14));

        // Grupo de botões para as estrelas
        ButtonGroup grupoEstrelas = new ButtonGroup();
        JPanel estrelas = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        estrelas.setBackground(COR_BRANCO);

        // Variável para armazenar a avaliação selecionada
        final int[] avaliacaoSelecionada = {0};

        // Criar 5 botões de rádio para as estrelas
        for (int i = 1; i <= 5; i++) {
            final int valor = i;
            JRadioButton estrela = new JRadioButton(String.valueOf(i));
            estrela.setFont(new Font("Arial", Font.BOLD, 16));
            estrela.setForeground(COR_VERDE);
            estrela.setBackground(COR_BRANCO);
            estrela.setFocusPainted(false);

            estrela.addActionListener(e -> avaliacaoSelecionada[0] = valor);

            grupoEstrelas.add(estrela);
            estrelas.add(estrela);

            // Selecionar a terceira estrela por padrão
            if (i == 3) {
                estrela.setSelected(true);
                avaliacaoSelecionada[0] = 3;
            }
        }

        painelEstrelas.add(labelAvaliacao);
        painelEstrelas.add(estrelas);

        // Área para reclamação
        JLabel labelReclamacao = new JLabel("Descreva sua reclamação ou comentário:");
        labelReclamacao.setFont(new Font("Arial", Font.PLAIN, 14));
        labelReclamacao.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea areaReclamacao = new JTextArea();
        areaReclamacao.setFont(new Font("Arial", Font.PLAIN, 14));
        areaReclamacao.setLineWrap(true);
        areaReclamacao.setWrapStyleWord(true);
        areaReclamacao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_VERDE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JScrollPane scrollReclamacao = new JScrollPane(areaReclamacao);
        scrollReclamacao.setPreferredSize(new Dimension(400, 150));
        scrollReclamacao.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.setBackground(COR_BRANCO);

        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBackground(COR_BRANCO);
        botaoCancelar.setForeground(Color.BLACK);
        botaoCancelar.addActionListener(e -> dialogoAvaliacao.dispose());

        JButton botaoEnviar = new JButton("Enviar Avaliação");
        botaoEnviar.setBackground(COR_VERDE);
        botaoEnviar.setForeground(Color.BLACK);
        botaoEnviar.addActionListener(e -> {
            String reclamacao = areaReclamacao.getText().trim();
            int avaliacao = avaliacaoSelecionada[0];

            // Aqui você poderia salvar a avaliação em um arquivo ou banco de dados
            JOptionPane.showMessageDialog(dialogoAvaliacao,
                    "Avaliação enviada com sucesso!\n\n" +
                            "Empresa: " + nomeEmpresa + "\n" +
                            "Nota: " + avaliacao + " estrelas\n" +
                            "Comentário: " + (reclamacao.isEmpty() ? "Nenhum" : reclamacao),
                    "Avaliação Registrada",
                    JOptionPane.INFORMATION_MESSAGE);

            dialogoAvaliacao.dispose();
        });

        painelBotoes.add(botaoCancelar);
        painelBotoes.add(botaoEnviar);

        // Adicionar componentes ao painel principal
        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        painelPrincipal.add(painelEstrelas);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        painelPrincipal.add(labelReclamacao);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        painelPrincipal.add(scrollReclamacao);

        // Adicionar componentes ao diálogo
        dialogoAvaliacao.add(painelPrincipal, BorderLayout.CENTER);
        dialogoAvaliacao.add(painelBotoes, BorderLayout.SOUTH);

        // Exibir o diálogo
        dialogoAvaliacao.setVisible(true);
    }

}