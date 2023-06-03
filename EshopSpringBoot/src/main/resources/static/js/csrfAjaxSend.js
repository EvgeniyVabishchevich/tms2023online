$(function () {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    $(document).on("ajaxSend", function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});