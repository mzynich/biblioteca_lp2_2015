/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author mzynich
 */
public class Emprestimo {

    private int id;
    private Cliente cliente;
    private ItemLivro itemLivro;
    private DateTime dataEmprestimo, dataDevolucao, devolucaoEfetiva;
    private int diasAtraso;
    private boolean ativo;
    
    public Emprestimo(Cliente cliente, ItemLivro itemLivro) {
        this.cliente = cliente;
        this.itemLivro = itemLivro;
        this.dataEmprestimo = new DateTime();
        this.dataDevolucao = calculaDataDevolucao();
        this.devolucaoEfetiva = null;
        this.diasAtraso = 0;
        this.ativo = true;
    }
    
    public Emprestimo(int id,Cliente cliente, ItemLivro itemLivro){
        this.id = id;
        this.cliente = cliente;
        this.itemLivro = itemLivro;
        this.dataEmprestimo = new DateTime();
        this.dataDevolucao = calculaDataDevolucao();
        this.devolucaoEfetiva = null;
        this.diasAtraso = 0;
        this.ativo = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public ItemLivro getItemLivro() {
        return itemLivro;
    }

    public DateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public DateTime getDataDevolucao() {
        return dataDevolucao;
    }

    private DateTime calculaDataDevolucao() {
        return dataEmprestimo.plusDays(7);
    }

    public DateTime getDevolucaoEfetiva() {
        return devolucaoEfetiva;
    }

    public void setDevolucaoEfetiva(DateTime devolucaoEfetiva) {
        this.devolucaoEfetiva = devolucaoEfetiva;
        calculaDiasAtraso();
    }

    public int getDiasAtraso() {
        return diasAtraso;
    }

    //Calcula os dias de atraso, utilizando a diferença entre os dias de devolução efetiva e a devolução planejada. O método "daysBetween" leva em conta
    //o horário, por isso é necessário o método "withTimeAtStartOfDay()", para descartar estes campos
    private void calculaDiasAtraso() {
        int i = Days.daysBetween(dataDevolucao.withTimeAtStartOfDay(), devolucaoEfetiva.withTimeAtStartOfDay()).getDays();
        if (i <= 0) {
            this.diasAtraso = 0;
        } else {
            this.diasAtraso = i;
        }
    }
}
