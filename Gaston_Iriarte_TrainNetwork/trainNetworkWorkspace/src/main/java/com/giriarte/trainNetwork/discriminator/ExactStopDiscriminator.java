package com.giriarte.trainNetwork.discriminator;

import java.util.TreeSet;

import com.giriarte.trainNetwork.model.Route;

public class ExactStopDiscriminator extends Discriminator{

	/** Filtra las rutas únicamente si la longitud de las mismas es igual a un valor dado
	 * @param value. La longitud que deben contener las rutas
	 */
	public ExactStopDiscriminator(Integer value) {
		super(value);
	}

	/* (non-Javadoc)
	 * @see com.giriarte.trainNetwork.discriminator.Discriminator#addDiscrimiatedRoute(java.lang.StringBuilder, java.lang.Integer, java.util.TreeSet)
	 */
	@Override
	public Boolean addDiscrimiatedRoute(StringBuilder routeStr,
			Integer distance, TreeSet<Route> routes) {
		Boolean back = true;
		if(routeStr.toString().length()==(getValue()+1)) {
			addRoute(routeStr, distance, routes);
		} else if(routeStr.toString().length()>(getValue()+1)){
			return false;
		}
		return back;
	}

	/* (non-Javadoc)
	 * @see com.giriarte.trainNetwork.discriminator.Discriminator#checkDiscRule(java.lang.StringBuilder, java.lang.Integer)
	 */
	@Override
	public Boolean checkDiscRule(StringBuilder routeStr, Integer distance) {
		Boolean back = true;
		if(routeStr.toString().length()==(getValue()+1)) {
			back = true;
		} else if(routeStr.toString().length()>(getValue()+1)){
			return false;
		}
		return back;
	}

}
