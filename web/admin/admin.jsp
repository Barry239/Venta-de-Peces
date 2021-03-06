<%@page import="Classes.Producto"%>
<%@page import="java.util.List"%>
<%@page import="Classes.Compra"%>
<%@page import="DB.DBAdmin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap 4.5 -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <!-- FontAwesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Animate CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="../css/gradient.css">
        <link rel="stylesheet" href="../css/main.css">
        
        <title>PezKBonis</title>
    </head>
    <body>
    <%
        // Obtener cookies
        Cookie[] ck = request.getCookies();
        System.out.println(ck.length);
        
        if (ck != null) {
            if (ck.length == 3) {
                response.sendRedirect("../user/index.jsp");
            } else if (ck.length == 2) {
    %>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="./index.jsp">PezKBonis</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- Navbar links -->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="./index.jsp">Inicio <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="./admin.jsp">Administación</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Usuarios
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <form action="Cerrar_Sesion_Admin" method="POST">
                                <button type="submit" class="dropdown-item">Cerrar sesión</button>
                            </form>
                        </div>
                    </li>
                </ul>
                <!-- End of navbar links -->
            </div>
        </nav>
        <!-- End of navbar -->
        
        <!-- Main container -->
        <div class="container-fluid mt-4" id="content">
            <div class="row">
                <div class="col mx-2"><h3 class="text-light">Administación</h3></div>
            </div>
            <div class="row">
                <div class="col-md">
                    <!-- Card -->
                    <div class="card my-4 animate__animated animate__bounceInLeft">
                        <div class="card-header">
                            <h5 class="card-title">Compras</h5>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            <table class="table table-hover">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">Nombre</th>
                                        <th scope="col" class="text-center">Cantidad</th>
                                        <th scope="col" class="text-center">Precio total</th>
                                        <th scope="col" class="text-center">Domicilio</th>
                                        <th scope="col" class="text-center">Acción</th>
                                    </tr>
                                </thead>
                                <tbody>
    <%
                List<Compra> compras = DBAdmin.ObtenerTodasLasCompras();
                if (compras.size() > 0) {
                    for (Compra compra: compras) {
    %>
                                    <tr>
                                        <th scope="row"><%= compra.getNombre_producto() %></th>
                                        <td class="text-center"><%= compra.getCantidad_compra() %></td>
                                        <td class="text-center"><%= compra.getPrecio_producto() * compra.getCantidad_compra() %></td>
                                        <td class="text-right"><%= compra.getDomicilio_compra() %></td>
                                        <td class="text-right">
    <%
                        if (!compra.isTerminada_compra()) {
    %>
                                            <form action="Marcar_Recibido" method="POST">
                                                <input type="hidden" name="id_compra" value="<%= compra.getId_compra() %>">
                                                <button type="submit" class="btn btn-primary">Marcar de recibido</button>
                                            </form>
    <%
                        } else {
    %>
                                            Completada
    <%
                        }
    %>
                                        </td>
                                    </tr>
    <%
                    }
                }
    %>
                                </tbody>
                            </table>
                            </div>
                        </div>
                    </div>
                    <!-- End of card -->
                    <!-- Card -->
                    <div class="card my-4 animate__animated animate__bounceInLeft">
                        <div class="card-header">
                            <h5 class="card-title">Productos</h5>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            <table class="table table-hover">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">Nombre</th>
                                        <th scope="col" class="text-center">Precio</th>
                                        <th scope="col" class="text-center">Stock</th>
                                        <th scope="col" class="text-center">Acción</th>
                                    </tr>
                                </thead>
                                <tbody>
    <%
                List<Producto> productos = DBAdmin.ObtenerProductos();
                if (productos.size() > 0) {
                    for (Producto producto: productos) {
    %>
                                    <tr>
                                        <form action="Editar_Producto" method="POST">
                                            <td scope="row"><%= producto.getNombre_producto() %></td>
                                            <td class="text-right">
                                                <div class="input-group mb-3">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">$</span>
                                                    </div>
                                                    <input type="text" class="form-control" name="precio" id="precio" value="<%= producto.getPrecio_producto() %>">
                                                </div>
                                            </td>
                                            <td class="text-right">
                                                <div class="input-group mb-3">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text">Cant.</span>
                                                    </div>
                                                    <input type="text" class="form-control" name="stock" id="stock" value="<%= producto.getStock_producto() %>">
                                                </div>
                                            </td>
                                            <td class="text-right">
                                                <input type="hidden" name="id_producto" value="<%= producto.getId_producto() %>">
                                                <button type="submit" class="btn btn-warning">Editar</button>
                                            </td>
                                        </form>
                                    </tr>
    <%
                    }
                }
    %>
                                </tbody>
                            </table>
                            </div>
                        </div>
                    </div>
                    <!-- End of card -->
                </div>
            </div>
        </div>
        <!-- End of main container -->
<%
            } else {
                response.sendRedirect("../error.jsp");
            }
        }
%>
    </body>
    
    <!-- Bootstrap 4.5 -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <!-- SweetAlert2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <!-- Custom JS -->
    <script src="../js/main.js"></script>
    <script src="../js/validate.js"></script>
</html>
