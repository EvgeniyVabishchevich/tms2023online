package by.tms.eshopspringboot.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Constants {

    @UtilityClass
    public static class MappingPath {
        public static final String ADMIN = "admin/admin";
        public static final String BUY = "buy";
        public static final String CART = "cart";
        public static final String CATEGORIES_PATH = "categories";
        public static final String ERROR404 = "error404";
        public static final String LOGIN = "login";
        public static final String REGISTER = "register";
        public static final String PRODUCTS = "products";
        public static final String USER = "user";
        public static final String SEARCH = "search";
    }

    @UtilityClass
    public static class Attributes {
        public static final String PRODUCTS_MAP = "productsMap";
        public static final String TOTAL_PRICE = "totalPrice";
        public static final String CATEGORIES = "categories";
        public static final String PRODUCTS = "products";
        public static final String SELECTED_CATEGORY = "selectedCategory";
        public static final String SEARCH_REQUEST = "searchRequest";
        public static final String MIN_PRICE = "minPrice";
        public static final String MAX_PRICE = "maxPrice";
    }

    @UtilityClass
    public static class RequestParameters {
        public static final String PASSWORD_REPEAT = "passwordRepeat";
        public static final String PRODUCT_ID = "productId";
        public static final String CATEGORIES = "categories";
        public static final String IMAGE = "image";
        public static final String DESCRIPTION = "description";
        public static final String PRICE = "price";
        public static final String NAME = "name";
        public static final String SEARCH_REQUEST = "searchRequest";
        public static final String MIN_PRICE = "minPrice";
        public static final String MAX_PRICE = "maxPrice";
        public static final String CATEGORY = "category";
        public static final String SELECTED_CATEGORY = "selectedCategory";
        public static final String PRODUCTS = "products";
    }
}
