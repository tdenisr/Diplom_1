import praktikum.IngredientType;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static Random random = new Random();

    public static IngredientType getRandomType() {
        IngredientType types[] = IngredientType.values();
        return types[random.nextInt(types.length)];
    }

    public static float getFloatFromReceipt(String string) {
        //Парсим цену из рецепта и преобразуем ее в float
        Pattern pattern = Pattern.compile("\\s+\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(string);
        matcher.find();
        String stringPrice = matcher.group(0);
        return Float.valueOf(stringPrice);
    }
}
