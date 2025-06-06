import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class CadastroEFrameTest {

    private CadastroEFrame frame;
    private final String arquivoTeste = "arquivo_teste_empresa.txt";

    @BeforeEach
    public void setUp() {
        frame = new CadastroEFrame() {
            @Override
            protected void salvarEmpresa(Empresa empresa) {
                try (FileWriter fw = new FileWriter(arquivoTeste, true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter pw = new PrintWriter(bw)) {

                    String linha = empresa.getNome() + "," + empresa.getUsername() + "," + empresa.getSenha() + "," + empresa.getCnpj() + "," + empresa.getAtividade();
                    pw.println(linha);

                } catch (IOException erro) {
                    fail("Erro ao salvar empresa: " + erro.getMessage());
                }
            }
        };

        File file = new File(arquivoTeste);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSalvarEmpresa() throws IOException {
        String nome = "João";
        String usuario = "joao123";
        String senha = "senha123";
        String cnpj = "12.345.678/9123-40";
        Atividade atividade = Atividade.ESPORTES;

        Empresa empresa = new Empresa(nome, usuario, senha, cnpj, atividade);
        frame.salvarEmpresa(empresa);

        File file = new File(arquivoTeste);
        assertTrue(file.exists(), "Arquivo não foi criado!");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha = br.readLine();
            assertNotNull(linha, "Nenhuma linha foi lida do arquivo!");

            String[] dados = linha.split(",");

            assertEquals(nome, dados[0], "Nome incorreto");
            assertEquals(usuario, dados[1], "Username incorreto");
            assertEquals(senha, dados[2], "Senha incorreta");
            assertEquals(cnpj, dados[3], "CNPJ incorreto");
            assertEquals("ESPORTES", dados[4], "Atividade incorreta");
        }
    }

}
