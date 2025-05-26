public abstract class Usuario {
    private String id;
    protected String nome;
    protected String username;
    protected String senha;

    // Construtor
    public Usuario(String id, String nome, String username, String senha) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.senha = senha;
    }

    // Gets e Sets necessários
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    protected void setSenha(String senha) {
        this.senha = senha;
    }

}
