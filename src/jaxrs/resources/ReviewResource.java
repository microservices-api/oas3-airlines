package jaxrs.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.swagger.oas.annotations.Operation;
import io.swagger.oas.annotations.Parameter;
import io.swagger.oas.annotations.callbacks.Callback;
//import io.swagger.oas.annotations.callbacks.Callback;
import io.swagger.oas.annotations.security.OAuthFlows;
import io.swagger.oas.annotations.security.OAuthFlow;
//import io.swagger.oas.annotations.servers.Server;
//import io.swagger.oas.annotations.servers.ServerVariable;
import io.swagger.oas.annotations.media.Content;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.parameters.Parameters;
import io.swagger.oas.annotations.parameters.RequestBody;
import io.swagger.oas.annotations.media.ExampleObject;
import io.swagger.oas.annotations.responses.ApiResponse;
import io.swagger.oas.annotations.security.SecurityScheme;
import io.swagger.oas.annotations.servers.Server;
import io.swagger.oas.annotations.servers.ServerVariable;
import io.swagger.oas.annotations.security.OAuthScope;
import io.swagger.oas.annotations.security.SecurityRequirement;
import jaxrs.model.Airline;
import jaxrs.model.Review;
import jaxrs.model.User;

@Path("/reviews")
@Schema(name = "Airlines Rating App")
@SecurityScheme(
		type = "oauth2",
		description = "authentication needed to create and delete reviews",
		flows = @OAuthFlows(
					implicit = @OAuthFlow(
							authorizationUrl = "https://example.com/api/oauth/dialog",
							scopes = @OAuthScope(
									name = "write:reviews",
									description = "create a review"
									)
							),
					authorizationCode = @OAuthFlow(
							authorizationUrl = "https://example.com/api/oauth/dialog",
							tokenUrl = "https://example.com/api/oauth/token"
							)
				),
		name = "")

public class ReviewResource {
	
	private static Map<Integer, Review> reviews = new ConcurrentHashMap<Integer, Review>();
	private volatile int currentId = 0;
	
	static {
		reviews.put(1, new Review(new User(), new Airline("Acme Air", "1-888-1234-567"), 8, "great!")  );
		reviews.put(2, new Review(new User(), new Airline("Acme Air", "1-888-1234-567"), 7, "good")  );
	}
	@GET
	@Operation(
			
			operationId = "getAllReviews",
			summary = "get all the reviews",
			method = "GET",
			responses = @ApiResponse(
					responseCode = "200",
					description = "successful operation",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(
									type = "array",
									implementation = Review.class
									)
							)
					)
			)
	@Produces("application/json")
	public Response getAllReviews(){
		return Response.ok().entity(reviews.values()).build();
	}
	
	@GET
	@Path("{id}")
	@Operation(
			operationId = "getReviewById",
			
			summary="Get a review with ID", 
			responses={
					@ApiResponse(
							responseCode="200", 
							description="Review retrieved",
							content=@Content(
									schema=@Schema(
											implementation=Review.class))),
					@ApiResponse(
							responseCode="404", 
							description="Review not found")
			})
	@Produces("application/json")
	public Response getReviewById(
			@Parameter(
					name = "id",
					description = "ID of the booking",
					required = true, 
					in = "path",
					content = @Content(
							examples = @ExampleObject(
										value = "1")))
			@PathParam("id") int id){
		Review review = reviews.get(id);
		if(review!=null){
			return Response.ok().entity(review).build();	
		}
		else{			
			return Response.status(Status.NOT_FOUND).build();	
		}
	}
	
	@GET
	@Path("{user}")
	@Operation(
			operationId = "getReviewByUser",
			summary="Get all reviews by user", 
			responses={
					@ApiResponse(
							responseCode="200", 
							description="Review(s) retrieved", 
							content=@Content(
									schema=@Schema(
											implementation=Review.class))),
					@ApiResponse(
							responseCode="404", 
							description="Review(s) not found")
			})
	@Produces("application/json")
	public Response getReviewByUser(
			@Parameter(
					name = "user",
					description = "username of the user for the reviews",
					required = true, 
					in = "path",
					content = @Content(
							examples = @ExampleObject(
										value = "bsmith"))) 
			@PathParam("user") String user){
		
		List<Review> reviewsByUser = new ArrayList<Review>();
		for (Review review : reviews.values()) {
			User currentUser = review.getUser();
			if (currentUser.getUsername() == user) {
				reviewsByUser.add(review);
			}
		}
		if(!reviewsByUser.isEmpty()){
			return Response.ok().entity(reviewsByUser).build();	
		}
		else{			
			return Response.status(Status.NOT_FOUND).build();	
		}
	}

	@GET
	@Path("{airline}")
	@Operation(
			operationId = "getReviewByAirline",
			summary="Get all reviews by airlines", 
			parameters = {
					@Parameter(
							name = "airline",
							description = "name of the airlines for the reviews",
							required = true, 
							in = "path",
							content = @Content(
									examples = @ExampleObject(
												value = "Acme Air"))) 
					
			},
			responses={
					@ApiResponse(
							responseCode="200", 
							description="Review(s) retrieved", 
							content=@Content(
									schema=@Schema(
											implementation=Review.class))),
					@ApiResponse(
							responseCode="404", 
							description="Review(s) not found")
			})
	@Produces("application/json")
	public Response getReviewByAirline(
			@Parameter(
					name = "airline",
					description = "name of the airlines for the reviews",
					required = true, 
					in = "path",
					content = @Content(
							examples = @ExampleObject(
										value = "Acme Air"))) 
			@PathParam("airline") String airlines){
		
		List<Review> reviewsByAirlines = new ArrayList<Review>();
		for (Review review : reviews.values()) {
			Airline currentAirline = review.getAirlines();
			if (currentAirline.getName() == airlines) {
				reviewsByAirlines.add(review);
			}
		}
		if(!reviewsByAirlines.isEmpty()){
			return Response.ok().entity(reviewsByAirlines).build();	
		}
		else{			
			return Response.status(Status.NOT_FOUND).build();	
		}
	}
	
	@GET
	@Path("{user}/{airlines}")
	@Operation(
			operationId = "getReviewByAirlineAndUser",
			summary="Get all reviews for an airline by User", 
			responses={
					@ApiResponse(
							responseCode="200", 
							description="Review(s) retrieved", 
							content=@Content(
									schema=@Schema(
											implementation=Review.class))),
					@ApiResponse(
							responseCode="404", 
							description="Review(s) not found")
			})
	@Produces("application/json")
	public Response getReviewByAirlineAndUser(
			@Parameters(
					parameters = {
							@Parameter(
								name = "airlines",
								description = "name of the airlines for the reviews",
								required = true, 
								in = "path",
								content = @Content(
										examples = @ExampleObject(
													value = "Acme Air"))),
							@Parameter(
									name = "user",
									description = "sername of the user for the reviews",
									required = true, 
									in = "path",
									content = @Content(
											examples = @ExampleObject(
														value = "bsmith")))
					})
			@PathParam("user") String user,
			@PathParam("airlines") String airlines){
		List<Review> reviewsByAirlinesUser = new ArrayList<Review>();
		for (Review review : reviews.values()) {
			Airline currentAirline = review.getAirlines();
			User currentUser = review.getUser();
			
			if (currentAirline.getName() == airlines && currentUser.getUsername() == user) {
				reviewsByAirlinesUser.add(review);
			}
		}
		if(!reviewsByAirlinesUser.isEmpty()){
			return Response.ok().entity(reviewsByAirlinesUser).build();	
		}
		else{			
			return Response.status(Status.NOT_FOUND).build();	
		}
	}
	
	/*
	 * TODO: add authentication once security field is working
	 * TODO: add callbacks
	 */
	
	@POST
	@Operation(
			summary="Create a Review",
			servers = {
					@Server(
							url = "localhost:9080/airlines/reviews/{id}",
							description = "view of all the reviews",
							variables = {
									@ServerVariable(
											name = "id",
											description = "id of the review",
											defaultValue = "1")
							})			
			},
			security = @SecurityRequirement(
					name = "reviews",
					scopes = "write:reviews"),
			responses={
					@ApiResponse(
							responseCode="201",
							description="review created",
							content = @Content(
									schema=@Schema(name= "id", description = "id of the new review",type="string")))
					},
			requestBody = @RequestBody(
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Review.class),
							examples = @ExampleObject(
									name = "review",
									summary = "External review example",										  
									externalValue = "http://foo.bar/examples/review-example.json"
									)
							),
					required = true,
					description = "example review to add"
					)
			)
	@Consumes("application/json")
	@Produces("application/json")
	public Response createReview(
			@Parameter(
					description = "review to create",
					required = true,
					content = @Content(
							mediaType = "application/json",
							
							schema = @Schema(implementation = Review.class))) 
			Review review) {
		reviews.put(currentId, review);
		return Response.status(Status.CREATED).entity("{\"id\":" + currentId++ + "}").build();	
	}
	
	/*
	 * TODO: add authentication once security field is working
	 */
	
	@DELETE
	@Path("{id}")
	@Operation(
			summary="Delete a Review with ID", 
			responses={
					@ApiResponse(
							responseCode="200", 
							description="Review deleted"
							),
					@ApiResponse(
							responseCode="404", 
							description="Review not found"
							)
			})
	@Produces("text/plain")
	public Response deleteReview(
			@PathParam("id") int id){
		if(reviews.get(id)!=null) {
			reviews.remove(id);
			return Response.ok().build();
		}
		else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
