import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Criacao{

    public Criacao(String nomes, String lPath, String objectNome){
        String Path = lPath + "\\"+nomeArquivo(objectNome);
        File novoArquivo = new File(Path);
        try{
            FileWriter writenFile = new FileWriter(Path);
            writenFile.write(nomes);
            writenFile.close();
            salvarArquivo(novoArquivo);

            System.out.println("\tArquivo Salvo:" );
        }catch(IOException x){
            System.out.println("Erro ao escrever");
        }
    }

    private void salvarArquivo(File novoArquivo) {
        try{
            novoArquivo.createNewFile();
        }catch(IOException x){
            System.out.println("Erro ao criar o arquivo");
        }
    }

    private String nomeArquivo(String objectNome) {
        objectNome = objectNome.toLowerCase().replace(' ', '_')+"_";
        LocalDate cDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatoData = cDate.format(formatter).replace('-', '_');
        return objectNome+formatoData+".txt";
    }

}