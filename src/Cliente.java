public class Cliente extends Usuario {
    private String cpf;
    private String numeroTel;

    //construtor
    public Cliente(String nome, String username, String senha, String cpf, String numeroTel) {
        super(nome, username, senha);
        this.cpf =  formatarCpf(cpf.replaceAll("[^0-9]", ""));
        this.numeroTel = formatarTelefone(numeroTel.replaceAll("[^0-9]", ""));
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | " + "Usuário: " + username + " | " + " Senha: " + senha + " | " + "CPF: " + cpf + " | " + "Telefone: "+ numeroTel;
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

    private String formatarCpf(String cpfNumerico) {
        return cpfNumerico.substring(0, 3) + "." +
                cpfNumerico.substring(3, 6) + "." +
                cpfNumerico.substring(6, 9) + "-" +
                cpfNumerico.substring(9, 11);
    }

    private String formatarTelefone(String telefoneNumerico) {
        return "(" + telefoneNumerico.substring(0, 2) + ")" +
                telefoneNumerico.substring(2, 7) + "-" +
                telefoneNumerico.substring(7, 11);
    }
}
