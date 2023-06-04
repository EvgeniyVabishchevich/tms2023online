function addProductId(productId) {
    $.ajax
    ({
        url: '/add-product',
        data: {productId: productId},
        type: 'post',
        success: function () {
            alert('product added')
        }
    });
}