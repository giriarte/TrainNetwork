package com.giriarte.trainNetwork.discriminator;

import java.util.TreeSet;

import com.giriarte.trainNetwork.model.Route;

public class MaxStopDiscriminator extends Discriminator {
	
	/** Filtra las rutas únicamente si la cantidad de paradas es menor a un valor dado
	 * @param value. La cantidad máxima de paradas que deben contener las rutas
	 */
	public MaxStopDiscriminator(Integer value) {
		super(value);
	}

	/* (non-Javadoc)
	 * @see com.giriarte.trainNetwork.discriminator.Discriminator#addDiscrimiatedRoute(java.lang.StringBuilder, java.lang.Integer, java.util.TreeSet)
	 */
	@Override
	public Boolean addDiscrimiatedRoute(StringBuilder routeStr, Integer distance,
			TreeSet<Route> routes) {
		if(checkDiscRule(routeStr, distance)) {
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
		if(routeStr.toString().length()<=(getValue()+1)) {
			return true;
		} else {
			return false;
		}
	}

}
