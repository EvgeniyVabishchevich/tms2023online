package by.tms.eshopspringboot.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Constants {

    @UtilityClass
    public class MappingPath {
        public final String ADMIN = "admin/admin";
        public final String BUY = "buy";
        public final String CART = "cart";
        public final String CATEGORIES = "categories";
        public final String ERROR404 = "error404";
        public final String LOGIN = "login";
        public final String REGISTER = "register";
        public final String PRODUCTS = "products";
        public final String USER = "user";
        public final String SEARCH_RESULT = "searchResult";
        public final String SEARCH = "search";
    }

    @UtilityClass
    public class Attributes {
        public final String PRODUCTS_MAP = "productsMap";
        public final String TOTAL_PRICE = "totalPrice";
        public final String CATEGORIES = "categories";
        public final String PRODUCTS = "products";
        public final String SELECTED_CATEGORY = "selectedCategory";
        public final String SEARCH_REQUEST = "searchRequest";
        public final String MIN_PRICE = "minPrice";
        public final String MAX_PRICE = "maxPrice";
    }

    @UtilityClass
    public class RequestParameters {
        public final String PASSWORD_REPEAT = "passwordRepeat";
        public final String PRODUCT_ID = "productId";
        public final String CATEGORIES = "categories";
        public final String IMAGE = "image";
        public final String DESCRIPTION = "description";
        public final String PRICE = "price";
        public final String NAME = "name";
        public final String SEARCH_REQUEST = "searchRequest";
        public final String MIN_PRICE = "minPrice";
        public final String MAX_PRICE = "maxPrice";
        public final String CATEGORY = "category";
        public final String SELECTED_CATEGORY = "selectedCategory";
        public final String PRODUCTS = "products";
    }
}
