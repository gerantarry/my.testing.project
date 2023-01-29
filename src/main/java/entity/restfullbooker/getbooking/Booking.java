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


    public String toJsonString(){
        return "{\n" +
                "  \"firstname\" : \""+firstname+"\",\n" +
                "  \"lastname\" : \""+ lastname +"\",\n" +
                "  \"totalprice\" : "+ totalprice +",\n" +
                "  \"depositpaid\" : "+ depositpaid +",\n" +
                "  \"bookingdates\" : {\n" +
                "    \"checkin\" : \""+bookingdates.getCheckin()+"\",\n" +
                "    \"checkout\" : \""+bookingdates.getCheckout()+"\"\n" +
                "  },\n" +
                "  \"additionalneeds\" : \""+ additionalneeds +"\"\n" +
                "}";
    }
}

@Data
class BookingDates {
    private String checkin;
    private String checkout;
}
