package ru.mentee.power.exceptions.multilevel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderProcessor {
    private static final Logger LOG = Logger.getLogger(OrderProcessor.class.getName());

    public List<ProductItem> parseProducts(List<String> lines) throws DataFormatException {
        List<ProductItem> products = new ArrayList<>();
        int lineNumber = 0;
        for (String raw : lines) {
            lineNumber++;
            if (raw == null || raw.trim().isEmpty()) {
                // пропускаем пустые строки
                continue;
            }
            String[] parts = raw.split(";");
            if (parts.length != 3) {
                String msg = "Неверный формат строки. Ожидалось 3 поля: " + raw;
                LOG.warning("Line " + lineNumber + ": " + msg);
                throw new InvalidProductFormatException(msg, raw, lineNumber);
            }

            String name = parts[0].trim();
            String qtyStr = parts[1].trim();
            String priceStr = parts[2].trim();

            try {
                int qty = Integer.parseInt(qtyStr);
                double price = Double.parseDouble(priceStr);
                if (qty < 0) {
                    throw new InvalidPriceFormatException("Количество не может быть отрицательным: " + qtyStr, qtyStr, lineNumber);
                }
                if (price < 0) {
                    throw new InvalidPriceFormatException("Цена не может быть отрицательной: " + priceStr, priceStr, lineNumber);
                }
                products.add(new ProductItem(name, qty, price));
            } catch (NumberFormatException nfe) {
                String msg = "Ошибка разбора числового поля в строке: " + raw;
                LOG.warning("Line " + lineNumber + ": " + msg + " - " + nfe.getMessage());
                throw new InvalidPriceFormatException(msg, (nfe.getMessage() == null ? "" : nfe.getMessage()), lineNumber, nfe);
            }
        }
        return products;
    }

    public double calculateOrderTotal(List<ProductItem> products) throws OrderException {
        if (products == null || products.isEmpty()) {
            throw new EmptyOrderException("Заказ пустой");
        }
        double total = 0.0;
        for (ProductItem p : products) {
            try {
                double itemTotal = p.totalPrice();
                if (Double.isNaN(itemTotal) || Double.isInfinite(itemTotal)) {
                    throw new PriceCalculationException("Некорректная арифметика для товара: " + p.getName(), p);
                }
                total += itemTotal;
            } catch (ArithmeticException ae) {
                LOG.severe("Ошибка при расчете стоимости для товара: " + p + " - " + ae.getMessage());
                throw new PriceCalculationException("Ошибка при расчете стоимости для товара: " + p.getName(), p, ae);
            }
        }
        return total;
    }
}

