package rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import annotations.Secured;
import dao.*;
import entities_info.*;
import model.*;

@Path("/house")
public class HouseEndPoint {
	@POST
	@Secured
	@Consumes({ "application/json" })
	@Produces({"text/plain"})
	public int insert(final HouseInfo houseinfo) {
		UserDAO userdao = new UserDAOImpl();
		int userid = userdao.get_user_id(houseinfo.getOwner());
		model.User user = userdao.get_user(houseinfo.getOwner());
		
		HouseDAO dao = new HouseDAOImpl();
		
		House house = new House();
		house.setUser(user);
		
		HousePK id = new HousePK();
		id.setHost_id(userid);
		
		house.setId(id);
		
		house.setName(houseinfo.getHousename());
		house.setSquare_meters(houseinfo.getSquare_meters());
		house.setCountry(houseinfo.getCountry());
		house.setCity(houseinfo.getCity());
		house.setAddress(houseinfo.getAddress());
		house.setIsPrivate(houseinfo.getIsPrivate());
		house.setPriceperp(houseinfo.getPriceperp());
		house.setMaxnumofP(houseinfo.getMaxnumofp());
		house.setFloor(houseinfo.getFloor());
		house.setNumberBeds(houseinfo.getNumberBeds());
		house.setNumberBaths(houseinfo.getNumberBaths());
		house.setNumberBedrooms(houseinfo.getNumberBedrooms());
		house.setIsRoom(houseinfo.getIsRoom());
		house.setWiFi(houseinfo.getWiFi());
		house.setHeating(houseinfo.getHeating());
		house.setAc(houseinfo.getAc());
		house.setElevator(houseinfo.getElevator());
		house.setKitchen(houseinfo.getKitchen());
		house.setTv(houseinfo.getTv());
		house.setParking(houseinfo.getParking());
		house.setLivingRoom(houseinfo.getLivingRoom());
		house.setSummary(houseinfo.getSummary());
		house.setInformation(houseinfo.getInformation());
		house.setRentingRules(houseinfo.getRentingRules());
		house.setTransitInfo(houseinfo.getTransitInfo());
		house.setCoord_x(houseinfo.getCoord_x());
		house.setCoord_y(houseinfo.getCoord_y());
		house.setStartAvailability(houseinfo.getDate_from());
		house.setEndAvailability(houseinfo.getDate_to());
		house.setPrice(houseinfo.getPrice());
		
        dao.create(house);
        
        List<House> houses = dao.list();
        
        House lasthouse = null;
        
		for (House h : houses) {
			lasthouse = h;
		}
		
		int idlast = lasthouse.getId().getIdHouse();
		return idlast;
	}
	
	@GET
	@Path("/{id}")
	@Produces({ "application/json" })
	public Response example(@PathParam("id") int id) {
		HouseDAO housedao = new HouseDAOImpl();
		House house = housedao.find_by_id(id);
		HouseInfo houseinfo= new HouseInfo();
		houseinfo.setOwner(house.getUser().getUsername());
		houseinfo.setIdHouse(house.getId().getIdHouse());
		houseinfo.setHousename(house.getName());
		houseinfo.setSquare_meters(house.getSquare_meters());
		houseinfo.setCountry(house.getCountry());
		houseinfo.setCity(house.getCity());
		houseinfo.setAddress(house.getAddress());
		houseinfo.setIsPrivate(house.getIsPrivate());
		houseinfo.setPriceperp(house.getPriceperp());
		houseinfo.setMaxnumofP(house.getMaxnumofp());
		houseinfo.setFloor(house.getFloor());
		houseinfo.setNumberBeds(house.getNumberBeds());
		houseinfo.setNumberBaths(house.getNumberBaths());
		houseinfo.setNumberBedrooms(house.getNumberBedrooms());
		houseinfo.setIsRoom(house.getIsRoom());
		houseinfo.setWiFi(house.getWiFi());
		houseinfo.setHeating(house.getHeating());
		houseinfo.setAc(house.getAc());
		houseinfo.setElevator(house.getElevator());
		houseinfo.setKitchen(house.getKitchen());
		houseinfo.setTv(house.getTv());
		houseinfo.setParking(house.getParking());
		houseinfo.setLivingRoom(house.getLivingRoom());
		houseinfo.setSummary(house.getSummary());
		houseinfo.setInformation(house.getInformation());
		houseinfo.setRentingRules(house.getRentingRules());
		houseinfo.setTransitInfo(house.getTransitInfo());
		houseinfo.setCoord_x(house.getCoord_x());
		houseinfo.setCoord_y(house.getCoord_y());
		houseinfo.setPrice(house.getPrice());
		houseinfo.setDate_from(house.getStartAvailability());
		houseinfo.setDate_to(house.getEndAvailability());
		List<ReviewInfo> reviewsinfo1 = new ArrayList<ReviewInfo>();
		List<ReviewInfo> reviewsinfo2 = new ArrayList<ReviewInfo>();
		List<Review> reviews=house.getReviews();
		for(Review rev: reviews){
			if(rev.getReview_for_host()){
				ReviewInfo revinfo = new ReviewInfo();
				revinfo.setText(rev.getReviewtext());
				revinfo.setForhost(rev.getReview_for_host());
				revinfo.setUsername(rev.getUser().getUsername());
				reviewsinfo1.add(revinfo);
			}else{
				ReviewInfo revinfo = new ReviewInfo();
				revinfo.setText(rev.getReviewtext());
				revinfo.setForhost(rev.getReview_for_host());
				revinfo.setUsername(rev.getUser().getUsername());
				reviewsinfo2.add(revinfo);
			}
		}
		houseinfo.setHostReviews(reviewsinfo1);
		houseinfo.setListReview(reviewsinfo2);
		String catbase= System.getProperty("catalina.base");
		String sep = System.getProperty("file.separator");
		if(house.getPhoto()!=null){
			File file1 = new File(catbase + sep + house.getPhoto());

			houseinfo.setFilename(house.getPhoto());
		
			String stringa1= encodeFileToBase64Binary(file1);
			houseinfo.setPhoto(stringa1);
		}
		if(house.getUser().getPhoto()!=null){
			File file2 = new File(catbase + sep + house.getUser().getPhoto());

			houseinfo.setFilename(house.getUser().getPhoto());
		
			String stringa2= encodeFileToBase64Binary(file2);
			houseinfo.setPhoto_owner(stringa2);
		}
		
		return Response.ok(houseinfo).build();
	}
	
	private static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String( Base64.getEncoder().encode(bytes), "UTF-8");
            fileInputStreamReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }
	
	@PUT
	@Secured
	@Path("/update")
	@Consumes({ "application/json" })
	public Response update_house(final HouseInfo houseinfo) {
		UserDAO userdao = new UserDAOImpl();
		model.User user = userdao.get_user(houseinfo.getOwner());
		
		HouseDAO housedao = new HouseDAOImpl();
		
		House house = new House();
		house.setUser(user);
		
		HousePK id = new HousePK();
		id.setIdHouse(houseinfo.getIdHouse());
		house.setId(id);
		
		house.setName(houseinfo.getHousename());
		house.setSquare_meters(houseinfo.getSquare_meters());
		house.setCountry(houseinfo.getCountry());
		house.setCity(houseinfo.getCity());
		house.setAddress(houseinfo.getAddress());
		house.setIsPrivate(houseinfo.getIsPrivate());
		house.setPriceperp(houseinfo.getPriceperp());
		house.setMaxnumofP(houseinfo.getMaxnumofp());
		house.setFloor(houseinfo.getFloor());
		house.setNumberBeds(houseinfo.getNumberBeds());
		house.setNumberBaths(houseinfo.getNumberBaths());
		house.setNumberBedrooms(houseinfo.getNumberBedrooms());
		house.setIsRoom(houseinfo.getIsRoom());
		house.setWiFi(houseinfo.getWiFi());
		house.setHeating(houseinfo.getHeating());
		house.setAc(houseinfo.getAc());
		house.setElevator(houseinfo.getElevator());
		house.setKitchen(houseinfo.getKitchen());
		house.setTv(houseinfo.getTv());
		house.setParking(houseinfo.getParking());
		house.setLivingRoom(houseinfo.getLivingRoom());
		house.setSummary(houseinfo.getSummary());
		house.setInformation(houseinfo.getInformation());
		house.setRentingRules(houseinfo.getRentingRules());
		house.setTransitInfo(houseinfo.getTransitInfo());
		house.setCoord_x(houseinfo.getCoord_x());
		house.setCoord_y(houseinfo.getCoord_y());
		house.setPhoto(houseinfo.getFilename());
		house.setPrice(houseinfo.getPrice());
		house.setStartAvailability(houseinfo.getDate_from());
		house.setEndAvailability(houseinfo.getDate_to());
		
        housedao.updateHouse(house);
		return Response.ok().build();
	}
	
	@POST
	@Path("/search")
	@Consumes({ "application/json" })
	@Produces({"application/json"})
	public Response search_house(final SearchInfo searchinfo) {
		HouseDAO housedao = new HouseDAOImpl();
		List<House> houses = housedao.search(searchinfo);
		UserDAO userdao = new UserDAOImpl();
		int userid;
		if(searchinfo.getName()!=null)
			userid = userdao.get_user_id(searchinfo.getName());
		else
			userid=-1;
		
		if (houses != null && houses.size()>0) {
			int in=1;
			List<HouseInfo> housesinfo= new ArrayList<HouseInfo>();
			for (House h : houses) {
				if(userid!=h.getUser().getIdUser()){
					if((searchinfo.getDate_from()!=null) && (searchinfo.getDate_to()!=null)){
						List<Reservation> reservations = h.getReservations();
						if(reservations==null){
							in=1;
						}else{
							in=1;
							for(Reservation res: reservations){
								if((searchinfo.getDate_from().after(res.getCheck_in()) && searchinfo.getDate_from().before(res.getCheck_out())) ||
										(searchinfo.getDate_to().after(res.getCheck_in()) && searchinfo.getDate_to().before(res.getCheck_out())) ||
										(searchinfo.getDate_from().before(res.getCheck_in()) && searchinfo.getDate_from().before(res.getCheck_out())) ||
										(searchinfo.getDate_to().after(res.getCheck_in()) && searchinfo.getDate_to().after(res.getCheck_out())))
					
					
								{
									in=0;
								}
							}
						}
					}
					if(in==1){
						HouseInfo house = new HouseInfo();
						house.setIdHouse(h.getId().getIdHouse());
						house.setHousename(h.getName());
						house.setPrice(h.getPrice());
						house.setIsPrivate(h.getIsPrivate());
						house.setIsRoom(h.getIsRoom());
						house.setNumberBeds(h.getNumberBeds());
						house.setNumberReviews(h.getNumberReviews());
						house.setRate(h.getRate());
						house.setNumberReviews(h.getReviews().size());
						if(h.getPhoto()!=null){
							String catbase= System.getProperty("catalina.base");
							String sep = System.getProperty("file.separator");
							File file = new File(catbase + sep + h.getPhoto());

							house.setFilename(house.getPhoto());
			
							String stringa= encodeFileToBase64Binary(file);
							house.setPhoto(stringa);
						}
						housesinfo.add(house);
					}
				}
			}
			return Response.ok(housesinfo).build();
		}else{
			int error=-1;
			return Response.ok(error).build();
		}
	}
	
	@POST
    @Path("/upload/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({"application/json"})
    public Response uploadFile( @PathParam("id") final int id, @Multipart("file") Attachment attachment) throws IOException {
		int error=1;
		try{
			String sep = System.getProperty("file.separator");
			String catbase= System.getProperty("catalina.base");

			String filename = attachment.getContentDisposition().getParameter("filename");
      
			HouseDAO dao = new HouseDAOImpl();
			String filename1 = id + filename;
			dao.insertPhoto(id, filename1);
			java.nio.file.Path path = Paths.get(catbase + sep + filename1);
			Files.deleteIfExists(path);
			InputStream in = attachment.getObject(InputStream.class);
			Files.copy(in, path);
       
		}catch (IOException e) {
			e.printStackTrace();
			return Response.ok(error).build();
		}  
		return Response.ok().build();
    } 
	
	@GET
	@Secured
	@Path("/list/{username}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public List<HouseInfo> list_house(@PathParam("username")final String username) {
		UserDAO userdao = new UserDAOImpl();
		User userd = userdao.get_user(username);
		List<House> houses=userd.getHouses();
		if(houses!=null){
			List<HouseInfo> housesinfo= new ArrayList<HouseInfo>();
			for (House h : houses) {
				HouseInfo house = new HouseInfo();
				house.setIdHouse(h.getId().getIdHouse());
				house.setHousename(h.getName());
				house.setSquare_meters(h.getSquare_meters());
				housesinfo.add(house);
			}
			return housesinfo;
		}else
			return null;
	}
	
	@POST
	@Path("/reservation")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response reservation(final ReservationInfo reservationinfo) {
		HouseDAO housedao = new HouseDAOImpl();
		House house = housedao.find_by_id(reservationinfo.getIdhouse());
		
		if(house!=null){
			UserDAO userdao = new UserDAOImpl();
			User user = userdao.get_user(reservationinfo.getUsername());
		
			ReservationDAO reservationdao = new ReservationDAOImpl();
			Reservation reservation = new Reservation();
			reservation.setCheck_in(reservationinfo.getDatefrom());
			reservation.setCheck_out(reservationinfo.getDateto());
			reservation.setHouse(house);
			reservation.setUser(user);
		
			reservationdao.create(reservation);
			return Response.ok().build();
		}else{
			int error=-1;
			return Response.ok(error).build();
		}
	}
	
	@POST
	@Path("/review")
	@Consumes({ "application/json" })
	@Produces({ "text/plain" })
	public Response review(final ReviewInfo reviewinfo) {
		HouseDAO housedao = new HouseDAOImpl();
		House house = housedao.find_by_id(reviewinfo.getIdhouse());
		
		UserDAO userdao = new UserDAOImpl();
		User user = userdao.get_user(reviewinfo.getUsername());
		
		List<Reservation> reservations = house.getReservations();
		
		int reserv=0;
		
		if(reservations != null){
			for(Reservation res: reservations){
				if(res.getId().getTenant_id()==user.getIdUser()){
					reserv=1;
					if(reviewinfo.getDate().after(res.getCheck_in())) {
						reserv=2;
					}
				}
			}
		}

		if(reserv==2){
			ReviewDAO reviewdao = new ReviewDAOImpl();
			Review review = new Review();
			review.setReviewtext(reviewinfo.getText());
			if(reviewinfo.getForhost()!=null)
				review.setReview_for_host(reviewinfo.getForhost());
			else
				review.setReview_for_host(false);
			review.setHouse(house);
			review.setUser(user);
		
			reviewdao.create(review);
			return Response.ok("true").build();
		}
		else
			return Response.ok("false").build();
	}
	
	@POST
	@Path("/rating")
	@Consumes({ "application/json" })
	@Produces({ "text/plain" })
	public Response rating(final ReviewInfo reviewinfo) {
		HouseDAO housedao = new HouseDAOImpl();
		House house = housedao.find_by_id(reviewinfo.getIdhouse());
		
		UserDAO userdao = new UserDAOImpl();
		User user = userdao.get_user(reviewinfo.getUsername());
		
		List<Reservation> reservations = house.getReservations();
		
		int reserv=0;
		
		if(reservations != null){
			for(Reservation res: reservations){
				if(res.getId().getTenant_id()==user.getIdUser()){
					reserv=1;
					if(reviewinfo.getDate().after(res.getCheck_in())) {
						reserv=2;
					}
				}
			}
		}

		
		if(reserv==2){
			float total_rate;
			total_rate=(house.getRate()+ reviewinfo.getRate())/2;
			housedao.insert_rate(reviewinfo.getIdhouse(), total_rate);
			return Response.ok("true").build();
		}
		else
			return Response.ok("false").build();
	}
}


