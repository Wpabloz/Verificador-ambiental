import java.time.LocalDate;

public class Selo {
    private LocalDate emissao;
    private boolean valido;
    private TipoSelo tipoSelo;

    //construtor
    public Selo(LocalDate emissao, boolean valido) {
        this.emissao = emissao;
        this.valido = valido;
    }

    //gets e sets
    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public LocalDate getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDate emissao) {
        this.emissao = emissao;
    }

    public TipoSelo getTipoSelo() {
        return tipoSelo;
    }

    public void setTipoSelo(TipoSelo tipoSelo) {
        this.tipoSelo = tipoSelo;
    }
}