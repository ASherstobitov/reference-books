package com.company.referencebooks.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

public enum Condition implements EnumClass<String> {

    one("1.	Новый – заполняется при создании доку-мента"),
    two ("2.	На согласовании – на согласовании у ру-ководителя или согласующих"),
    three ("3.	На подписании – на подписании у подпи-санта"),
    four ("4.	На доработке"),
    five ("5.	Зарегистрирован");

    private String id;

    Condition(String value) {
        this.id = value;
    }


    @Override
    public String getId() {
        return id;
    }

    public static String fromId(String item) {
        for (Condition at : Condition.values()) {
            if (at.equals(item)) {
                return at.getId();
            }
        }
        return null;
    }


}
