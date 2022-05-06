$(function() {
    $('.delete-address').click(function() {
        var id = $(this).data('id');
        $.ajax({
            url: jsRoutes.controllers.HomeController.delete(id).url,
            type: "DELETE"
        }).done(function() {
            window.location.reload();
        });
    });

    $('.add-address').click(function() {
        var address = $("#new-address").val();
        $.ajax({
            url: jsRoutes.controllers.HomeController.add(address).url,
            type: "POST"
        }).done(function() {
            window.location.reload();
        });
    });

    $('.sync-balances').click(function() {
        $.ajax({
            url: jsRoutes.controllers.HomeController.sync().url,
            type: "GET"
        }).done(function() {
            window.location.reload();
        });
    });

    $('.list-transactions').click(function() {
        var id = $(this).data('id');
        var offset = $(this).data('offset');
        $.ajax({
            url: jsRoutes.controllers.HomeController.transactions(id, offset).url,
            type: "GET"
        }).done(function() {
            window.location.reload();
        });
    });
});