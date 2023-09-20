import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{index}: Ingredient: {0} name: {1} price: {2}")
    public static Object[][] getIngredientData() {
        return new Object[][]{
                {IngredientType.FILLING, "salad", 10.10f},
                {IngredientType.FILLING, "onion", 0.10f},
                {IngredientType.SAUCE, "ketchup", 0.20f},
        };
    }

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        float expected = price;
        Assert.assertEquals("Цена отличается от ожидемой", expected, ingredient.getPrice(), 0.001);
    }

    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        String expected = name;
        Assert.assertEquals("Название ингредиента отличается от ожидаемого", expected, ingredient.getName());
    }

    @Test
    public void getTypeTest() {
        Ingredient ingredient = new Ingredient(type, name, price);
        IngredientType expected = type;
        Assert.assertEquals("Тип ингредиента отличается от ожидаемого", expected, ingredient.getType());
    }

}
