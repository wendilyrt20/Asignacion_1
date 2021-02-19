package edu.pucmm.eict;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.get;

public class Main {
    public static void main(String[] args){

        String mensaje = "Hola";
        System.out.println(mensaje);

//Creando la instancia del servidor.
        Javalin app = Javalin.create( config -> {
            //set configs
            config.addStaticFiles("/publico");

        });
        app.start(7000);
/*
Crear una aplicación Web que cumpla los siguientes criterios:
1) Un filtro debe interceptar las peticiones y si el usuario no está logueado debe
 debe mostrar un formulario con los campos de usuario y contraseña.

2) Un proceso para recibir validar el formulario y si cumple con la autentifación
 redireccionar a la ruta (/) mostrando un mensaje de inicio.
3) Incluir de css en la pagina de inicio y formulario.
 */
        app.before("/", ctx -> {
            if (ctx.sessionAttribute("user")== null && ctx.sessionAttribute("pass")==null){
                ctx.redirect("/login.html");
            }
            else ctx.redirect("/autenticado.html");
        });

        app.get("/login",ctx -> {

        });

        app.post("/autentificar", ctx -> {
            //ctx.sessionAttribute("tt","s");
            String usuario = ctx.formParam("user");
            String pass = ctx.formParam("pass");
            ctx.sessionAttribute("user", usuario);
            ctx.sessionAttribute("pass", pass);

            if (usuario!=null && pass!=null) {
                ctx.redirect("/");
            }

        });


    }
}
