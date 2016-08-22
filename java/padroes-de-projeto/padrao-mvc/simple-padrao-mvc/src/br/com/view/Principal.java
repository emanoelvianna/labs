package br.com.view;

import br.com.controller.ControllerPrincipal;

public class Principal {
	public static void main(String[] args) {
		 FramePrincipal framePrincipal = new FramePrincipal();
	        new ControllerPrincipal(framePrincipal);
	        framePrincipal.setVisible(true);
    }

}
