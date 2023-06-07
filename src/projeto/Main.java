package projeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        List<Produto> produtos = new ArrayList<>();
        DecimalFormat deci = new DecimalFormat("0.00");

        System.out.println("Entre com local do arquivo");
        String arquivostr = scan.nextLine();

        File arquivo = new File(arquivostr);
        String localArquivoStr = arquivo.getParent(); //pega o caminho desprezando o nome do arquivo

        boolean sucesso = new File(localArquivoStr + "\\out").mkdir();

        String arquivoAlvostr = localArquivoStr + "\\out\\summary.csv";

        //Faz a leitura do arquivo colocando o caminho e nome do arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(arquivostr))) {

            String itemCsv = br.readLine();
            while (itemCsv != null) {

                String[] campos = itemCsv.split(",");
                String nome = campos[0];
                double preco = Double.parseDouble(campos[1]);
                int quantidade = Integer.parseInt(campos[2]);

                produtos.add(new Produto(nome, preco, quantidade));

                itemCsv = br.readLine();
            }

            //Faz a escrita do Arquivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoAlvostr))) {
                for (Produto produto : produtos) {
                    bw.write(produto.getNome() + "," + deci.format(produto.total()));
                    bw.newLine();

                }
                System.out.println(arquivoAlvostr +" CRIADO");
            } catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

}
