package io.github.nathancorghi.mysql.query;

public class MassasQuery {

    public static String findByProductName() {
        return "select *    "
            +  "from        "
            +  "     massas ";
    }

    public static String updateProductColor() {
        return "update massas           "
            +  "   set color = ?        "
            +  " where name_product = ? ";
    }
}
