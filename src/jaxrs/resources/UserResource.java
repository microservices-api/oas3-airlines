package jaxrs.resources;

import io.swagger.oas.annotations.*;
import io.swagger.oas.annotations.info.Contact;
import io.swagger.oas.annotations.parameters.RequestBody;
import io.swagger.oas.annotations.info.License;
import io.swagger.oas.annotations.links.Link;
import io.swagger.oas.annotations.links.LinkParameters;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.media.ExampleObject;
import io.swagger.oas.annotations.responses.ApiResponse;
import io.swagger.oas.annotations.media.Content;
import jaxrs.data.UserData;
import jaxrs.model.User;
import jaxrs.exception.ApiException;
import jaxrs.exception.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/user")
@Contact(
		name = "AirlinesRatingApp API Support",
		url = "http://airlinesapisupport.com/help",
		email = "askus@airlinessupport.com"
		)
@License(
		name = "Apache 2.0",
		url = "http://www.apache.org/licenses/LICENSE-2.0.html"
		)
@Schema(
		name = "User Schema",
		description = "API resource for user model.",
		externalDocs = @ExternalDocumentation(				
				description = "For more information, see the link.",
				url = "https://github.com/janamanoharan/airlinesratingapp")
		)
@Produces({"application/json", "application/xml"})

public class UserResource {

	  static UserData userData = new UserData();

	  @POST
	  @Operation(
			  method = "post",
			  summary = "Create user",
			  description = "This can only be done by the logged in user.",
			  requestBody = @RequestBody(
					  description = "Record of a new user to be created in the system.",
					  content = @Content(
							  mediaType = "application/json",
							  schema = @Schema(
									  implementation = User.class),
							  examples = @ExampleObject(
										  name = "user",
										  summary = "External user example",
										  externalValue = "http://foo.bar/examples/user-example.json"
										  )
							  )
					  ),
			  responses = {
					  @ApiResponse(
							  responseCode = "200",
							  description = "New user record successfully created"
							  ),
					  @ApiResponse(
							  responseCode = "400",
							  description = "Unable to create this user record."
							  )
			  })
	  public Response createUser(
	      @Parameter(
	    		  description = "Created user object",
	    		  schema = @Schema(implementation = User.class),
	    		  required = true
	    		  ) User user) {
	    userData.addUser(user);
	    return Response.ok().entity("").build();
	  }

	  @POST
	  @Path("/createWithArray")
	  @Operation(
			  method = "post",
			  summary = "Creates list of users with given input array",
			  responses = {
					  @ApiResponse(
							  responseCode = "200",
							  description = "successful operation"
							  )
			  })
	  public Response createUsersWithArrayInput(
			  @Parameter(
					  description = "List of user object", 
					  required = true
					  ) User[] users) {
	      for (User user : users) {
	          userData.addUser(user);
	      }
	      return Response.ok().entity("").build();
	  }

	  @POST
	  @Path("/createWithList")
	  @Operation(
			  method = "post",
			  summary = "Creates list of users with given input list",
			  responses = {
					  @ApiResponse(
							  responseCode = "200",
							  description = "successful operation"
							  )
			  })
	  public Response createUsersWithListInput(
			  @Parameter(
					  description = "List of user object", 
					  required = true) java.util.List<User> users) {
	      for (User user : users) {
	          userData.addUser(user);
	      }
	      return Response.ok().entity("").build();
	  }

	  @PUT
	  @Path("/{username}")
	  @Operation(
			  method = "put",
			  summary = "Updated user",
			  description = "This can only be done by the logged in user.",
			  responses = {
					  @ApiResponse(
							  responseCode = "200", 
							  description = "User updated successfully"
							  ),
					  @ApiResponse(
							  responseCode = "400", 
							  description = "Invalid user supplied"
							  ),
					  @ApiResponse(
							  responseCode = "404", 
							  description = "User not found"
							  )
			  })
	  public Response updateUser(
	      @Parameter(
	    		  name = "username",
	    		  description = "name that need to be deleted",
	    		  schema = @Schema(type = "String"),
	    		  required = true
	    		  ) 
	      @PathParam("username") String username,
	      @Parameter(
	    		  description = "Updated user object", 
	    		  required = true) User user) {
	    userData.addUser(user);
	    return Response.ok().entity("").build();
	  }

	  @DELETE
	  @Path("/{username}")
	  @Operation(
			  method = "delete",
			  summary = "Delete user",
			  description = "This can only be done by the logged in user.",
			  responses = {
					  @ApiResponse(
							  responseCode = "200", 
							  description = "User deleted successfully"
							  ),
					  @ApiResponse(
							  responseCode = "400", 
							  description = "Invalid username supplied"
							  ),
					  @ApiResponse(
							  responseCode = "404", 
							  description = "User not found"
							  ) 
					  })
	  public Response deleteUser(
	      @Parameter(
	    		  name = "username",
	    		  description = "The name that needs to be deleted",
	    		  schema = @Schema(type = "String"),
	    		  required = true
	    		  ) 
	      @PathParam("username") String userName) {
	    if (userData.removeUser(userName)) {
	      return Response.ok().entity("").build();
	    } else {
	      return Response.status(Response.Status.NOT_FOUND).build();
	    }
	  }

	  @GET
	  @Path("/{username}")
	  @Operation(
			  method = "get",
			  summary = "Get user by user name",
			  operationId = "getUserName",
			  responses = {
					  @ApiResponse(
							  responseCode = "200", 
							  description = "successful operation",
							  content = @Content(
										schema = @Schema(implementation = User.class)
								)),
					  @ApiResponse(
							  responseCode = "400", 
							  description = "Invalid username supplied",
							  content = @Content(
										schema = @Schema(implementation = User.class)
								)),
					  @ApiResponse(
							  responseCode = "404", 
							  description = "User not found",
									  content = @Content(
												schema = @Schema(implementation = User.class)
										)
							  ) 
					  })
	  public Response getUserByName(
	      @Parameter(
	    		  name = "username",
	    		  description = "The name that needs to be fetched. Use Bob1 for testing.", 
	    		  schema = @Schema(type = "String"),
	    		  required = true
	    		  ) 
	      @PathParam("username") String userName) throws ApiException {
	    User user = userData.findUserByName(userName);
	    if (null != user) {
	      return Response.ok().entity(user).build();
	    } else {
	      throw new NotFoundException(404, "User not found");
	    }
	  }
	  
	  @GET
	  @Path("/{id}")
	  @Operation(
			  method = "get",
			  summary = "Get user by id",
			  responses = {
					  @ApiResponse(
							  responseCode = "200", 
							  description = "Successful operation",
							  content = @Content(
										schema = @Schema(implementation = User.class)
								),
							  links = @Link(
									  name = "User name",
									  description = "The username corresponding to provided user id",
									  operationId = "getUserName",
									  parameters = @LinkParameters(
											  name = "userId",
											  expression = "$request.path.id"))
							  ),
					  @ApiResponse(
							  responseCode = "400", 
							  description = "Invalid id supplied",
							  content = @Content(
										schema = @Schema(implementation = User.class)
								)),
					  @ApiResponse(
							  responseCode = "404", 
							  description = "User not found",
									  content = @Content(
												schema = @Schema(implementation = User.class)
										)
							  ) 
					  })
	  public Response getUserById(
	      @Parameter(
	    		  name = "id",
	    		  description = "The name that needs to be fetched. Use 1 for testing.", 
	    		  schema = @Schema(type = "int"),
	    		  required = true
	    		  ) 
	      @PathParam("id") int _id) throws ApiException {
	    User user = userData.findUserById(_id);
	    if (null != user) {
	      return Response.ok().entity(user).build();
	    } else {
	      throw new NotFoundException(404, "User not found");
	    }
	  }

	  @GET
	  @Path("/login")
	  @Operation(
			  method = "get",
			  summary = "Logs user into the system",
			  responses = {
					  
					  @ApiResponse(
							  responseCode = "200", 
							  description = "successful operation",
							  content = @Content(
										schema = @Schema(implementation = User.class)
								)
							  ),
					  
					  @ApiResponse(
							  responseCode = "400", 
							  description = "Invalid username/password supplied"
							  )
					  })
			
	  public Response loginUser(
	      @Parameter(
	    		  name = "username",
	    		  description = "The user name for login",
	    		  schema = @Schema(type = "String"),
	    		  required = true
	    		  ) 
	      @QueryParam("username") String username,
	      @Parameter(
	    		  name = "password",
	    		  description = "The password for login in clear text",
	    		  schema = @Schema(type = "String"),
	    		  required = true) 
	      @QueryParam("password") String password) {
	    return Response.ok()
	        .entity("logged in user session:" + System.currentTimeMillis())
	        .build();
	  }

	  @GET
	  @Path("/logout")
	  @Operation(
			  method = "get",
			  summary = "Logs out current logged in user session",
			  responses = {  
					  @ApiResponse(
							  responseCode = "200",
							  description = "successful operation"
							  ) 
					  })
	  public Response logoutUser() {
	    return Response.ok().entity("").build();
	  }
	}

