package pojo;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.Courier;

public class RandomCourier extends Courier {
    public static final String RANDOM_LOGIN = RandomStringUtils.random(8);
    public static  final String RANDOM_PASSWORD = RandomStringUtils.random(8);
    public static final String RANDOM_FIRST_NAME = RandomStringUtils.random(8);

    public Courier RandomCourier() {
        return new Courier(RANDOM_LOGIN, RANDOM_PASSWORD, RANDOM_FIRST_NAME);
    }
}
