package com.giriarte.trainNetwork.discriminator;

import java.util.TreeSet;

import com.giriarte.trainNetwork.model.Route;

public class MaxDistanceDiscriminator extends Discriminator{

	/** Filtra las rutas únicamente si la longitud de las mismas es menor a un valor dado
	 * @param value. La longitud máxima que deben contener las rutas
	 */
	public MaxDistanceDiscriminator(Integer value) {
		super(value);
	}

	/* (non-Javadoc)
	 * @see com.giriarte.trainNetwork.discriminator.Discriminator#addDiscrimiatedRoute(java.lang.StringBuilder, java.lang.Integer, java.util.TreeSet)
	 */
	@Override
	public Boolean addDiscrimiatedRoute(StringBuilder routeStr, Integer distance,
			TreeSet<Route> routes) {
		if(distance<getValue()) {
			addRoute(routeStr, distance, routes);
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.giriarte.trainNetwork.discriminator.Discriminator#checkDiscRule(java.lang.StringBuilder, java.lang.Integer)
	 */
	@Override
	public Boolean checkDiscRule(StringBuilder routeStr, Integer distance) {
		if(distance<getValue()) {
			return true;
		} else {
			return false;
		}
	}
	
}
