package Programa;

import javax.swing.*;
import java.util.ArrayList;



public class AgenciaBancaria {
    //static Scanner input = new Scanner(System.in); n será mais usado
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<Conta>();
        operacoes();
    }

    public static void operacoes() {

        int operacao = Integer.parseInt(JOptionPane.showInputDialog("...Selecione uma operação ..." +
                "\n|   (1) Criar conta        " + "|   (2) Depósito  " + "|   (3) Saque   " +
                "\n|   (4) Transferência   " + "|   (5) Listar       " + "|   (6) Excluir conta" +
                "\n|   (7) Sair   "
        ));


        //int operacao = input.nextInt(); n será mais usado

        switch (operacao) {
            case 1:
                criarConta();
                break;
            case 2:
                depositar();
                break;
            case 3:
                sacar();
                break;
            case 4:
                transferir();
                break;
            case 5:
                listarContas();
                break;
            case 6:
                excluiConta();
                break;
            case 7:
                JOptionPane.showMessageDialog(null, "A payWise agradece por sua preferencia!");
                System.exit(0);
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                operacoes();
                break;
        }
    }


    public static void criarConta() {
        Pessoa pessoa = new Pessoa();


        String nome = JOptionPane.showInputDialog("Nome: ");
        if (!nome.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "O nome deve conter apenas letras.");
            criarConta();
            return;
        }
        pessoa.setNome(nome);

        String cpf = JOptionPane.showInputDialog("CPF: ");
        if(cpf.length() != 11){
            JOptionPane.showMessageDialog(null, "CPF inválido. CPF inválido, tente novamente.");
            criarConta(); // Chama novamente o método para obter um CPF válido
            return; // Retorna para evitar a criação da conta com um CPF inválido
        }

        pessoa.setCpf(cpf);


        pessoa.setEmail(JOptionPane.showInputDialog("Email: "));

        Conta conta = new Conta(pessoa);
        contasBancarias.add(conta);

        JOptionPane.showMessageDialog(null, "### CONTA CRIADA COM SUCESSO! ####");
        operacoes();
    }


    private static Conta encontrarConta(int numeroConta) {
        Conta conta = null;
        if(contasBancarias.size() > 0) {
            for(Conta c: contasBancarias) {
                if(c.getNumeroConta() == numeroConta) {
                    conta = c;
                }
            }
        }
        return conta;
    }

    public static void depositar(){
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da conta para depósito: "));

        Conta conta = encontrarConta(numeroConta);

        if(conta != null) {
            Double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o valor que deseja depositar: "));
            conta.depositar(valorDeposito);
            JOptionPane.showMessageDialog(null, "Seu depoisto foi efetuado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Conta não encontrada!");
        }
        operacoes();

    }

    public static void sacar() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Número da conta: "));

        Conta conta = encontrarConta(numeroConta);

        if(conta != null) {
            Double valorSaque = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o valor para sacar: "));
            conta.sacar(valorSaque);
            JOptionPane.showMessageDialog(null, "Seu saque foi efetuado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Conta não encontrada! ");
        }
        operacoes();
    }

    public static void transferir() {
        int numeroContaRemetente = Integer.parseInt(JOptionPane.showInputDialog(null, "Número da conta do remetente: "));
        Conta contaRemetente = encontrarConta(numeroContaRemetente);


        if(contaRemetente != null) {
            int numeroContaDestinatario = Integer.parseInt(JOptionPane.showInputDialog(null, "Número da conta do destinatário: "));
            Conta contaDestinario = encontrarConta(numeroContaDestinatario);

            if(contaDestinario != null) {
                Double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor para transferência: "));
                contaRemetente.transferir(contaDestinario, valor);
            } else {
                JOptionPane.showMessageDialog(null,"Conta não encontrada!");
            }
        }
        operacoes();
    }

    public static void listarContas() {
        if(contasBancarias.size() > 0) {
            for(Conta conta: contasBancarias) {
                JOptionPane.showMessageDialog(null, conta);
            }
        }else {
            JOptionPane.showMessageDialog(null, "Não há contas cadastradas!");
        }

        operacoes();
    }

    public static void excluiConta() {
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da conta para excluir: "));

        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            contasBancarias.remove(conta);
            JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Conta não encontrada!");
        }

        operacoes();
    }
}
