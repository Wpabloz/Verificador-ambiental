public class Main {
    public static void main(String[] args) {
        // Criação do JFrame
       try {
            InicialFrame inicialPage = new InicialFrame();
            inicialPage.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
