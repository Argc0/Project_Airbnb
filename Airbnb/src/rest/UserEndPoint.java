package rest;

import java.security.Key;
import java.util.Date;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.*;
import java.nio.file.*; 
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;

import org.apache.cxf.jaxrs.ext.multipart.*; 

import annotations.Secured;
import dao.*;
import entities_info.*;
import export_to_xml.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.*;
import model.User;

@Path("/users")
public class UserEndPoint {

	@POST
	@Path("/login")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response login(final LoginInfo loginInfo) {
		UserDAO dao = new UserDAOImpl();
		User userd = dao.find_login(loginInfo.getUsername(), loginInfo.getPassword());
		if (userd != null) {
			loginInfo.setIsAdmin(userd.getIsAdmin());
			loginInfo.setIsApproved(userd.getIsApproved());
			if(userd.getIsApproved()==true){
				String token = issueToken(loginInfo.getUsername());
				loginInfo.setToken(token);
			}
			loginInfo.setIsHost(userd.getIsHost());
			return Response.ok(loginInfo).build();
		}
		else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	private String issueToken(String username) {
		Key key = utilities.KeyHolder.key;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		long expMillis = nowMillis + 1200000L;
        Date exp = new Date(expMillis);
		String jws = Jwts.builder()
				  .setSubject(username)
				  .setIssuedAt(now)
				  .signWith(SignatureAlgorithm.HS512, key)
				  .setExpiration(exp)
				  .compact();
		return jws;
    }
	
	@POST
	@Path("/signup")
	@Consumes({ "application/json" })
	public int create(final SignUpInfo signupinfo) {	
		UserDAO dao = new UserDAOImpl();
		
		if (dao.username_exists(signupinfo.getUsername())) {
			return -1;
		}
		User usr = new User();

		usr.setUsername(signupinfo.getUsername());
		usr.setPassword(signupinfo.getPassword());
		usr.setEmail(signupinfo.getEmail());
		usr.setFirstName(signupinfo.getFirstName());
		usr.setLastName(signupinfo.getLastName());
		usr.setIsTenant(signupinfo.getIstenant());
		usr.setIsHost(signupinfo.getIshost());
		if (signupinfo.getIshost()==true)
			usr.setIsApproved(false);
		else
			usr.setIsApproved(true);
		usr.setIsAdmin(false);
		if (signupinfo.getTelephone() !=null) usr.setTelephone(signupinfo.getTelephone());
		
        dao.create(usr);
        
        List<User> users = dao.list();
   
        int id_num=-2;
		for (User u : users) {
			id_num=u.getIdUser();
		}


		return id_num;
	}
	
	@POST
	@Path("/send_message")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response send_message(final MessageInfo messageinfo ) {
		UserDAO userdao = new UserDAOImpl();
		User user1 = userdao.get_user(messageinfo.getHost_name());
		User user2 = userdao.get_user(messageinfo.getTenant_name());
		
		Message message = new Message();
		
		message.setText(messageinfo.getText());
		message.setUser1(user1);
		message.setUser2(user2);
		
		MessageDAO messagedao = new MessageDAOImpl();
		
		messagedao.create(message);
		return Response.ok().build();
	}
	
	@GET
	@Secured
	@Path("/list_messages/{username}")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public List<MessageList> send_message(@PathParam("username")final String username ) {
		UserDAO userdao = new UserDAOImpl();
		int userid = userdao.get_user_id(username);
		
		MessageDAO messagedao = new MessageDAOImpl();
		List<Message> messages =messagedao.user_messages(userid);
		if(messages!=null){
			int count = userdao.return_count();
			int flag=0;
			String name_user=null;
			List<MessageList> listmessagelist = new ArrayList<MessageList>();
			for(int i=1; i<=count; i++){
				flag=0;
				if(i!=userid){
					List<MessageInfo> listmessageinfo=new ArrayList<MessageInfo>();
					for(Message msg: messages){
						if((msg.getId().getHost_id()==i)||(msg.getId().getTenant_id()==i)){
							flag=1;
							MessageInfo messageinfo= new MessageInfo();
							messageinfo.setHost_name(msg.getUser1().getUsername());
							messageinfo.setTenant_name(msg.getUser2().getUsername());
							messageinfo.setHost_id(msg.getId().getHost_id());
							messageinfo.setTenant_id(msg.getId().getTenant_id());
							messageinfo.setIdMessage(msg.getId().getIdMessage());
							messageinfo.setText(msg.getText());
							listmessageinfo.add(messageinfo);
							if(msg.getId().getHost_id()==userid)
								name_user=msg.getUser2().getUsername();
							else if(msg.getId().getTenant_id()==userid)
								name_user=msg.getUser1().getUsername();
						}
					}
					if(flag==1){
						MessageList messageList = new MessageList();
						messageList.setName(name_user);
						messageList.setListMessage(listmessageinfo);
						listmessagelist.add(messageList);
					}
				}
			}
			return listmessagelist;
		}else
			return null;
	}
	
	@PUT
	@Path("/delete_message")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response delete_message(final MessageInfo messageinfo ) {
		MessageDAO messagedao = new MessageDAOImpl();
		boolean bol=messagedao.del_message(messageinfo.getIdMessage());
		if(bol){
			return Response.ok().build();
		}else{
			int error=-1;
			return Response.ok(error).build();
		}
	}
	
	@GET
	@Secured
	@Path("admin/{username}")
	@Produces({"application/json"})
	public Response findByUsername(@PathParam("username") final String username) {
		UserDAO userdao = new UserDAOImpl();
		User userd = userdao.get_user(username);
		
		if(userd!=null){
			UserInfo user = new UserInfo();
			user.setIdUser(userd.getIdUser());
			user.setUsername(userd.getUsername());
			user.setIsApproved(userd.getIsApproved());
	
			user.setPassword(userd.getPassword());
			user.setEmail(userd.getEmail());
			user.setFirstName(userd.getFirstName());
			user.setLastName(userd.getLastName());
		
			user.setIsHost(userd.getIsHost());
			user.setIsTenant(userd.getIsTenant());
			user.setTelephone(userd.getTelephone());
			String catbase= System.getProperty("catalina.base");
			String sep = System.getProperty("file.separator");
			if(userd.getPhoto()!=null){
				File file = new File(catbase + sep + userd.getPhoto());
		
				String string64= encodeFileToBase64Binary(file);
				user.setPhoto(string64);
			}
			return Response.ok(user).build();
		}else{
			return Response.ok(null).build();
		}
	}

	
	@GET
	@Secured
	@Path("/admin")
	@Produces({ "application/json" })
	public List<UserInfo> listAll() {
		UserDAO userdao = new UserDAOImpl();
		List<User> usersd = userdao.list();
		List<UserInfo> users = null;
		if (usersd != null && usersd.size()>0)
		{
			users = new ArrayList<UserInfo>();
			for (User userd : usersd)
			{
				UserInfo user = new UserInfo();
				user.setIdUser(userd.getIdUser());
				user.setUsername(userd.getUsername());
				user.setIsApproved(userd.getIsApproved());
				users.add(user);
			}
			return users;
		}else
			return null;
	}
	
	@PUT
	@Secured
	@Path("/admin/{id}")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response update_user(@PathParam("id")final int id) {
		UserDAO dao = new UserDAOImpl();
		boolean check = dao.aprrove_user(id);
		if(!check) {
			int error=-1;
			return Response.ok(error).build();
		}else
			return Response.ok().build();
	}
	
	@PUT
	@Secured
	@Path("/edit")
	@Consumes({"application/json"})
	public Response editUser(final UserInfo userinfo) {
		UserDAO dao = new UserDAOImpl();
		User usr = new User();

		usr.setIdUser(userinfo.getIdUser());
		usr.setUsername(userinfo.getUsername());
		usr.setPassword(userinfo.getPassword());
		usr.setEmail(userinfo.getEmail());
		usr.setFirstName(userinfo.getFirstName());
		usr.setLastName(userinfo.getLastName());
		usr.setIsTenant(userinfo.getIsTenant());
		usr.setIsHost(userinfo.getIsHost());
		usr.setTelephone(userinfo.getTelephone());
		
		dao.updateUser(usr);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/change-pwd/{username}")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response ChangePassword(@PathParam("username")final String username, final String pwd) {
		UserDAO dao = new UserDAOImpl();
		boolean check = dao.changePassword (username, pwd);
		if (check == false) {
			int error=-1;
			return Response.ok(error).build();
		}
		return Response.ok().build();
	}
	
	
	@POST
    @Path("/upload/{id}")
	@Produces({"application/json"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile( @PathParam("id") final int id, @Multipart("file") Attachment attachment) throws IOException {
		int error=-1;
		try{
			String sep = System.getProperty("file.separator");
			String catbase= System.getProperty("catalina.base");

			String filename = attachment.getContentDisposition().getParameter("filename");
      
			UserDAO dao = new UserDAOImpl();
			String filename1 = id + filename;
			dao.insertPhoto(id, filename1);
			java.nio.file.Path path = Paths.get(catbase + sep + filename1);
			Files.deleteIfExists(path);
			InputStream in = attachment.getObject(InputStream.class);
			Files.copy(in, path);
		} catch (IOException e) {
			e.printStackTrace();
			return Response.ok(error).build();
		}  
		return Response.ok().build();
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
	
	
	@GET
	@Secured
	@Path("/admin/xml")
	@Produces({ "application/zip" })
	public Response export_xml() throws IOException {
		int error=-1;
		String catbase= System.getProperty("catalina.base");
		String sep = System.getProperty("file.separator");
		String xml_folder_path = catbase + sep + "Airbnb_xml_files";

	    try {
	    	File f = new File(xml_folder_path);
	    	f.mkdir();
	    }
	    catch(Exception e) {
	         e.printStackTrace();
	         return Response.ok(error).build();
	    }
	
		String houses_filename = xml_folder_path + sep + "houses.xml";
		File file = new File(houses_filename);
		Houses houses = makeHouseXml();
	    try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Houses.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(houses, file);
			
		} catch (JAXBException e) {
			e.printStackTrace();
			return Response.ok(error).build();
		}
	    
	    String reservations_filename = xml_folder_path + sep + "reservations.xml";
		file = new File(reservations_filename);
		Reservations reservations = makeReservationXml();
	    try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Reservations.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(reservations, file);
			
		} catch (JAXBException e) {
			e.printStackTrace();
			return Response.ok(error).build();
		}
	    
	    String reviews_for_houses_filename = xml_folder_path + sep + "reviews_for_houses.xml";
		file = new File(reviews_for_houses_filename);
		HouseReviews housereviews = makeReviewsHousesXml();
	    try {
			JAXBContext jaxbContext = JAXBContext.newInstance(HouseReviews.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(housereviews, file);
			
		} catch (JAXBException e) {
			e.printStackTrace();
			return Response.ok(error).build();
		}
	    
	    String reviews_for_hosts_filename = xml_folder_path + sep + "reviews_for_hosts.xml";
		file = new File(reviews_for_hosts_filename);
		HostReviews hostreviews = makeReviewsHostsXml();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(HostReviews.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(hostreviews, file);
			
		} catch (JAXBException e) {
			e.printStackTrace();
			return Response.ok(error).build();
		}

		
		File zipfile = new File (xml_folder_path + sep + "xml.zip");
		  
		try {
			FileOutputStream fos = new FileOutputStream(zipfile);
			ZipOutputStream zos = new ZipOutputStream(fos);
		
			addToZipFile(houses_filename, zos, "houses.xml");
			addToZipFile(reservations_filename, zos, "reservations.xml");
			addToZipFile(reviews_for_houses_filename, zos, "reviews_for_houses.xml");
			addToZipFile(reviews_for_hosts_filename, zos, "reviews_for_hosts.xml");
		
			zos.close();
			fos.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.ok(error).build();
		}  
		return Response.ok((Object)zipfile).build();
	}
	
	private Houses makeHouseXml () {
		Houses houses = new Houses();
		
		HouseDAO housedao = new HouseDAOImpl();
		List<House> houseslist = housedao.list();
		
		List<House_to_xml> housesxml = null;
		
		if (houseslist != null && houseslist.size()>0) {
			housesxml = new ArrayList<House_to_xml>();
			
			for (House housed : houseslist) {
				House_to_xml housexml = new House_to_xml();
				housexml.setId(housed.getId().getIdHouse());
				housexml.setOwner(housed.getUser().getUsername());
				housexml.setCountry(housed.getCountry());
				housexml.setCity(housed.getCity());
				housexml.setAddress(housed.getAddress());
				housexml.setDate_from(housed.getStartAvailability());
				housexml.setDate_to(housed.getEndAvailability());
				housexml.setHousename(housed.getName());
				housexml.setIsprivate(housed.getIsPrivate());
				housexml.setPrice(housed.getPrice());
				housexml.setSquaremeters(housed.getSquare_meters());
				
				housesxml.add(housexml);
			}
		}
		
		houses.setHouses(housesxml);
		return houses;
	}
	
	private Reservations makeReservationXml () {
		Reservations reservations = new Reservations();
		
		ReservationDAO reservationdao = new ReservationDAOImpl();
		List<Reservation> reservationslist = reservationdao.list();
		
		List<Tenant_Reservation> tenant_reservations = new ArrayList<Tenant_Reservation>();
		int flag=0;
		String tenant=null;
		
		UserDAO userdao = new UserDAOImpl();
		int count = userdao.return_count();
		if (reservationslist != null && reservationslist.size()>0) {
			for(int i=1; i<=count; i++){
				flag=0;
				List<Reservation_to_xml> reservationsxml = new ArrayList<Reservation_to_xml>();
				for (Reservation res : reservationslist) {
					if(res.getUser().getIdUser()==i){
						flag=1;
						Reservation_to_xml reservationxml = new Reservation_to_xml();
						reservationxml.setIdreservation(res.getId().getIdReservation());
						reservationxml.setHousename(res.getHouse().getName());
						reservationxml.setDatefrom(res.getCheck_in());
						reservationxml.setDateto(res.getCheck_out());
				
						reservationsxml.add(reservationxml);
						tenant= res.getUser().getUsername();
					}
				}
				if(flag==1){
					Tenant_Reservation tenant_res = new Tenant_Reservation();
					tenant_res.setTenantname(tenant);
					tenant_res.setReservations(reservationsxml);
					tenant_reservations.add(tenant_res);
				}
			}
		}
		
		reservations.setReservations(tenant_reservations);
		return reservations;
	}
		
	private HouseReviews makeReviewsHousesXml () {
		HouseReviews housereviews = new HouseReviews();
		
		ReviewDAO reviewdao = new ReviewDAOImpl();
		List<Review> reviewslist = reviewdao.reviews_for_houses();
		
		List<Reviews_for_house> reviewsforhouse = new ArrayList<Reviews_for_house>();
		int flag=0;
		String housename=null;
		int id = 0;
		
		HouseDAO housedao = new HouseDAOImpl();
		int count = housedao.return_count();
		
		if (reviewslist != null && reviewslist.size()>0) {
			for(int i=1; i<=count; i++){
				flag=0;
				List<Review_to_xml> reviewsxml = new ArrayList<Review_to_xml>();
				for (Review rev : reviewslist) {
					if(rev.getHouse().getId().getIdHouse()==i){
						flag=1;
						Review_to_xml reviewxml = new Review_to_xml();
						reviewxml.setIdreview(rev.getId().getIdReview());
						reviewxml.setText(rev.getReviewtext());
						reviewxml.setEditor(rev.getUser().getUsername());
				
						reviewsxml.add(reviewxml);
						housename= rev.getHouse().getName();
						id=rev.getHouse().getId().getIdHouse();
					}
				}
				if(flag==1){
					Reviews_for_house review_for_house = new Reviews_for_house();
					review_for_house.setHousename(housename);
					review_for_house.setIdhouse(id);
					review_for_house.setReviews(reviewsxml);
					reviewsforhouse.add(review_for_house);
				}
			}
		}
		
		housereviews.setReviews(reviewsforhouse);;
		return housereviews;
	}
	
	private HostReviews makeReviewsHostsXml () {
		HostReviews hostreviews = new HostReviews();
		
		ReviewDAO reviewdao = new ReviewDAOImpl();
		List<Review> reviewslist = reviewdao.reviews_for_hosts();
		
		List<Reviews_for_host> reviewsforhost = new ArrayList<Reviews_for_host>();
		int flag=0;
		String hostname=null;
		
		UserDAO userdao = new UserDAOImpl();
		int count = userdao.return_count();
		
		if (reviewslist != null && reviewslist.size()>0) {
			for(int i=1; i<=count; i++){
				flag=0;
				List<Review_to_xml> reviewsxml = new ArrayList<Review_to_xml>();
				for (Review rev : reviewslist) {
					if(rev.getHouse().getId().getHost_id()==i){
						flag=1;
						Review_to_xml reviewxml = new Review_to_xml();
						reviewxml.setIdreview(rev.getId().getIdReview());
						reviewxml.setText(rev.getReviewtext());
						reviewxml.setEditor(rev.getUser().getUsername());
				
						reviewsxml.add(reviewxml);
						hostname= rev.getHouse().getUser().getUsername();
					}
				}
				if(flag==1){
					Reviews_for_host review_for_host = new Reviews_for_host();
					review_for_host.setHostname(hostname);
					review_for_host.setReviews(reviewsxml);
					reviewsforhost.add(review_for_host);
				}
			}
		}
		
		hostreviews.setReviews(reviewsforhost);
		return hostreviews;
	}
	
	private void addToZipFile(String fileName, ZipOutputStream zos, String entry) throws FileNotFoundException, IOException {
	
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(entry);
		zos.putNextEntry(zipEntry);
	
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}
	
		zos.closeEntry();
		fis.close();
	}
	
	@GET
	@Path("/check_username/{name}")
	@Consumes({ "application/json" })
	@Produces({ "text/plain" })
	public Response check_username(@PathParam("name")String name) {
		UserDAO userd = new UserDAOImpl();
		
		boolean check_boolean=userd.username_exists(name);
		if(check_boolean){
			return Response.ok("true", "text/plain").build();
		}else{
			return Response.ok("false", "text/plain").build();
		}

	}
}
