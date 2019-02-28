package Main.Negocio;

import java.util.ArrayList;
import java.util.Date;

import Main.Datos.*;
import Main.Entidades.*;
import Main.Util.Email;
import Main.Util.Funciones;
import Main.Util.LogException;

public class VentaLogic {
VentaData venData;
DetalleVentaData detData;
EntregaData entData;
	
	public VentaLogic(){
		venData = new VentaData();
		detData = new DetalleVentaData();
		entData = new EntregaData();
	}
	
	public ArrayList<Venta> GetAll(){
		return venData.GetAll();
	}
	
	public ArrayList<Venta> GetVentaWithEntregas(){
		return venData.GetVentaWithEntregas();
	}
	
	public boolean RegistrarVenta(Venta venta) {
		if(venta.getEntrega() != null && venta.getEntrega().getDireccion().isEmpty()) {
			return false;
		}
		
		venta.setFecha(new Date());
		
		Venta idVenta = venData.Insert(venta);
		if( idVenta != null) {
			venta.setIdVenta(idVenta.getIdVenta());
			
			for(DetalleVenta det : venta.getDetallesVentas()) {
				det.setVenta(venta);
				detData.Insert(det);
			}
			
			if(venta.getEntrega() != null) {
				venta.getEntrega().setVenta(venta);
				entData.Insert(venta.getEntrega());
			}
			
			try {
				Email email = Email.getInstance();
				email.send(venta.getUsuario().getEmail(), "Venta Nro." + venta.getIdVenta(), CuerpoMensaje(venta));
			}
			//Se devuelve true porque aunque fallo el envio, se registro igual la venta
			catch(Exception ex) {
				return true;
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	private String CuerpoMensaje(Venta ven) {
		String mensaje = "Estimado Usuario/a " + ven.getUsuario().getNombre() + " " + ven.getUsuario().getApellido();
		mensaje += "\n    Le enviamos este mensaje para confirmar que se ha registrado exitosamente su compra,";
		mensaje +="\nla misma tiene como identificacion el id " + ven.getIdVenta();
		mensaje +="\nEl importe total de su compra es igual a " + ven.getImporte();
		mensaje +="\n\nLos detalles de su compra : ";
		mensaje +="\nLibro" + Funciones.repeat(" ",65) +"Cantidad" + Funciones.repeat(" ",62) + "Precio p/u" + Funciones.repeat(" ",60) + "Subtotal";
		for(DetalleVenta det : ven.getDetallesVentas()) {
			mensaje +="\n" + det.getLibro().getTitulo() + Funciones.repeat(" ",70 - det.getLibro().getTitulo().length()) +
		det.getCantidad() + Funciones.repeat(" ",70 - Integer.toString(det.getCantidad()).length()) + 
		(det.getSubTotal()/det.getCantidad()) + Funciones.repeat(" ",70 - Double.toString((det.getSubTotal()/det.getCantidad())).length()) +
		det.getSubTotal();
		}
		if(ven.getEntrega() != null) {
			mensaje += "\n\nSe enviara los libros a la siguiente direccion " + ven.getEntrega().getDireccion();
			mensaje += "\nAntes del envio se le enviara un mail informandole que el pedido seria enviado ese dia.";
		}
		else {
			mensaje += "\n\nPodra buscar su compra cualquier dia en el horario que esta abierto el local, Lunes a Viernes de 8hs a 12hs y 16hs a 20hs";
		}
		mensaje += "\n\n\nMuchas gracias por su compra,\nSaludos.";
		return mensaje;
	}
	
	public boolean RegistrarEntregas(ArrayList<Venta> entregas) {
		boolean resultado = true;
		for(Venta ent : entregas) {
			if(!entData.Update(ent.getEntrega())) {
				resultado = false;
			}
			else {
				try {
					Email email = Email.getInstance();
					email.send(ent.getUsuario().getEmail(), "Venta Nro." + ent.getIdVenta() + " Envio a domicilio",
							"Estimado Usuario/a " + ent.getUsuario().getNombre() + " " + ent.getUsuario().getApellido() + "\n" +
							"Le confirmamos la entrega de compra con el envio a la direccion " + ent.getEntrega().getDireccion() + ".\n" +
							"Si llega a haber un error con su pedido les pedimos que se contacte con nosotros atraves de la pagina.\n\n\n" +
							"Muchas gracias por su compra,\nSaludos.");
				}
				catch(Exception ex) {
					
					ex.printStackTrace();
				}
			}
		}
		
		return resultado;
	}
	
	
	
}
