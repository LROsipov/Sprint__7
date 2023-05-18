package pojo;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomOrder extends Order {
    private static final String RANDOM_FIRSTNAME = RandomStringUtils.random(8);
    private static  final String RANDOM_LASTNAME = RandomStringUtils.random(8);
    private static final String RANDOM_ADDRESS = RandomStringUtils.random(8);
    private static final int RANDOM_METRO_STATION =  ThreadLocalRandom.current().nextInt(1, 10);
    private static final String RANDOM_PHONE = RandomStringUtils.random(11);
    private static final int RANDOM_RENT_TIME= ThreadLocalRandom.current().nextInt(1, 7);
    private static final String RANDOM_DELIVERY_DATE = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    private static final String RANDOM_COMMENT = RandomStringUtils.random(10);
    private  String[] newColor;


    public RandomOrder(String[] newColour) {
        super(
                RANDOM_FIRSTNAME,
                RANDOM_LASTNAME,
                RANDOM_ADDRESS,
                RANDOM_METRO_STATION,
                RANDOM_PHONE,
                RANDOM_RENT_TIME,
                RANDOM_DELIVERY_DATE,
                RANDOM_COMMENT,
                newColour);
    }
}
