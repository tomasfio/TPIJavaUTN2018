package Main.Negocio;

import Main.Datos.*;
import Main.Entidades.*;

public class VentaLogic {
VentaData venData;
DetalleVentaData detData;
EntregaData entData;
	
	public VentaLogic(){
		venData = new VentaData();
		detData = new DetalleVentaData();
		entData = new EntregaData();
	}
	
	public boolean RegistrarVenta(Venta venta) {
		if(venta.getEntrega() != null && venta.getEntrega().getDireccion().isEmpty()) {
			return false;
		}
		
		Venta idVenta = venData.Insert(venta);
		if( idVenta != null) {
			venta.setIdVenta(idVenta.getIdVenta());
			
			for(DetalleVenta det : venta.getDetallesVentas()) {
				det.setVenta(venta);
				detData.Insert(det);
			}
			
			if(venta.getEntrega() != null) {
				venta.getEntrega().setIdVenta(venta);
				entData.Insert(venta.getEntrega());
			}
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
