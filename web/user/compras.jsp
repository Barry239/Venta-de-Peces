<%@page import="DB.DBAdmin"%>
<%@page import="Classes.Compra"%>
<%@page import="java.util.List"%>
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
            if (ck.length == 2) {
                response.sendRedirect("../admin/index.jsp");
            } else if (ck.length != 3) {
                response.sendRedirect("./index.jsp");
            } else {
                // Obtener id_usuario de cookie
                int id_usuario = 0;
                for (Cookie cke: ck) {
                    if (cke.getName().equals("id_usuario")) {
                        id_usuario = Integer.parseInt(cke.getValue());
                    }
                }
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
                        <a class="nav-link" href="./index.jsp">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./productos.jsp">Productos</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="./compras.jsp">Compras</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Usuarios
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <form method="post" action="Cerrar_Sesion_Usuario">
                                <button type="submit" class="dropdown-item">Cerrar sesión</button>
                            </form>
                        </div>
                    </li>
                </ul>
                <ul class="navbar-nav navbar-right">
                    <li class="nav-item">
                        <a class="nav-link" href="./carrito.jsp"><i class="fa fa-shopping-cart"></i> Tu carrito</a>
                    </li>
                </ul>
                <!-- End of navbar links -->
            </div>
        </nav>
        <!-- End of navbar -->
        
        <!-- Main container -->
        <div class="container-fluid mt-4" id="content">
            <div class="row">
                <div class="col mx-2"><h3 class="text-light">Compras</h3></div>
            </div>
            <!-- Card -->
            <div class="card my-4 animate__animated animate__fadeIn">
                <div class="card-header">
                    <h5 class="card-title">Citas</h5>
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
                                <th scope="col" class="text-center">Recibido</th>
                            </tr>
                        </thead>
                        <tbody>
    <%
                List<Compra> compras = DBAdmin.ObtenerComprasDeUsuario(id_usuario);
                if (compras.size() > 0) {
                    for (Compra compra: compras) {
    %>
                            <tr>
                                <th scope="row"><%= compra.getNombre_producto() %></th>
                                <td class="text-center"><%= compra.getCantidad_compra() %></td>
                                <td class="text-center">$ <%= compra.getPrecio_producto() * compra.getCantidad_compra() %></td>
                                <td class="text-right"><%= compra.getDomicilio_compra() %></td>
                                <td class="text-right"><%= compra.isTerminada_compra() %></td>
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
        <!-- End of main container -->
    <%
            }
        } else {
            response.sendRedirect("../error.jsp");
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
