package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.Utils.JPAUtils;
import com.latam.alura.tienda.dao.CategoriaDAO;
import com.latam.alura.tienda.dao.ProductoDAO;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;

public class RegistroDeProducto {
	public static void main(String[] args) {
		registrarProducto();
		EntityManager em = JPAUtils.getEntityManager();
		
		ProductoDAO productoDao = new ProductoDAO(em);
		Producto producto = ProductoDAO.consultaPorId(em, 1l);
		System.out.println(producto.getNombre());
		
		BigDecimal precio = productoDao.consultarPrecioPorNombreDeProducto("Samsung");
		System.out.println(precio);
	}

	private static void registrarProducto() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria cocina = new Categoria("ARTICULOSCOCINA");
		Categoria carros = new Categoria("AUTOS");
		Producto celular1 = new Producto("Samsung", "Telefono usado", new BigDecimal(2000), celulares);
		Producto celular2 = new Producto("Xiaomi", "Telefono usado", new BigDecimal(1000), celulares);
		Producto celular3 = new Producto("Lg", "Telefono nuevo", new BigDecimal(3000), celulares);
		Producto celular4 = new Producto("LgUsado", "Telefono usado", new BigDecimal(400), celulares);
		Producto lamborgini = new Producto("Lamborgini", "Auto usado", new BigDecimal(5000000), carros);
		Producto ferrary = new Producto("Murcielago", "Auto usado", new BigDecimal(100000000), carros);
		Producto olla = new Producto("olla Simple", "olla nueva simple", new BigDecimal(700), cocina);
		Producto cuchillo = new Producto("cuchillo Simple", "cuchillo nuevo simple", new BigDecimal(300), cocina);
		
		EntityManager em = JPAUtils.getEntityManager();
		
		ProductoDAO productoDao = new ProductoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		
		//apertura de transacciones. 		
		em.getTransaction().begin();
		//ejecución de transacciones
		productoDao.guardar(celular1);
		productoDao.guardar(celular2);
		productoDao.guardar(celular3);
		productoDao.guardar(celular4);
		productoDao.guardar(lamborgini);
		productoDao.guardar(ferrary);
		productoDao.guardar(cuchillo);
		productoDao.guardar(olla);
		categoriaDao.guardar(celulares);
		categoriaDao.guardar(cocina);
		categoriaDao.guardar(carros);
		//Guardado de la transaccion
		em.getTransaction().commit();
		//cierre de la transacción
		em.close();
	}
}
