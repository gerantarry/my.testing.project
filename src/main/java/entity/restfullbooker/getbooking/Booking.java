package entity.restfullbooker.getbooking;

import lombok.Data;

@Data
public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;

    private BookingDates bookingdates;

    private String additionalneeds;



}
@Data
class BookingDates{
    private String checkin;
    private String checkout;
}
