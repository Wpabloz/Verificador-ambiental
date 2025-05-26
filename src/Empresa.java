public class Empresa extends Usuario {
    private String cnpj;
    private Endereco endereco;
    private Atividade atividade;
    private boolean statusAmbiental;
    private Selo seloVerde;

    //construtor
    public Empresa(String id, String nome, String username, String senha, String cnpj, Endereco endereco, Atividade atividade) {
        super(id, nome, username, senha);
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.atividade = atividade;
        this.statusAmbiental = false;  // padrão
    }

    // Gets e Sets
    public String getCnpj() {
        return cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public boolean isStatusAmbiental() {
        return statusAmbiental;
    }

    public Selo getSeloVerde() {
        return seloVerde;
    }


    //Métodos
    public void gerarSelo() {
        if (statusAmbiental) {
            this.seloVerde = new Selo(java.time.LocalDate.now(), true);
        }
    }
    public boolean verificaPendencias() {
        return !statusAmbiental;
    }


}
