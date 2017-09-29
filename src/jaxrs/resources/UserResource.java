package jaxrs.resources;

import io.swagger.oas.annotations.*;
import io.swagger.oas.annotations.info.Contact;
import io.swagger.oas.annotations.info.Info;
import io.swagger.oas.annotations.parameters.RequestBody;
import io.swagger.oas.annotations.info.License;
import io.swagger.oas.annotations.headers.Header;
import io.swagger.oas.annotations.links.Link;
import io.swagger.oas.annotations.links.LinkParameter;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.media.ArraySchema;
import io.swagger.oas.annotations.media.ExampleObject;
import io.swagger.oas.annotations.media.Encoding;
import io.swagger.oas.annotations.responses.ApiResponse;
import io.swagger.oas.annotations.security.SecurityScheme;
import io.swagger.oas.annotations.security.SecurityRequirement;
import io.swagger.oas.annotations.media.Content;
import jaxrs.data.UserData;
import jaxrs.model.User;
import jaxrs.exception.ApiException;
import jaxrs.exception.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/user")
@Produces({"application/json", "application/xml"})
@Info(
	title = "User Resource API",
	version = "1.0.0",
	description = "API resource for User model of the AirlinesRatingApp.",
	termsOfService = "http://exampleurl.com/termsofservice",
	contact = @Contact(
			name = "AirlinesRatingApp API Support Team",
			url = "http://exampleurl.com/contact",
			email = "ask@airlinessupport.com"
			),
	license = @License(
			name = "Apache 2.0",
			url = "http://www.apache.org/licenses/LICENSE-2.0.html"
			)
	)
@SecurityScheme(
		  description = "user security scheme",
		  type = "http",
		  name = "test",
		  schemeName = "httpTestScheme",
		  scheme = "testScheme")
@SecurityRequirement(
		name = "users",
		scopes = "write:users")

public class UserResource {

	  static UserData userData = new UserData();
	  
	  @POST
	  @Operation(
			  method = "post",
			  summary = "Create user",
			  description = "This can only be done by the logged in user.",
			  operationId = "createUser",
			  requestBody = @RequestBody(
					  description = "Record of a new user to be created in the system.",
					  required = true,
					  content = @Content(
							  mediaType = "application/json",
							  schema = @Schema(
									  name = "testUser",
									  type = "object",
									  implementation = User.class),
							  examples = @ExampleObject(
										  name = "user",
										  summary = "External user example",										  
										  externalValue = "http://foo.bar/examples/user-example.json"
										  ),
							  encoding = @Encoding(
									  name = "email",
									  contentType = "text/plain",
									  style = "UTF-8",
									  allowReserved = true,
									  explode = true,
									  headers = @Header(
											  name = "testHeader"
											  )
									  )
							  )
					  ),
			  parameters = {
					  @Parameter(
							  name = "_id",
							  in = "query",
							  description = "User id for the new user record to be created",
							  required = true,
							  allowReserved = true,
							  style = "form",
							  schema = @Schema(type = "integer", format = "int32")
							  ),
					  @Parameter(
							  name = "userName",
							  in = "query",
							  description = "Username for the new user record to be created",
							  required = true,
							  schema = @Schema(
									  type = "string", 
									  externalDocs = @ExternalDocumentation(
											  description = "How to create good user names.",
											  url = "http://exampleurl.com/usernames"
											  )
									  )
							  ),
					  @Parameter(
							  name = "password",
							  in = "query",
							  description = "User password for the new user record to be created",
							  required = true,
							  hidden = true,
							  schema = @Schema(
									  type = "string",
									  externalDocs = @ExternalDocumentation(
											  description = "How to create good passwords.",
											  url = "http://exampleurl.com/passwords"
											  )
									  )
							  ),
					  @Parameter(
							  name = "firstName",
							  in = "query",
							  description = "User's first name for the new user record to be created",
							  required = true,
							  schema = @Schema(type = "string")
							  ),
					  @Parameter(
							  name = "lastName",
							  in = "query",
							  description = "User's last name for the new user record to be created",
							  style = "form",
							  required = true,
							  schema = @Schema(type = "string")
							  ),
					  @Parameter(
							  name = "sex",
							  in = "query",
							  description = "User's sex for the new user record to be created",
							  required = true,
							  style = "form",
							  schema = @Schema(type = "string")
							  ),
					  @Parameter(
							  name = "age",
							  in = "query",
							  description = "User's age for the new user record to be created",
							  required = true,
							  schema = @Schema(type = "integer", format = "int64")
							  ),
					  @Parameter(
							  name = "phone",
							  in = "query",
							  description = "User phone number for the new user record to be created",
							  required = true,
							  schema = @Schema(type = "string")
							  ),
					  @Parameter(
							  name = "status",
							  in = "query",
							  description = "User status for the new user record to be created",
							  required = true,
							  schema = @Schema(type = "integer")
							  )
			  },
			  responses = {
					  @ApiResponse(
							  responseCode = "200",
							  description = "New user record successfully created."
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
			  summary = "Creates list of users with given input array", //Array of User objects
			  operationId = "createUsersFromArray",
			  parameters = {
					  @Parameter(
							  name = "userArray",
							  in = "query",
							  description = "An array of User objects to create records.",
							  required = true,
							  content = @Content(
									  mediaType = "application/json",
									  schema = @Schema(
											  type = "array",
											  implementation = User.class
											  ),
									  encoding = @Encoding(
											  name = "firstName",
											  contentType = "application/json",
											  style = "UTF-8",
											  allowReserved = true,
											  explode = false
											  )
									  ),									  
							  array = @ArraySchema(
									  schema = @Schema(
											  type = "object",
											  implementation = User.class
											  ),
									  minItems = 2)
							  )							  
			  },
			  responses = {
					  @ApiResponse(
							  responseCode = "200",
							  description = "Successfully created list of users."
							  ),
					  @ApiResponse(
							  responseCode = "400",
							  description = "Unable to create list of users."
							  )
			  })
	  public Response createUsersWithArrayInput(
			  @Parameter(
					  description = "Array of user object", 
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
			  summary = "Creates list of users with given input list", //List of User objects
			  operationId = "createUsersFromList",
			  responses = {
					  @ApiResponse(
							  responseCode = "200",
							  description = "Successfully created list of users."
							  ),
					  @ApiResponse(
							  responseCode = "400",
							  description = "Unable to create list of users."
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
			  summary = "Update user",
			  description = "This can only be done by the logged in user.",
			  operationId = "updateUser",
			  requestBody = @RequestBody(
					  description = "Record of a new user to be created in the system.",
					  content = @Content(
							  mediaType = "application/json",
							  schema = @Schema(implementation = User.class),
							  examples = @ExampleObject(
									  name = "user",
									  summary = "Example user properties to update",
									  value = "Properties to update in JSON format here"
									  )
							  )
					  ),
			  responses = {
					  @ApiResponse(
							  responseCode = "200", 
							  description = "User updated successfully",
							  content = @Content(
									  schema = @Schema(
											  name = "upadtedUser",
											  implementation = User.class
											  ),
									  encoding = @Encoding(
											  name = "password",
											  contentType = "application/json",
											  style = "UTF-8",
											  allowReserved = true,
											  explode = false
											  )
									  )
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
	    		  description = "User that needs to be updated",
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
			  operationId = "deleteUser",
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
			  operationId = "getUserByUserName",
			  responses = {
					  @ApiResponse(
							  responseCode = "200", 
							  description = "Successfully retrieved user by user name.",
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
			  operationId = "getUserById",
			  responses = {
					  @ApiResponse(
							  responseCode = "200", 
							  description = "Successfully retrieved user by id.",
							  content = @Content(
										schema = @Schema(implementation = User.class)
								),
							  links = @Link(
									  name = "User name",
									  description = "The username corresponding to provided user id",
									  operationId = "getUserByName",
									  parameters = @LinkParameter(
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
	    		  schema = @Schema(type = "integer"),
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
			  operationId = "logInUser",
					  externalDocs = @ExternalDocumentation(
							  description = "Policy on user security.",
							  url = "http://exampleurl.com/policy"
							  ),
			  responses = {
					  
					  @ApiResponse(
							  responseCode = "200", 
							  description = "Successful user login.",
							  content = @Content(
										schema = @Schema(implementation = User.class)
								)
							  ),
					  
					  @ApiResponse(
							  responseCode = "400", 
							  description = "Invalid username/password supplied"
							  )
					  }
			  )
			
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
			  operationId = "logOutUser",
			  externalDocs = @ExternalDocumentation(
					  description = "Policy on user security.",
					  url = "http://exampleurl.com/policy"
					  ),
			  responses = {  
					  @ApiResponse(
							  responseCode = "200",
							  description = "Successful user logout."
							  ) 
					  })
	  public Response logoutUser() {
	    return Response.ok().entity("").build();
	  }
	}