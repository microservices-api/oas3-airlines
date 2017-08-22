/**
* (C) Copyright IBM Corporation 2016.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package jaxrs.app;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.oas.annotations.info.Contact;
import io.swagger.oas.annotations.info.Info;
import io.swagger.oas.annotations.info.License;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.security.SecurityRequirement;
import jaxrs.resources.AirlinesResource;
import jaxrs.resources.AvailabilityResource;
import jaxrs.resources.BookingResource;
import jaxrs.resources.ReviewResource;

@ApplicationPath("/")
@Info(title="Airlines API", 
	  version = "1.0",
      contact = @Contact(email = "bobsmith@bsmith.com", name ="Bob Smith", url = "http://bobsmith.com"),
      license = @License(name="license 1", url="http://bobsmith.com"))
@SecurityRequirement(
		name = "airlines_auth",
		scopes = {"write:reviews"}
		)
@Schema(
		name = "Airline Booking API",
		description = "APIs for booking and managing air flights"
		)
public class JAXRSApp extends Application {
	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<Object>();
		singletons.add(new AirlinesResource());
		singletons.add(new AvailabilityResource());
		singletons.add(new BookingResource());
		singletons.add(new ReviewResource());
		return singletons;
	}
	
	public static int getRandomNumber(int max, int min) {
		return (new Random()).nextInt(max - min + 1) + min;
	}
}
