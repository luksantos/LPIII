<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SGMC - Pesquisa de Categorias</title>
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <ul class="main-nav">
        <h2>SGMC - Pesquisa de Categorias</h2>
    </ul>
    <table border=1 class="table">
        <tr>
            <th>Descrição:</th>
            <th colspan="2">Ação</th>

        </tr>
        <c:forEach items="${categorias}" var="categoria">
            <tr>
                <td>
                    <c:out value="${categoria.descricao}" />
                </td>
                <td><a href="ManterCategoriaController?acao=prepararOperacao&operacao=Excluir&idCategoria=<c:out value="${categoria.idCategoria}" />">Excluir</a></td>
                <td><a href="ManterCategoriaController?acao=prepararOperacao&operacao=Alterar&idCategoria=<c:out value="${categoria.idCategoria}" />">Alterar</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form class="table-input" action="ManterCategoriaController?acao=prepararOperacao&operacao=Incluir" method="post">
        <table>
            <tr>
                <td><input type="submit" name="btnIncluir" value="Incluir"></td>
            </tr>
        </table>
    </form>
    <table class="table-input">
        <tr>
            <td><input type="submit" name="btnVoltar" value="Voltar"
                    onclick="window.location.href='http://localhost:8080/SGMC/'"></td>
        </tr>
    </table>
</body>

</html>