package com.giriarte.trainNetwork.service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.giriarte.trainNetwork.discriminator.Discriminator;
import com.giriarte.trainNetwork.discriminator.MaxDistanceDiscriminator;
import com.giriarte.trainNetwork.model.City;
import com.giriarte.trainNetwork.model.DistanceNotFoundException;
import com.giriarte.trainNetwork.model.Route;

public class TrainNetworkServiceImpl implements TrainNetworkService {

	Set<City> cities = new HashSet<City>();
	
	/* (non-Javadoc)
	 * @see com.giriarte.trainNetwork.service.TrainNetworkService#getDirectDistance(java.lang.String)
	 */
	public Integer getDirectDistance(String routePath) {
		Integer distance = 0;
		int index = 1;
		
		char prevCityId = routePath.charAt(0);
		char actualCityId;
		try {
			for(; index <routePath.length(); index++) {
				actualCityId = routePath.charAt(index);
				City actualCity = searchCity(actualCityId);
				distance += actualCity.getDistanceFrom(searchCity(prevCityId));
				prevCityId = actualCityId;
			}
		} catch (DistanceNotFoundException e) {
			System.err.println("SIN RUTA");
			distance = -1;
		}
		return distance;
	}

	
	/* (non-Javadoc)
	 * @see com.giriarte.trainNetwork.service.TrainNetworkService#getRoutesCount(java.lang.Character, java.lang.Character, com.giriarte.trainNetwork.discriminator.Discriminator)
	 */
	public Integer getRoutesCount(Character sourceCity, Character destCity, Discriminator discriminator) {
		City sourceCityObj = searchCity(sourceCity);
		City destCityObj = searchCity(destCity);
		TreeSet<Route> routes = destCityObj.getRoutes(sourceCityObj, discriminator);
		for(Route route : routes) {
			System.out.println("Ruta: "+route.getRouteIndication()+" . Distancia: "+route.getRouteLength());
		}
		return routes.size();
		
	}

	/* (non-Javadoc)
	 * @see com.giriarte.trainNetwork.service.TrainNetworkService#getMiniumDistance(java.lang.Character, java.lang.Character)
	 */
	public Integer getMiniumDistance(Character sourceCity, Character destCity) {
		City sourceCityObj = searchCity(sourceCity);
		City destCityObj = searchCity(destCity);
		TreeSet<Route> routes = destCityObj.getRoutes(sourceCityObj, new MaxDistanceDiscriminator(30));
		return routes.first().getRouteLength();
	}

	/* (non-Javadoc)
	 * @see com.giriarte.trainNetwork.service.TrainNetworkService#addCity(java.lang.Character)
	 */
	public void addCity(Character cityID) {
		cities.add(new City(cityID));
	}

	/* (non-Javadoc)
	 * @see com.giriarte.trainNetwork.service.TrainNetworkService#addRoute(java.lang.String[])
	 */
	public void addRoute(String... routeDescription) {
		for(String oneRoute : routeDescription) {
			char sourceCityId = oneRoute.charAt(0);
			char destCityId = oneRoute.charAt(1);
			int routeLength = Character.getNumericValue(oneRoute.charAt(2));
			City destCity = searchCity(destCityId);
				
			destCity.getCitiesFrom().put(searchCity(sourceCityId), routeLength);	

		}
	}

	private City searchCity(char cityID) {
		City back = null;
		for(City oneCity : cities) {
			if(oneCity.getName() == cityID) {
				back = oneCity;
			}
		}
		return back;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}


}
