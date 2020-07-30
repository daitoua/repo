/*
$(function() {
        //var shopId = getQueryString('shopId');
        var url = "/o2o/productadmin/getproductcategory?shopId=1";

        getlist();

        function getlist(e) {
            $.ajax({
                    url: url,
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            handleList(data.list);
                        }


                    }


                }
            )
        }


        function handleList(data) {
            var html = '';
            data.map(function (item, index) {
                html += '<div class="row row-shop"><div class="col-40">'
                    + item.productCategoryName + '</div><div class="col-20">'
                    + item.priority
                    + '</div><div class="col-40">'
                    + '<a href="#">删除</a>' + '</div></div>';

            });
            $('.shop-wrap').html(html);
        }
    }
    );*/


$(function() {
    getlist();
    function getlist(e) {
        $.ajax({
            url : "/o2o/productadmin/getproductcategory?shopId=1",
            type : "get",
            dataType : "json",
            success : function(data) {
                if (data.success) {
                    handleList(data.list);

                }
            }
        });
    }


    function handleList(data) {
        var html = '';
        data.map(function(item, index) {
            html += '<div class="row row-shop"><div class="col-40">'
                + item.productCategoryName + '</div><div class="col-40">'
                + item.priority
                + '</div><div class="col-20">'
                + '<a href="#">删除</a>' + '</div></div>';

        });
        $('.shop-wrap').html(html);
    }


});
