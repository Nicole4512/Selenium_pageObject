package ru.ibs.framework;

import ru.ibs.framework.baseclass.BaseTest;

public class Test extends BaseTest {
    @org.junit.jupiter.api.Test
    public void m() {
        app.getStartPage().getDnsHeaderBlock()
                .closePlaceWindow()
                .fillFinderList("iPhone Xr")
                .findProduct("6.1\" Смартфон Apple iPhone Xr 64 ГБ желтый [6x2.5 ГГц, 3 Гб, 1 SIM, IPS, 1792x828, камера 12 Мп, NFC, 4G, GPS, 2942 мА*ч]")
                .getProduct("Со скидкой")
                .chooseMenu("Гарантия: 12 мес.")
                .ChooseGuarantee("+ 12 мес.")
                .changePrice("6.1\" Смартфон Apple iPhone Xr 64 ГБ желтый")
                .clickBuy()
                .getDnsHeaderBlock()
                .fillFinderProduct("Игра Detroit: Стать человеком (PS4)")
                .getProduct("Без скидки")
                .clickBuy()
                .getDnsHeaderBlock()
                .clickCart()
                .chooseMenu("Игра Detroit: Стать человеком (PS4)", "Удалить")
                .checkGuarantee("6.1\" Смартфон Apple iPhone Xr 64 ГБ желтый", "+ 12 мес.")

                .changeQuantity("6.1\" Смартфон Apple iPhone Xr 64 ГБ желтый", "Плюс")
                .changeQuantity("6.1\" Смартфон Apple iPhone Xr 64 ГБ желтый", "Плюс")
                .returnADeleted()
                .getDnsHeaderBlock()
                .checkPrice();


    }
}
