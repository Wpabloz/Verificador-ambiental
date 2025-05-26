public class Cliente extends Usuario {
    private String cpf;
    private String numeroTel;

    //construtor
    public Cliente(String id, String nome, String username, String senha, String cpf, String numeroTel) {
        super(id, nome, username, senha);
        this.cpf = cpf;
        this.numeroTel = numeroTel;
    }

    // Gets e Sets
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }
}
