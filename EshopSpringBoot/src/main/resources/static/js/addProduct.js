function addProductId(productId) {
    $.ajax
    ({
        url: '/add-product',
        data: {productId: productId},
        type: 'post',
        success: alert('product added')
    });
}