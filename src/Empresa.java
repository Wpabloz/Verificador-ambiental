public class Empresa extends Usuario {
    private String cnpj;
    private Atividade atividade;
    private boolean statusAmbiental;
    private Selo seloVerde;


    //construtor
    public Empresa(String nome, String username, String senha, String cnpj, Atividade atividade) {
        super(nome, username, senha);
        this.cnpj = formatarCNPJ(cnpj.replaceAll("[^0-9]", ""));
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

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public void setCnpj(String cnpj) {

        this.cnpj = formatarCNPJ(cnpj.replaceAll("[^0-9]", ""));
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

    private String formatarCNPJ(String s) {
        return s.substring(0, 2) + "." +
                s.substring(2, 5) + "." +
                s.substring(5, 8) + "/" +
                s.substring(8, 12) + "-" +
                s.substring(12, 14);
    }

    public static String removerMascaraCNPJ(String cnpj) {
        return cnpj.replaceAll("[^0-9]", "");  // Remove tudo que não for número
    }


    public Empresa copiar() {
        return new Empresa(this.nome, this.username, this.senha, this.cnpj, this.atividade);
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "atividade=" + atividade +
                ", cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
