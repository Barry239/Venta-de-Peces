<%@page import="Classes.Carrito"%>
<%@page import="DB.DBAdmin"%>
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
                    <li class="nav-item">
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
                    <li class="nav-item active">
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
                <div class="col mx-2"><h3 class="text-light">Tu carrito</h3></div>
            </div>
            <!-- Card -->
            <div class="card my-4 animate__animated animate__fadeInDown">
                <div class="card-header">
                    <h5 class="card-title">Artículos</h5>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                    <!-- Table -->
                    <table class="table table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Nombre</th>
                                <th scope="col" class="text-center">Cantidad</th>
                                <th scope="col" class="text-center">Costo</th>
                                <th scope="col" class="text-center">Acción</th>
                            </tr>
                        </thead>
                        <tbody>
    <%
                List<Carrito> carrito = DBAdmin.ObtenerCarrito(id_usuario);
                int cantidad_total = 0;
                float precio_total = 0f;
                if (carrito.size() > 0) {
                    for (Carrito item: carrito) {
                        cantidad_total += item.getCantidad_carrito();
                        precio_total += item.getPrecio_producto() * item.getCantidad_carrito();
    %>
                            <tr>
                                <th scope="row"><%= item.getNombre_producto() %></th>
                                <td class="text-right"><%= item.getCantidad_carrito() %></td>
                                <td class="text-right">$ <%= item.getPrecio_producto() %></td>
                                <td class="text-center">
                                    <form action="DeleteFromBag" method="POST">
                                        <input type="hidden" name="id_carrito" value="<%= item.getId_carrito() %>">
                                        <button type="submit" class="btn btn-danger">Quitar del carrito</button>
                                    </form>
                                </td>
                            </tr>
    <%
                    }
    %>
                            <tr>
                                <th scope="row">Total</th>
                                <td class="text-right"><%= cantidad_total %></td>
                                <td class="text-right">$ <%= precio_total %></td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#buy">Comprar</button>
                                </td>
                            </tr>
    <%
                }
    %>
                        </tbody>
                    </table>
                    <!-- End of table -->
                    </div>
                </div>
            </div>
            <!-- End of card -->
            <!-- Modal -->
            <div class="modal fade" id="buy" tabindex="-1" role="dialog" aria-labelledby="buyLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <form action="Comprar_Carrito" method="POST">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="buyLabel">Confirmación de compra</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="hour" class="col-form-label">Domicilio:</label>
                                    <input type="text" class="form-control" id="domicilio" name="domicilio">
                                </div>
                                <p>Elija su forma de pago: </p>
                                <div class="form-row mb-2">
                                    <div class="col">
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="pago" id="paypal" value="1" checked onclick="enable_paypal();">
                                            <label class="form-check-label" for="paypal">
                                                PayPal
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="pago" id="card" value="2" onclick="enable_card();">
                                            <label class="form-check-label" for="card">
                                                Tarjeta
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <!-- Paypal inputs -->
                                <div id="paypal_inputs">
                                    <div class="form-group">
                                        <input type="email" class="form-control" id="email" placeholder="Email">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" id="password" placeholder="Contraseña">
                                    </div>
                                </div>
                                <!-- End of paypal inputs -->
                                <!-- Credit card inputs -->
                                <div id="credit_card_inputs" style="display: none;">
                                    <div class="form-row">
                                        <div class="col-8">
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="number_card" placeholder="Número de tarjeta">
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="form-group">
                                                <input type="text" class="form-control" id="cvc" placeholder="CVC">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="exp_date" placeholder="Fecha de expiración">
                                    </div>
                                </div>
                                <!-- End of credit card inputs -->
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-primary">Confirmar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- End of modal -->
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