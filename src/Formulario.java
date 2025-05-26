import java.util.ArrayList;

public class Formulario {
    private Empresa entrevistado;
    private ArrayList<String> perguntas;
    private ArrayList<Boolean> respostas;

    //construtor
    public Formulario(Empresa entrevistado, ArrayList<String> perguntas) {
        this.entrevistado = entrevistado;
        this.perguntas = perguntas;
        this.respostas = new ArrayList<>();
        // Inicializa as respostas como nulas
        for (int i = 0; i < perguntas.size(); i++) {
            respostas.add(null);
        }
    }

    // ✅ Preenche o formulário com as respostas
    public void preencherFormulario(ArrayList<Boolean> respostas) {
        if (respostas.size() == perguntas.size()) {
            this.respostas = respostas;
        } else {
            System.out.println("Número de respostas incompatível com o de perguntas.");
        }
    }

    // ✅ Mostra as respostas do formulário
    public void mostrarRespostas() {
        for (int i = 0; i < perguntas.size(); i++) {
            System.out.println(perguntas.get(i) + " -> " +
                    (respostas.get(i) != null ? respostas.get(i) : "Não respondido"));
        }
    }

    // Gets e Sets
    public Empresa getEntrevistado() {
        return entrevistado;
    }

    public ArrayList<String> getPerguntas() {
        return perguntas;
    }

    public ArrayList<Boolean> getRespostas() {
        return respostas;
    }

    public void setEntrevistado(Empresa entrevistado) {
        this.entrevistado = entrevistado;
    }

    public void setPerguntas(ArrayList<String> perguntas) {
        this.perguntas = perguntas;
        // Sempre que atualizar as perguntas, zera as respostas
        this.respostas = new ArrayList<>();
        for (int i = 0; i < perguntas.size(); i++) {
            respostas.add(null);
        }
    }

    public void setRespostas(ArrayList<Boolean> respostas) {
        this.respostas = respostas;
    }
}
