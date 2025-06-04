public class Empresa extends Usuario {
    private String cnpj;
    private Atividade atividade;
    private boolean statusAmbiental;
    private Selo[] selos;


    //construtor
    public Empresa(String nome, String username, String senha, String cnpj, Atividade atividade) {
        super(nome, username, senha);
        this.cnpj = formatarCNPJ(cnpj.replaceAll("[^0-9]", ""));
        this.atividade = atividade;
        this.statusAmbiental = false;
        this.selos = new Selo[TipoSelo.values().length];

        for (int i = 0; i < TipoSelo.values().length; i++) {

            selos[i] = new Selo(0, TipoSelo.values()[i]);
            System.out.println(selos[i].getTipoSelo());
        }
    }

    public Empresa(String nome, String username, String senha, String cnpj, Atividade atividade, boolean statusAmbiental, Selo[] selos) {
        super(nome, username, senha);
        this.cnpj = formatarCNPJ(cnpj.replaceAll("[^0-9]", ""));
        this.atividade = atividade;
        this.statusAmbiental = false;
        this.selos = new Selo[TipoSelo.values().length];

        for (int i = 0; i < TipoSelo.values().length; i++) {

            selos[i] = new Selo(0, TipoSelo.values()[i]);
            System.out.println(selos[i].getTipoSelo());
        }
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

    public Selo[] getSelos() {
        return selos;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public void setCnpj(String cnpj) {

        this.cnpj = formatarCNPJ(cnpj.replaceAll("[^0-9]", ""));
    }

    //Métodos

    public boolean verificaPendencias() {
        return !statusAmbiental;
    }

    public Selo getSelo(TipoSelo tipoSelo){
        for (int i = 0; i < selos.length; i++) {
            Selo selo = selos[i];
            if (selo.getTipoSelo() == tipoSelo) {
                return selo;
            }
        }
        return null;
    }

    public Double getProgressoSelo(TipoSelo tipoSelo){
        for (int i = 0; i < selos.length; i++) {
            Selo selo = selos[i];
            if (selo.getTipoSelo() == tipoSelo) {
                System.out.println(selo.getProgresso());
                return selo.getProgresso();
            }
        }
        return null;
    }


    public void setProgressoSelo(TipoSelo tipoSelo, double progresso) {
        for (Selo selo : selos) {
            if (selo.getTipoSelo() == tipoSelo) {
                System.out.println(progresso);
                selo.setProgresso(progresso);
                break;
            }
        };
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
