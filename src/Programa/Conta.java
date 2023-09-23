package Programa;

import utilitarios.Utils;

import javax.swing.*;

public class Conta {
                                                                   
    private static int accountCounter = 1;
    /*Criar uma pessoa do tipo Pessoa. Como estou no mesmo pakote, não preciso importar.*/
    private int numeroConta;
    private Pessoa pessoa;
    private Double saldo = 0.0;

    public Conta(Pessoa pessoa) {
        this.numeroConta = Conta.accountCounter;
        this.pessoa = pessoa;
        Conta.accountCounter += 1;
    }

    public int getNumeroConta() {
        return numeroConta;
    }
                                   
    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String toString() {
        return
            "\nNumero da conta: " + this.getNumeroConta() +
            "\nNome: " + this.pessoa.getNome() +
            "\nCPF: " + this.pessoa.getCpf() +
            "\nEmail: " + this.pessoa.getEmail() +
            "\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
            "\n";
    }

    /*Método de depósito*/
    public void depositar(Double valor) {
        if(valor > 0) {
           setSaldo(getSaldo() + valor);
            JOptionPane.showMessageDialog(null, "Seu depoisto foi efetuado com sucesso!");
           } else {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar o depósito!");
           }
    }

    /*Método de saque*/
    public void sacar(Double valor) {
        if(valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            JOptionPane.showMessageDialog(null, "Saque efetuado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi realizar o saque!");
        }
    }

    /*Método de transferência*/
    public void transferir(Conta contaParaDeposito, Double valor) {
        if(valor > 0 && this.getSaldo() >= valor){
            setSaldo(getSaldo() - valor);

            contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;
            JOptionPane.showMessageDialog(null, "Transferência feita com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a transeferência!");
        }
    }
}