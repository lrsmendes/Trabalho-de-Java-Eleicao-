/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.trabalho2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Victal Kayke
 */
public class Trabalho2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //path do arquivo csv contendo os dados de votações
        String link = "C:\\Users\\victa\\Downloads\\votacao_secao_2022_SP\\votacao_secao_2022_SP.csv";
        File file = new File(link);
        //bloco de execução do code
        //atribuição das variaveis do que foi solicitado buscar na planilha
        int secaoEscolhida = 0;
        int NR_SECAO = 0;
        int NR_VOTAVEL = 0;
        String NM_VOTAVEL;
        int NR_TURNO = 0;
        int QT_VOTOS = 0;
        int contNulos = 0;
        int contBranco = 0;
        int contRepublicanosGov = 0;
        int contPTGov = 0;
        float soma = 0;
        double percentual13 = 0;
        double percentual10 = 0;
        double percentualBranco = 0;
        double percentualNulo = 0;
        //bloco try com recursos file e buffered para ler o arquivo csv
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            //atribuição de uma leitura de linha somente para pular a 
            //leitura direto para a segunda linha que tem os dados pertinentes
            br.readLine();
            String line = br.readLine();
            //enquanto a leitura da linha for diferente de nula, para que leia
            //enquanto tiver dados, linha em branco já sai do while
            while(line!= null){
                //atribuindo um vetor e guardando a planilha toda nele, porem
                //em cada posição vai ter uma coluna, quebrei as colunas com
                //o Split.
                String[] vetor = line.split(";");
                //if de vetor na posição 14 (coluna "O"), que é onde esta o municipio
                //se for pg ele entra e atribui os devidos valores, de:
                //nrSecao, nrVotavel, nmVotavel, qtVotos.
                if("PRAIA GRANDE".equals(vetor[14]) && NR_TURNO == 2){
                    NR_SECAO = Integer.parseInt(vetor[16]);
                    NR_VOTAVEL = Integer.parseInt(vetor[19]);
                    NM_VOTAVEL = vetor[20];
                    QT_VOTOS = Integer.parseInt(vetor[21]);
                    
                    //quantidade/contador de votos no geral
                    if(NR_VOTAVEL == 10){
                        contPTGov += QT_VOTOS;
                    }if(NR_VOTAVEL == 13){
                        contRepublicanosGov += QT_VOTOS;
                    }if(NR_VOTAVEL == 95){
                        contBranco += QT_VOTOS;
                    }if(NR_VOTAVEL == 96){
                        contNulos += QT_VOTOS;
                    }
                }
                //leitura de linha.
                line = br.readLine();  
            }
            //print
            System.out.printf("Total de votos na PG: ", calculoTotalDeVotos(contPTGov, contRepublicanosGov, contBranco, contNulos));
                System.out.println("Votos 13 na PG: " + contPTGov);
                System.out.println("Votos 10 na PG: " + contRepublicanosGov);
                System.out.println("Votos em branco na PG: " + contBranco);
                System.out.println("Votos nulos na PG: " + contNulos);
                System.out.printf("Percentual votos 13 na PG: " , percentualDeVotos13(contPTGov, QT_VOTOS) , "%");
                System.out.printf("Percentual votos 10 na PG: " , percentualDeVotos10(contRepublicanosGov, QT_VOTOS) , "%");
                System.out.printf("Percentual votos em branco na PG: " , percentualDeVotosBranco(contBranco, QT_VOTOS) , "%");
                System.out.printf("Percentual votos nulos na PG: " , percentualDeVotosNulo(contNulos, QT_VOTOS) , "%");
            
            
            System.out.println("Deseja saber os votos de alguma seção especifica? \n1.Sim\n2.Não");
            int escolha = sc.nextInt();
            if(escolha == 1){
                secaoEscolhida = 0;
                NR_SECAO = 0;
                NR_VOTAVEL = 0;
                NR_TURNO = 0;
                QT_VOTOS = 0;
                contNulos = 0;
                contBranco = 0;
                contRepublicanosGov = 0;
                contPTGov = 0;
                soma = 0;
                percentual13 = 0;
                percentual10 = 0;
                percentualBranco = 0;
                percentualNulo = 0;
                System.out.println("Qual a seção desejada?");
                secaoEscolhida = sc.nextInt();
                while(line!= null){
                    //atribuindo um vetor e guardando a planilha toda nele, porem
                    //em cada posição vai ter uma coluna, quebrei as colunas com
                    //o Split.
                    String[] vetor = line.split(";");
                    //if de vetor na posição 14 (coluna "O"), que é onde esta o municipio
                    //se for pg ele entra e atribui os devidos valores, de:
                    //nrSecao, nrVotavel, nmVotavel, qtVotos.
                    if("PRAIA GRANDE".equals(vetor[14]) && NR_TURNO == 2){
                        NR_SECAO = Integer.parseInt(vetor[16]);
                        NR_VOTAVEL = Integer.parseInt(vetor[19]);
                        NM_VOTAVEL = vetor[20];
                        QT_VOTOS = Integer.parseInt(vetor[21]);
                        if(NR_SECAO == secaoEscolhida){
                            //quantidade/contador de votos por seção
                            if(NR_VOTAVEL == 10){
                                contPTGov += QT_VOTOS;
                            }if(NR_VOTAVEL == 13){
                                contRepublicanosGov += QT_VOTOS;
                            }if(NR_VOTAVEL == 95){
                                contBranco += QT_VOTOS;
                            }if(NR_VOTAVEL == 96){
                                contNulos += QT_VOTOS;
                            }
                        }
                    }
                    //leitura de linha.
                    line = br.readLine();  
                }
                System.out.printf("Total de votos na Seção: ", calculoTotalDeVotos(contPTGov, contRepublicanosGov, contBranco, contNulos));
                System.out.println("Votos 13 na secao " + secaoEscolhida + ": " + contPTGov);
                System.out.println("Votos 10 na secao " + secaoEscolhida + ": " + contRepublicanosGov);
                System.out.println("Votos em branco na secao " + secaoEscolhida + ": " + contBranco);
                System.out.println("Votos nulos na secao " + secaoEscolhida + ": " + contNulos);
                System.out.printf("Percentual votos 13 na secao " , secaoEscolhida , ": %.2f" , percentualDeVotos13(contPTGov, QT_VOTOS) , "%");
                System.out.printf("Percentual votos 10 na secao " , secaoEscolhida , ": %.2f" , percentualDeVotos10(contRepublicanosGov, QT_VOTOS) , "%");
                System.out.printf("Percentual votos em branco na secao " , secaoEscolhida , ": %.2f" , percentualDeVotosBranco(contBranco, QT_VOTOS) , "%");
                System.out.printf("Percentual votos nulos na secao " , secaoEscolhida , ": %.2f" , percentualDeVotosNulo(contNulos, QT_VOTOS) , "%");
            }else{
                System.out.println("Ok, obrigado.");
            }
        }
        //catch para caso dê erro de leitura ou arquivo em branco
        catch(FileNotFoundException e){
            System.out.println("Erro: Arquivo não encontrado!");
        }
        //catch para tratamento de exceções genéricas
        catch(IOException e){
            System.out.println("Erro: " + e.getMessage());
        }  
    }
    
    private static double percentualDeVotos10(int contRepublicanosGov, int QT_VOTOS){
        //percentual de votos
        double percentual10 = (contRepublicanosGov / QT_VOTOS);
        return percentual10;
    }
    
    private static double percentualDeVotos13(int contPTGov,int QT_VOTOS){
        //percentual de votos
        double percentual13 = (contPTGov / QT_VOTOS);
        return percentual13;
    }
    
    private static double percentualDeVotosBranco(int QT_VOTOS, int contBranco){
        //percentual de votos
        double percentualBranco = (contBranco / QT_VOTOS);
        return percentualBranco;
    }
    
    private static double percentualDeVotosNulo(int contNulos, int QT_VOTOS){
        //percentual de votos
        double percentualNulo = (contNulos/ QT_VOTOS);
        return percentualNulo;
    }
    
    private static double calculoTotalDeVotos(int contPTGov, int contRepublicanosGov, int contBranco, int contNulos){
        double soma = (contPTGov + contRepublicanosGov + contBranco + contNulos);
        return soma;
    }
}
