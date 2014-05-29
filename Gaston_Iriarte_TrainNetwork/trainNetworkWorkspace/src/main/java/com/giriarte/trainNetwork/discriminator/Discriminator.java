package com.giriarte.trainNetwork.discriminator;

import java.util.TreeSet;

import com.giriarte.trainNetwork.model.Route;

public abstract class Discriminator {

	private Integer value;
	
	/** Constructor de la clase Discriminator. 
	 * @param value. valor a tener en cuenta en las comparaciones.
	 */
	public Discriminator(Integer value){
		this.value = value;
	}
	
	
	/** Agrega una ruta al Set de rutas
	 * @param routeStr. Cadena que representa las ciudades por las que pasa la ruta.
	 * @param distance. Distancia total de la ruta.
	 * @param routes. Set de rutas.
	 */
	public void addRoute(StringBuilder routeStr, Integer distance,
			TreeSet<Route> routes) {
		Route route = new Route();
		route.setRouteLength(distance);
		route.setRouteIndication(routeStr.toString());
		routes.add(route);
	}
	
	
	/** Aplica el criterio para agregar una ruta o no , y agrega la ruta
	 * @param routeStr. Cadena que representa la ruta
	 * @param distance. Distancia total de la ruta.
	 * @param routes. Set de rutas
	 * @return Si se agregó la ruta o no al set de rutas.
	 */
	public abstract Boolean addDiscrimiatedRoute(StringBuilder routeStr, Integer distance,
			TreeSet<Route> routes);
	
	
	/** Chequea si una ruta cumple las condiciones o no para ser agegada al recorrido. Aplicación del Strategy Pattern
	 * @param routeStr. Cadena que representa la ruta.
	 * @param distance. distancia total de la ruta.
	 * @return Si la ruta puede agegarse o no al recorrido.
	 */
	public abstract Boolean checkDiscRule(StringBuilder routeStr, Integer distance);

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
}
