import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

public class BunTest {
    Faker faker = new Faker();
    String bunName;
    float bunPrice;

    @Before
    public void init() {
        bunName = faker.funnyName().name();
        bunPrice = Float.valueOf(faker.commerce().price());
    }

    @Test
    public void bunGetNameTest() {
        Bun bun = new Bun(bunName, bunPrice);
        Assert.assertEquals("Ожидали получить другое имя булочки", bunName, bun.getName());
    }

    @Test
    public void bunGetPrice() {
        Bun bun = new Bun(bunName, bunPrice);
        Assert.assertEquals("Ожидали другую цену булочки", bunPrice, bun.getPrice(), 0.0);
    }
}
