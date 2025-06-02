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

                    String linha = "Dados do cliente: " + cliente.getNome() + " | " + cliente.getUsername() + " | " + cliente.getSenha() + " | " + cliente.getCpf() + " | " + cliente.getNumeroTel();
                    pw.println(linha);

                } catch (IOException erro) {
                    fail("Erro ao salvar cliente: " + erro.getMessage());
                }
            }
        };

        File file = new File(arquivoTeste);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSalvarCliente() throws IOException {
        String nome = "João";
        String usuario = "joao123";
        String senha = "senha123";
        String cpf = "12345678900";
        String telefone = "99999999999";

        Cliente cliente = new Cliente(nome, usuario, senha, cpf, telefone);
        frame.salvarCliente(cliente);

        File file = new File(arquivoTeste);
        assertTrue(file.exists(), "Arquivo não foi criado!");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String linha = br.readLine();
        br.close();

        String esperado = "Dados do cliente: " + cliente.getNome() + " | " + cliente.getUsername() + " | " + cliente.getSenha() + " | " + cliente.getCpf() + " | " + cliente.getNumeroTel();
        assertEquals(esperado, linha, "Conteúdo do arquivo não corresponde ao esperado.");
    }
}
