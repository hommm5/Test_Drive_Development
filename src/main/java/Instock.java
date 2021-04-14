import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private Map<String, Product> inStock = new LinkedHashMap<>();

    @Override
    public int getCount() {
        return inStock.size();
    }

    @Override
    public boolean contains(Product product) {
        return inStock.containsKey(product.getLabel());
    }

    @Override
    public void add(Product product) {
        inStock.put(product.getLabel(), product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        if (!inStock.containsKey(product)) {
            throw new IllegalArgumentException();
        }
        inStock.get(product).setQuantity(quantity);

    }

    @Override
    public Product find(int index) {
        Product result = null;

        if (index < 0 || index >= getCount()) {
            throw new IndexOutOfBoundsException();
        }
        int counter = 0;

        for (Map.Entry<String, Product> entry : inStock.entrySet()) {

            if (counter == index) {

                result = entry.getValue();
            }
            counter++;

        }
        return result;
    }

    @Override
    public Product findByLabel(String label) {
        for (Map.Entry<String, Product> entry : inStock.entrySet()) {
            if (entry.getKey().equals(label)) {
                return entry.getValue();
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        Iterable<Product> products = null;

        if (inStock.size() >= count) {
            products = inStock
                    .values()
                    .stream()
                    .sorted(Product::compareTo)
                    .limit(count).collect(Collectors.toList());
            System.out.println();
        }

        return products == null ? new ArrayList<>() : products;
    }

    @Override
    public Iterable<Product> findAllInRange(double low, double high) {
        return inStock.values()
                .stream()
                .filter(product -> product.getPrice() > low && product.getPrice() <= high)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {

        return inStock.values()
                .stream()
                .filter(product -> product.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count >= inStock.size()) {
            throw new IllegalArgumentException();
        }
        List<Product> collection = inStock.values()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(count)
                .collect(Collectors.toList());

        return collection;
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return inStock.values()
                .stream()
                .filter(product -> product.getQuantity()==quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return inStock.values()
                .stream()
                .iterator();
    }
}
