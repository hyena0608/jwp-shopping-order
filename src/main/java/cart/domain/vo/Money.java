package cart.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private final BigDecimal value;

    private Money(BigDecimal value) {
        this.value = value;
    }

    public static Money from(String value) {
        return new Money(new BigDecimal(value));
    }

    public static Money from(double value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public static Money from(int value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public static Money from(BigDecimal value) {
        return new Money(value);
    }

    public Money plus(Money other) {
        return new Money(value.add(other.value));
    }

    public Money minus(Money other) {
        return new Money(value.subtract(other.value));
    }

    public Money times(Quantity quantity) {
        BigDecimal multiplyMoney = value.multiply(BigDecimal.valueOf(quantity.getValue()));
        return new Money(multiplyMoney);
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
