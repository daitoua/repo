$(function () {
    var cache;
    var cacheId = $(".cache").attr("name");


    window.onload = function () {
        //载入缓存的列表
        loadCache(cacheId);
    }

    window.onunload = function () {
        //可以通过needCache这个flag来控制是否需要缓存
        if (localStorage.needCache == 'true') {
            //离开页面时生成缓存
            createCache(cacheId);
        }
    }

    /* *
     * @brief 可对指定多个控件进行内容和位置的缓存
     * @param cacheId 缓存元素的id
     * @return null
     * */
    function createCache(cacheId) {
        //对内容进行缓存
        var list = [];
        var listController = $('.cache');
        $.each(listController, function (index, value, array) {
            list.push(value.innerHTML);
        })
        //对浏览到的位置进行缓存
        var top = [];
        var topController = $(".cache").find(".top");
        $.each(topController, function (index, value, array) {
            top.push(value.scrollTop);
        })
        //存入sessionstorage中
        sessionStorage.setItem(cacheId, JSON.stringify({
            list: list,
            top: top
        }));
    }

    /* *
     * @breif 可对指定多个控件加载缓存
     * @param 加载缓存的id
     * @return null
     * */
    function loadCache(cacheId) {
        //一定要放在整个js文件最前面
        cache = sessionStorage.getItem(cacheId);
        if (cache) {
            cache = JSON.parse(cache);
            //还原内容
            var listController = $('.cache');
            $.each(listController, function (index, value, array) {
                value.innerHTML = cache.list[index];
            })
            //还原位置
            var topController = $(".cache").find(".top");
            $.each(topController, function (index, value, array) {
                value.scrollTop = cache.top[index];
            })
        }
    }

})
