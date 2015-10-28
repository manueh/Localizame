/*
 * Estructura principal del proyecto de la asignatura ARC
 *      Servidor encargado de recojer y devolver la posición de todos aquellos 
 * clienteS pertenecientes al mismo grupo
 */
package localizame;

import Controller.Controller;
import View.*;

public class Main {
    public static void main(String[] args) {
        PrincipalWindow vista = new PrincipalWindow();
        Controller controller = new Controller(vista);
    }
}
