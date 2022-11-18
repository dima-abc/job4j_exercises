package ru.job4j.ood.srp.currency;

/**
 * 2. Джуниор
 * 2.5. ООДТема
 * 2.5.1. SRP
 * 1. Отчеты. [#850 #259413]
 * Реализация конвертора валют
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 18.11.2022
 */
public class InMemoryCurrencyConverter implements CurrencyConverter<Currency> {
    private static final int CURRENCIES_COUNT = Currency.values().length;
    private final double[][] conversationTable = new double[CURRENCIES_COUNT][CURRENCIES_COUNT];

    public InMemoryCurrencyConverter() {
        this.conversationTable[Currency.RUB.ordinal()][Currency.USD.ordinal()] = 0.0153;
        this.conversationTable[Currency.RUB.ordinal()][Currency.EUR.ordinal()] = 0.0158;
        this.conversationTable[Currency.USD.ordinal()][Currency.EUR.ordinal()] = 1.0317;
        this.conversationTable[Currency.USD.ordinal()][Currency.RUB.ordinal()] = 65D;
        this.conversationTable[Currency.EUR.ordinal()][Currency.USD.ordinal()] = 0.9692;
        this.conversationTable[Currency.EUR.ordinal()][Currency.RUB.ordinal()] = 63D;
    }

    @Override
    public double convert(Currency source, double sourceValue, Currency target) {
        return sourceValue * conversationTable[source.ordinal()][target.ordinal()];
    }
}
