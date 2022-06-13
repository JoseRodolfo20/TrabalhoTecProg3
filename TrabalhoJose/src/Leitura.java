import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Leitura {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int csv = -1;
		String nomes, nomeArquivo = "";
		String lPath = System.getenv("CSV_FILE");
		File cPath = new File(lPath);
		List<String> fContent = new ArrayList<>();
		
		System.out.println("\t\tREGISTRAR CHAMADA");
		do {
			System.out.println("Nome do arquivo:\n\t0 - Sair.");
			nomeArquivo = in.nextLine();
			if(nomeArquivo.equals("0")) {
				break;
			}else {
				for(File f: cPath.listFiles()) {
					if(f.getName().equalsIgnoreCase(nomeArquivo+".csv")) {
						if(f.canRead()) {
							fContent = readFile(f);
							break;
						}
					}
				}
			}
			if(fContent.size() == 0) {
				System.out.println("Nenhum arquivo foi encontrado.");
			}else {
				csv = menu(fContent);
				if(csv == 0) {
					break;
				}else {
					System.out.println("Realizar chamada: "+
					fContent.get(csv - 1)+
					"\n	9 - Sair.");
					
					nomes = getnomes(fContent.get(csv - 1));
					Criacao cA = new Criacao(nomes, lPath, fContent.get(csv - 1));
					fContent.clear();
				}
			}
		}while(true);
		System.out.println("Até!");
		in.close();
	}
	
	public static List<String> readFile(File ler) {
		Scanner lerLinhas = null;
		List<String> fContent = new ArrayList<>();
		try {
			lerLinhas = new Scanner(ler);
			while(lerLinhas.hasNextLine()) {
				String host = lerLinhas.nextLine();
				String[] xA = host.split("\n|;");
				for(String e: xA) {
					fContent.add(e);
				}	
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro:::");
		}finally {
			lerLinhas.close();
		}
		return fContent;
		
	}
	public static int menu(List<String> objectes) {
		System.out.println("\t\t:::CHAMADA:::");
		for(int i = 0; i < objectes.size(); i++) {
			System.out.println("\t["+(i + 1)+"] "+objectes.get(i));
		}
		System.out.println("\t[0] Sair");
		int csv;
		do {
			System.out.print(">>");
			csv = readInt();
			if(csv == 0) {
				break;
			}
		}while(csv < 0 || csv > objectes.size());
		return csv;
		
	}
	public static int readInt() {
		Scanner in = new Scanner(System.in);
		int csv = -1;
		try {
			csv = in.nextInt();
		}catch(InputMismatchException e){
			System.out.println("Digite.");
		}
		in.nextLine();
		return csv;
	}
	public static String getnomes(String subject) {
		Scanner in = new Scanner(System.in);
		String entrar;
		String nomes = "";
		do {
			System.out.print(">>");
			entrar = in.nextLine();
			if(entrar.equals("9")) {
				break;
			}else {
				nomes += entrar +"\n";
			}
		}while(true);
		return nomes;
	}
}