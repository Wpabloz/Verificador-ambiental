import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class CadastroCFrameTest {

    private CadastroCFrame frame;
    private final String arquivoTeste = "arquivo_teste.txt";

    @BeforeEach
    public void setUp() {
        frame = new CadastroCFrame() {
            @Override
            protected void salvarCliente(Cliente cliente) {
                try (FileWriter fw = new FileWriter(arquivoTeste, true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter pw = new PrintWriter(bw)) {

                    String linha = cliente.getNome() + "," +
                            cliente.getUsername() + "," +
                            cliente.getSenha() + "," +
                            cliente.getCpf() + "," +
                            cliente.getNumeroTel();
                    pw.println(linha);

                } catch (IOException erro) {
                    fail("Erro ao salvar cliente: " + erro.getMessage());
                }
            }
        };

        // Apaga o arquivo de teste se já existir
        File file = new File(arquivoTeste);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSalvarCliente() throws IOException {
        // Dados esperados
        String nome = "João";
        String usuario = "joao123";
        String senha = "senha123";
        String cpf = "123.456.789-00";
        String telefone = "(99)99999-9999";

        // Cria o cliente e salva
        Cliente cliente = new Cliente(nome, usuario, senha, cpf, telefone);
        frame.salvarCliente(cliente);

        // Verifica se o arquivo foi criado
        File file = new File(arquivoTeste);
        assertTrue(file.exists(), "Arquivo não foi criado!");

        // Lê a primeira linha do arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha = br.readLine();
            assertNotNull(linha, "Nenhuma linha foi lida do arquivo!");

            String[] dados = linha.split(",");

            // Compara cada campo com os valores esperados
            assertEquals(nome,     dados[0], "Nome incorreto");
            assertEquals(usuario,  dados[1], "Username incorreto");
            assertEquals(senha,    dados[2], "Senha incorreta");
            assertEquals(cpf,      dados[3], "CPF incorreto");
            assertEquals(telefone, dados[4], "Telefone incorreto");
        }
    }
}
