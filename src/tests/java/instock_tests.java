import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class instock_tests {
    private Instock instock;
    private static final int INITIAL_QUANTITY = 14;
    private static final Product PRODUCT = new Product("test", 3.14, INITIAL_QUANTITY);
    private static final String[] productLabels = {
            "B",
            "A",
            "C",
            "D",
    };


    @Before
    public void setUp() {
        instock = new Instock();
    }

    @Test
    public void getCountReturnNumberOfProducts() {
        Product testProduct = new Product("secondTest", 3.14, 55);

        instock.add(PRODUCT);
        instock.add(testProduct);

        assertEquals(2, instock.getCount());
    }

    @Test
    public void addShouldAddAdditionalProductAndIncreaseTheCount() {

        instock.add(PRODUCT);

        assertEquals(1, instock.getCount());
    }

    @Test
    public void addShouldAddAdditionalProductThatIsContainedInInstock() {
        instock.add(PRODUCT);
        assertTrue(instock.contains(PRODUCT));
    }

    @Test
    public void containsShouldReturnTrueIfProductIsFoundWithinInstock() {
        instock.add(PRODUCT);
        assertTrue(instock.contains(PRODUCT));

    }

    @Test
    public void containsShouldReturnFalseIfProductIsNotFoundWithinInstock() {
        assertFalse(instock.contains(PRODUCT));
    }

    @Test
    public void findShouldReturnTheNProduct() {
        instock.add(PRODUCT);

        Product testProduct = new Product("secondTest", 3.14, 55);

        instock.add(testProduct);

        Product foundProduct = instock.find(1);

        assertEquals(testProduct, foundProduct);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findShouldReturnExceptionIfIndexIsNotPresent() {
        int count = instock.getCount(); //Gives the number of Products available.

        instock.find(count); //Count != index.
    }

    @Test
    public void changeQuantityShouldChangeTheQuantityOfaGivenProduct() {
        instock.add(PRODUCT);

        instock.changeQuantity(PRODUCT.getLabel(), INITIAL_QUANTITY + 15);

        int quantityChanged = PRODUCT.getQuantity();

        assertEquals(quantityChanged, INITIAL_QUANTITY + 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeQuantityShouldThrowExceptionIfProductIsNotPresent() {
        instock.add(PRODUCT);

        instock.changeQuantity("Missing", 15);
    }

    @Test
    public void findByLabelReturnsProductWithTheSameLabel() {
        instock.add(PRODUCT);

        fillWithMultipleInstock();

        Product findByLabel = instock.findByLabel("D");

        assertEquals("D", findByLabel.label);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByLabelReturnExceptionIfProductNotFound() {
        instock.add(PRODUCT);

        Product testProduct = new Product("secondTest", 3.14, 55);

        instock.add(testProduct);

        instock.findByLabel("Non existing");
    }

    @Test
    public void findFistByAlphabeticalOrderReturnsACollectionWithTheSameSize() {
        fillWithMultipleInstock();

        Iterable<Product> order = instock.findFirstByAlphabeticalOrder(3);

        int count = 0;

        for (Product product : order) {
            count++;
        }

        assertEquals(3, count);
    }

    @Test
    public void findFirstByAlphabeticalOrderReturnsEmptyCollectionIfCountOutOfRange() {
        fillWithMultipleInstock();

        Iterable<Product> first = instock.findFirstByAlphabeticalOrder(instock.getCount() + 1);

        int count = 0;

        for (Product product : first) {
            count++;
        }

        assertEquals(0, count);
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnCollectionInAlphabeticalOrder() {
        fillWithMultipleInstock();

        Iterable<Product> first = instock.findFirstByAlphabeticalOrder(2);

        List<String> collect = Arrays.stream(productLabels).sorted().collect(Collectors.toList());

        int index = 0;

        for (Product product : first) {
            assertEquals(collect.get(index++), product.getLabel());
        }


    }

    @Test
    public void findAllInThePriceRangeShouldReturnCollectionInPriceRange() {
        fillWithMultipleInstock();

        double lowerBound = 0.21;
        double upperBound = 9.15;

        Iterable<Product> allInRange = instock.findAllInRange(lowerBound, upperBound);

        for (Product product : allInRange) {
            assertTrue(product.getPrice() > lowerBound && product.getPrice() <= upperBound);
        }
    }

    @Test
    public void findAllInThePriceRangeShouldReturnCollectionInPriceRangeOrderedInDescendingOrder() {
        fillWithMultipleInstock();

        double lowerBound = 0.21;
        double upperBound = 9.15;

        Iterable<Product> allInRange = instock.findAllInRange(lowerBound, upperBound);

        double bigger = Double.MAX_VALUE;

        for (Product product : allInRange) {

            assertTrue(product.getPrice() > lowerBound && product.getPrice() <= upperBound);

            assertTrue(bigger > product.getPrice());
            bigger = product.getPrice();

        }
    }

    @Test
    public void findAllInThePriceRangeShouldReturnEmptyCollectionIfNotInRange() {
        fillWithMultipleInstock();

        double lowerBound = 10.21;
        double upperBound = 55.15;

        Iterable<Product> allInRange = instock.findAllInRange(lowerBound, upperBound);

        int count = 0;

        for (Product product : allInRange) {
            count++;
        }

        assertEquals(0, count);

    }

    @Test
    public void findAllByPriceReturnsAllProductsWithTheSamePrice() {
        fillWithMultipleInstock();

        double number = 9.0;

        Iterable<Product> allByPrice = instock.findAllByPrice(number);

        for (Product product : allByPrice) {
            assertEquals(9.0, product.getPrice(), 0.0);
        }

    }

    @Test
    public void findAllByPriceReturnsEmptyCollectionIfNoProductWithSamePrice() {
        fillWithMultipleInstock();

        double number = 1212.0;

        Iterable<Product> allByPrice = instock.findAllByPrice(number);

        int count = 0;

        for (Product product : allByPrice) {
            count++;
        }

        assertEquals(0, count);
    }

    @Test
    public void findFirstMostExpensiveProductsReturnsNumberOfFirstMostExpensiveProducts() {
        fillWithMultipleInstock();

        int number = 3;

        Iterable<Product> mostExpensiveProducts = instock.findFirstMostExpensiveProducts(number);

        double bigger = Double.MAX_VALUE;

        int count = 0;

        for (Product product : mostExpensiveProducts) {
            assertTrue(bigger > product.getPrice());
            bigger = product.getPrice();
            count++;
        }

        assertEquals(number, count);

    }

    @Test(expected = IllegalArgumentException.class)
    public void findFirstMostExpensiveProductsThrowsExceptionIfTheirCountIsBiggerThanSize() {
        fillWithMultipleInstock();

        int number = 5;

        Iterable<Product> mostExpensiveProducts = instock.findFirstMostExpensiveProducts(number);

    }
    @Test
    public void findByQuantityReturnsEmptyCollectionWhenNoSuchQuantityInStock(){
        fillWithMultipleInstock();

        int quantity = 1000;

        Iterable<Product> allByQuantity = instock.findAllByQuantity(quantity);

        int count = 0;

        for (Product product : allByQuantity) {
            count++;
        }
        assertEquals(0, count);
    }

    @Test
    public void findAllByQuantityReturnsProductsWithSameQuantity(){
        fillWithMultipleInstock();

        int quantity = 14;

        Iterable<Product> allByQuantity = instock.findAllByQuantity(quantity);

        for (Product product : allByQuantity) {
            assertEquals(quantity, product.getQuantity());
        }

    }
    @Test
    public void iteratorReturnsAllProductsInStock(){
        fillWithMultipleInstock();

        Iterator<Product> iterator = instock.iterator();

        for (Product product : instock) {
            Product p = iterator.next();
            assertEquals(product, p);
        }

    }

    private void fillWithMultipleInstock() {
        for (int i = 0; i < productLabels.length; i++) {
            instock.add(new Product(productLabels[i], i * i, INITIAL_QUANTITY + i));
        }
    }


}
