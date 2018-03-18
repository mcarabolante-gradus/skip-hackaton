package org.mcarabolante.resources;

import org.mcarabolante.domain.cuisine.Cuisine;
import org.mcarabolante.domain.cuisine.CuisineDAO;
import org.mcarabolante.domain.store.Store;
import org.mcarabolante.domain.store.StoreDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1/Cousine")
@Produces(MediaType.APPLICATION_JSON)
public class CuisineResource {

    private final CuisineDAO cuisineDAO;
    private final StoreDAO storeDAO;

    @Inject
    public CuisineResource(CuisineDAO cuisineDAO, StoreDAO storeDAO) {
        this.cuisineDAO = cuisineDAO;
        this.storeDAO = storeDAO;
    }

    @GET
    public List<Cuisine> list(){
        return cuisineDAO.list();
    }

    @GET
    @Path("search/{searchText}")
    public List<Cuisine> searchByName(@PathParam("searchText") String searchText){
        return cuisineDAO.listLikeName("%" + searchText + "%");
    }

    @GET
    @Path("{cousineId}/stores")
    public List<Store> storeByCuisine(@PathParam("cousineId") Long cuisine){
        return storeDAO.listByCuisine(cuisine);
    }


}
