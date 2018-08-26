import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

Contract.make {
    request {
        method 'POST'
        url '/bookings/'
        body([ 
        		   "customerId" : $(regex('[0-9]{10}')),
        		   "roomNumber" : $(regex('[0-9]{4}')),
			   "checkIn" : $(regex('[0-9]{2}-[0-9]{2}-[0-9]{4}')),
			   "checkOut" : $(regex('[0-9]{2}-[0-9]{2}-[0-9]{4}'))
		])
		headers { 
			header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
		}
    }
    response {
        status 201
		headers { 
			header(HttpHeaders.LOCATION, 'bookings/1')
		}
    }
}