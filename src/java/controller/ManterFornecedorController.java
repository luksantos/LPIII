/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EnderecoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Endereco;
import model.Fornecedor;

public class ManterFornecedorController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String acao = request.getParameter("acao");
        if(acao.equals("confirmarOperacao")){
            confirmarOperacao(request, response);
        }else{
            if(acao.equals("prepararOperacao")){
                prepararOperacao(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void prepararOperacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, ClassNotFoundException, SQLException {
        try{
            String operacao = request.getParameter("operacao");
            request.setAttribute("operacao", operacao);
            //request.setAttribute("enderecos", Endereco.obterEnderecos());
            request.setAttribute("enderecos", EnderecoDAO.getInstancia().findAllEnderecos());
            if(!operacao.equals("Incluir")){
                int idFornecedor = Integer.parseInt(request.getParameter("idFornecedor"));
                Fornecedor fornecedor = Fornecedor.obterFornecedor(idFornecedor);
                request.setAttribute("fornecedor", fornecedor);
            }
            RequestDispatcher view = request.getRequestDispatcher("/manterFornecedor.jsp");
            view.forward(request, response);
        }
        catch(ServletException e){
            throw e;
        }
        catch(IOException e){
            throw new ServletException(e);
        }
         catch(SQLException e){
            throw new ServletException(e);
        }
        catch(ClassNotFoundException e){
            throw new ServletException(e);
        }
    }
    
    public void confirmarOperacao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException{
        String operacao = request.getParameter("operacao");
        int idFornecedor = Integer.parseInt(request.getParameter("numIdFornecedor"));
        String nomeFantasia = request.getParameter("txtNomeFantasia");
        String cnpj = request.getParameter("numCnpj");
        String nomeRepresentante = request.getParameter("txtNomeRepresentante");
        String email = request.getParameter("txtEmail");
        String telefone = request.getParameter("numTelefone");
        int numero = Integer.parseInt(request.getParameter("numEndereco"));
        String complemento = request.getParameter("txtComplemento");
        int idEndereco = operacao.equals("Excluir") ? 0 : Integer.parseInt(request.getParameter("optEndereco"));
        
        try{
            Endereco endereco = null;
            if(idEndereco != 0){
                //endereco = Endereco.obterEndereco(idEndereco);
                endereco = EnderecoDAO.getInstancia().findEndereco(idEndereco);
            }
            Fornecedor fornecedor = new Fornecedor(idFornecedor, nomeFantasia, 
                    cnpj, nomeRepresentante, email, telefone, numero, 
                    complemento, endereco);
            if(operacao.equals("Incluir")){
                fornecedor.gravar();
            }
            else{
                if(operacao.equals("Excluir")){
                    fornecedor.excluir();
                }
                if(operacao.equals("Alterar")){
                    fornecedor.alterar();
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaFornecedorController");
            view.forward(request, response);
        } catch (IOException e){
            throw new ServletException(e);
        }
        catch(SQLException e){
            throw new ServletException(e);
        }
        catch(ClassNotFoundException e){
           throw new ServletException(e);
        }
    }
}
