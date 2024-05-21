package com.solvd.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String username;
    private String password;
    private String name;
    private String country;
    private String city;
    private String creditCardNumber;
    private String month;
    private String year;
}

/*
* public void addToCartByNameAndContinue(String name) {
    LOGGER.info("addToCartByProductName(" + name + ")");

    Actions actions = new Actions(getDriver());

    IntStream.range(0, products.size())
            .filter(i -> products.get(i).getText().contains(name))
            .forEach(i -> {
                WebElement product = products.get(i);
                LOGGER.info("Found product: " + product.getText());
                actions.moveToElement(product).perform();
                WebElement addToCartButton = addToCartButtons.get(i);
                click(addToCartButton);
                click(continueShoppingButton);
            });
}
*
* */