import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import org.junit.Assert;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Faker faker = new Faker();
    @Mock
    Bun bun;
    @Mock
    Ingredient mockIngredient;
    IngredientType ingredientType;

    Burger burgerWithIngredients = new Burger();

    @Before
    public void init(){
        for (int i = 0; i < faker.number().numberBetween(1, 50); i++) {
            ingredientType = Utils.getRandomType();
            Ingredient ingredient = new Ingredient(ingredientType, faker.food().ingredient(), Float.valueOf(faker.commerce().price()));
            burgerWithIngredients.addIngredient(ingredient);
        }
    }
    @Test
    public void setBunsTest(){
        String bunName = faker.funnyName().name();
        Burger burger = new Burger();
        Mockito.when(bun.getName()).thenReturn(bunName);
        burger.setBuns(bun);
        Assert.assertEquals("Ожидалось, что будет использована булка " + bunName, bunName, burger.bun.getName());
    }

    @Test
    public void addIngredientTest(){
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient);
        Assert.assertTrue("Ингредиент не добавился в бургер",burger.ingredients.contains(mockIngredient));
    }

    @Test
    public void removeIngredientTest(){
        int sizeBefore= burgerWithIngredients.ingredients.size();
        burgerWithIngredients.removeIngredient(sizeBefore-1);
        Assert.assertTrue("Ожидалось, что после удаления количество ингредиентов станет меньше",
                burgerWithIngredients.ingredients.size() < sizeBefore);
    }
    @Test
    public void moveIngredientTest(){
        int maxIndex = burgerWithIngredients.ingredients.size() -1;
        int index = faker.number().numberBetween(0, maxIndex);
        int newIndex = faker.number().numberBetween(0, maxIndex);
        System.out.println("index: " + index + " newIndex " + newIndex);
        Ingredient ingredientBefore = burgerWithIngredients.ingredients.get(index);
        burgerWithIngredients.moveIngredient(index, newIndex);
        Assert.assertEquals("Ингредиент не был перемещен на новую позицию",ingredientBefore, burgerWithIngredients.ingredients.get(newIndex));
        Assert.assertTrue("Количество ингредиентов после перемещения изменилось",burgerWithIngredients.ingredients.size() == maxIndex+1);
    }

    @Test
    public void getPriceTest(){
        float bunPrice = Float.valueOf(faker.commerce().price());
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        float ingredientsPrice = 0;
        Burger burger = new Burger();
        burger.setBuns(bun);
        for(Ingredient ingredient: burgerWithIngredients.ingredients){
            burger.addIngredient(ingredient);
            ingredientsPrice += ingredient.price;
        }
        float expectedPrice = bunPrice * 2 + ingredientsPrice;
        Assert.assertEquals("Цена бургера не соответсвует ожиданиям", expectedPrice, burger.getPrice(), 0.001);
    }
    @Test
    public void getReceiptTest(){
        float bunPrice = Float.valueOf(faker.commerce().price());
        String bunName = faker.food().fruit();
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(bun.getName()).thenReturn(bunName);
        float ingredientsPrice = 0;
        Burger burger = new Burger();
        burger.setBuns(bun);
        for(Ingredient ingredient: burgerWithIngredients.ingredients){
            burger.addIngredient(ingredient);
            ingredientsPrice += ingredient.price;
        }
        float expectedPrice =  bunPrice * 2 + ingredientsPrice;
        float actualPrice = Utils.getFloatFromReceipt(burger.getReceipt());
        Assert.assertEquals("Цена бургера не соответсвует ожиданиям", expectedPrice, actualPrice, 0.001);
        for(Ingredient ingredient: burgerWithIngredients.ingredients) {
            Assert.assertTrue(burger.getReceipt().contains(ingredient.name));
            Assert.assertTrue(burger.getReceipt().contains(bunName));
            Assert.assertTrue(burger.getReceipt().contains(ingredient.type.name().toLowerCase()));
        }
    }
}
