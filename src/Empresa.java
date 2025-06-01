public class Empresa extends Usuario {
    private String cnpj;
    private Atividade atividade;
    private boolean statusAmbiental;
    private Selo seloVerde;


    //construtor
    public Empresa(String nome, String username, String senha, String cnpj, Atividade atividade) {
        super(nome, username, senha);
        this.cnpj = cnpj;
        this.atividade = atividade;
        this.statusAmbiental = false;  // padrão
    }

    // Gets e Sets
    public String getCnpj() {
        return cnpj;
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
