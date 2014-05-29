package com.giriarte.trainNetwork.service;

import com.giriarte.trainNetwork.discriminator.Discriminator;

public interface TrainNetworkService {

	
	/**
	 * @param routePath. Cadena que representa una ruta directa entre dos o mas ciudades.
	 * @return la longitud de dicha ruta, o -1 en caso de que la ruta no exista.
	 */
	Integer getDirectDistance(String routePath);
	
	
	/**
	 * @param sourceCity. Ciudad de origen de las rutas buscadas.
	 * @param destCity. Ciudad destino de las rutas buscadas.
	 * @param discriminator. Criterio de búsqueda de las rutas.
	 * @return Cantidad de rutas entre dos ciudades determinadas, según el criterio aplicado en objeto discriminator
	 */
	Integer getRoutesCount(Character sourceCity, Character destCity, Discriminator discriminator);
	
	
	/**
	 * @param sourceCity. Ciudad de origen
	 * @param destCity. Ciudad destino
	 * @return Distancia mínima entre dos ciudades determinadas.
	 */
	Integer getMiniumDistance(Character sourceCity, Character destCity);
	
	
	/**
	 * @param cityID. Ciudad a agregar en el listado de la red de transporte ferroviario.
	 */
	void addCity(Character cityID);
	
	
	/**
	 * @param routeDescription. Descripción de la ruta entre dos ciudades determinadas, para agegar en la red ferroviaria.
	 */
	void addRoute(String... routeDescription);
	
}
