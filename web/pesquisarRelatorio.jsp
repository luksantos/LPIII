<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SGMC</title>
    <script language="JavaScript">
        function popUp(theUrl, winName, features) {
            window.open(theUrl, winName, features);
        }
    </script>
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <ul class="main-nav">
        <h2>SGMC - Pesquisa de Relatórios</h2>
    </ul>
    <ul class="main-table">
        <li>
            <a href="javascript:void(0)"
                onClick="popUp('manterRelatorioCliente.jsp', 'RelClientes', 'scrollbars=no, width=500, height=400, left=0, top=0')">Clientes</a>
        </li>
        <li>
            <a href="javascript:void(0)"
                onClick="popUp('RelProdutosController?acao=filtrar', 'RelProdutos', 'scrollbars=no, width=600, height=300, left=0, top=0')">Produtos por Categoria</a>
        </li>
    </ul>
</body>

</html>