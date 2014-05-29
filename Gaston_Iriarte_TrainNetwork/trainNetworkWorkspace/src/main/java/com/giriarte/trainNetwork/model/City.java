package com.giriarte.trainNetwork.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import com.giriarte.trainNetwork.discriminator.Discriminator;

public class City {

	private Character name;
	private HashMap<City, Integer> citiesFrom;
	
	public City(Character cityName) {
		this.name = cityName;
		citiesFrom = new HashMap<City, Integer>();
	}
	
	
	/** Realiza la busqueda de las rutas posibles desde una ciudad especifica, teniendo en cuenta el criterio discriminador como corte
	 * @param cityFrom. Ciudad de inicio de las rutas buscadas
	 * @param discriminator. Criterio de corte de busqueda (distancia maxima, cantidad de paradas, etc) 
	 * @return Un Set de rutas , ordenadas por su longitud
	 */
	public TreeSet<Route> getRoutes(City cityFrom, Discriminator discriminator) {
		TreeSet<Route> routes = new TreeSet<Route>(new Comparator<Route>(){
            public int compare(Route a, Route b){
            	int subResult = a.getRouteLength() - b.getRouteLength();
                return (subResult!=0)?subResult:1;
            }
        });
        
		StringBuilder routeStr = new StringBuilder();
		routeStr.append(this.getName());
		Integer distance = 0;
		findChildRoutes(this, cityFrom, routeStr, distance, discriminator, routes);
		return routes;
	}

	
	/** Metodo que aplica recursividad en la busqueda de las rutas posibles
	 * @param destCity. Ciudad intermedia, de paso, o bien ciudad final del recorrido.
	 * @param cityFrom. Ciudad desde donde se inicia la búsqueda.
	 * @param routeStr. Cadena conformada por las ciudades que se están recorriendo.
	 * @param distance. Distancia total del recorrido en la ruta actual.
	 * @param discriminator. Objeto que contiene el criterio de corte.
	 * @param routes. Set de rutas que es completado a lo largo de la recursividad.
	 */
	private void findChildRoutes(City destCity, City cityFrom, StringBuilder routeStr,
			Integer distance, Discriminator discriminator, TreeSet<Route> routes) {
		Set<City> citiesKey = destCity.getCitiesFrom().keySet();
		
		for(City oneCity : citiesKey) {
			Integer routeDistance = new Integer(distance);
			StringBuilder routePart = new StringBuilder();
			routePart.append(oneCity.getName());
			routeDistance+=destCity.getCitiesFrom().get(oneCity);
			routePart.append(routeStr);
			if(cityFrom.getName().equals(oneCity.getName())) {
				discriminator.addDiscrimiatedRoute(routePart, routeDistance, routes);
			}
			if(discriminator.checkDiscRule(routePart, routeDistance)){
				findChildRoutes(oneCity, cityFrom, routePart, routeDistance, discriminator, routes);
			}
			
		}
		
	}

	
	/** 
	 * @param searchCity. Ciudad origen del recorrido.
	 * @return distancia total del recorrido directo entre dos ciudades
	 * @throws DistanceNotFoundException en caso de que ambas ciudades no estén comunicadas.
	 */
	public Integer getDistanceFrom(City searchCity) throws DistanceNotFoundException{
		Integer distance;
		if(citiesFrom.containsKey(searchCity)) {
			distance = citiesFrom.get(searchCity);
		} else throw new DistanceNotFoundException();
		return distance;
	}
	
	public Character getName() {
		return name;
	}

	public void setName(Character name) {
		this.name = name;
	}

	public HashMap<City, Integer> getCitiesFrom() {
		return citiesFrom;
	}

	public void setCitiesFrom(HashMap<City, Integer> citiesFrom) {
		this.citiesFrom = citiesFrom;
	}
	
	public int hashCode() {
        Double rand = Math.random();
        return rand.intValue();
    }

    public boolean equals(Object obj) {
       if (!(obj instanceof City))
            return false;
        if (obj == this)
            return true;
        City cit = (City) obj;
        return(cit.getName().equals(this.name));
        
    }
	
}
