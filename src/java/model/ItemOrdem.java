/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ItensOrdemDAO;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemOrdem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItemOrdem;
    private int quantidade;
    @ManyToOne
    private Produto produto;
    private int idProduto;
    @ManyToOne
    private OrdemServico ordemServico;
    private int idOrdemSrv;

    public ItemOrdem(Integer idItensOrdem, int quantidade, Produto produto,
            OrdemServico ordemServico) {
        this.idItemOrdem = idItensOrdem;
        this.quantidade = quantidade;
        this.produto = produto;
        this.ordemServico = ordemServico;
    }

    public ItemOrdem() {
    }

    public Integer getIdItensOrdem() {
        return idItemOrdem;
    }

    public void setIdItensOrdem(Integer idItemOrdem) {
        this.idItemOrdem = idItemOrdem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() throws SQLException, ClassNotFoundException {
        if ((this.idProduto != 0) && (this.produto == null)) {
            this.produto = Produto.obterProduto(this.idProduto);
        }
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public OrdemServico getOrdemServico() throws SQLException, ClassNotFoundException {
        if ((this.idOrdemSrv != 0) && (this.ordemServico == null)) {
            this.ordemServico = OrdemServico.obterOrdemServico(this.idOrdemSrv);
        }
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Integer getIdOrdemSrv() {
        return idOrdemSrv;
    }

    public void setIdOrdemSrv(int idOrdemSrv) {
        this.idOrdemSrv = idOrdemSrv;
    }

    public static List<ItemOrdem> obterItensOrdem(int idItensOrdem) throws SQLException, ClassNotFoundException {
        return ItensOrdemDAO.getInstancia().findAllItemOrdems();
    }

    public static List<ItemOrdem> obterItensOrdens() throws ClassNotFoundException, SQLException {
        return ItensOrdemDAO.getInstancia().findAllItemOrdems();
    }
    
    public static ItemOrdem obterItemOrdem(Integer idItensOrdem) throws ClassNotFoundException, SQLException {
        return ItensOrdemDAO.findItemOrdem(idItensOrdem);
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        ItensOrdemDAO.getInstancia().save(this);
    }
    
    public void alterar() throws ClassNotFoundException, SQLException{
        ItensOrdemDAO.getInstancia().save(this);
    }
    
    public void excluir() throws ClassNotFoundException, SQLException{
        ItensOrdemDAO.getInstancia().remove(idItemOrdem);
    }
}
