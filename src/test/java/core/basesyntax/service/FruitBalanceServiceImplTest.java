package core.basesyntax.service;

import static org.junit.Assert.assertEquals;

import core.basesyntax.dao.FruitDaoImpl;
import core.basesyntax.database.Storage;
import core.basesyntax.model.Fruit;
import core.basesyntax.model.OperationType;
import core.basesyntax.service.interfaces.FruitOperationService;
import org.junit.Before;
import org.junit.Test;

public class FruitBalanceServiceImplTest {
    private final OperationType type = OperationType.BALANCE;
    private final FruitOperationService fruitBalanceService = new FruitBalanceServiceImpl(
            new FruitDaoImpl());
    private Fruit fruit = new Fruit("apple");

    @Before
    public void setUp() throws Exception {
        Storage.getFruitDataBase().entrySet().clear();
    }

    @Test
    public void apply_putElements_Ok() {
        fruitBalanceService.apply(new TransactionDto(type, fruit, 23));
        int expected = 23;
        int actual = Storage.getFruitDataBase().get(fruit);
        assertEquals(expected, actual);
    }

    @Test
    public void apply_putNullFruit_Ok() {
        fruitBalanceService.apply(new TransactionDto(type, null, 1));
        int expected = 1;
        int actual = Storage.getFruitDataBase().get(null);
        assertEquals(expected, actual);
    }
}