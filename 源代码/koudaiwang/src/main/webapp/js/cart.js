$(function () {
    $(".num .sub").click(function () {
        var c = parseInt($(this).siblings("span").text());
        if (c <= 1) {
            $(this).attr("disabled", "disabled")
        } else {
            c--;
            $(this).siblings("span").text(c);
            if($(this).siblings("span").attr('datasrc')) {

                var url = "collectnumadd?count=" + c + "&itemid=" + $(this).siblings("span").attr('datasrc');
                $.get(url, function () {
                });
            }

            var d = $(this).parents(".number").prev().text().substring(1);
            $(this).parents(".th").find(".sAll").text("￥" + (c * d).toFixed(2));
            a();
            b()
        }
    });
    $(".num .add").click(function () {
        var c = parseInt($(this).siblings("span").text());
        var num=$(this).siblings("span").attr('datasrc1');
        if (c >= num) {
            confirm("超出库存数量！")
        } else {
            c++;
            $(this).siblings("span").text(c);

            if($(this).siblings("span").attr('datasrc')) {
                var url = "collectnumadd?count=" + c + "&itemid=" + $(this).siblings("span").attr('datasrc');
                $.get(url, function () {
                });
            }
            var d = $(this).parents(".number").prev().text().substring(1);
            $(this).parents(".th").find(".sAll").text("￥" + (c * d).toFixed(2));
            a();
            b()
        }
    });

    function a() {
        var c = 0;
        var d = $(".th input[type='checkbox']:checked").length;
        if (d == 0) {
            $("#all").text("￥" + parseFloat(0).toFixed(2))
        } else {
            $(".th input[type='checkbox']:checked").each(function () {
                var e = $(this).parents(".pro").siblings(".sAll").text().substring(1);
                c += parseFloat(e);
                $("#all").text("￥" + c.toFixed(2))
            })
        }
    }

    function b() {
        var e = 0;
        var c = $(".th input[type='checkbox']:checked").parents(".th").find(".num span");
        var d = c.length;
        if (d == 0) {
            $("#sl").text(0)
        } else {
            c.each(function () {
                e += parseInt($(this).text());
                $("#sl").text(e)
            })
        }
        if ($("#sl").text() > 0) {
            $(".count").css("background", "#c10000")
        } else {
            $(".count").css("background", "#8e8e8e")
        }
    }

    $("input[type='checkbox']").on("click", function () {
        var f = $(this).is(":checked");
        var e = $(this).hasClass("checkAll");
        if (f) {
            if (e) {
                $("input[type='checkbox']").each(function () {
                    this.checked = true
                });
                b();
                a()
            } else {
                $(this).checked = true;
                var c = $("input[type='checkbox']:checked").length;
                var d = $("input").length - 1;
                if (c == d) {
                    $("input[type='checkbox']").each(function () {
                        this.checked = true
                    })
                }
                b();
                a()
            }
        } else {
            if (e) {
                $("input[type='checkbox']").each(function () {
                    this.checked = false
                });
                b();
                a()
            } else {
                $(this).checked = false;
                var c = $(".th input[type='checkbox']:checked").length;
                var d = $("input").length - 1;
                if (c < d) {
                    $(".checkAll").attr("checked", false)
                }
                b();
                a()
            }
        }
    });
    $(".btns .cart").click(function () {
        if ($(".categ p").hasClass("on")) {
            var c = parseInt($(".num span").text());
            var d = parseInt($(".goCart span").text());
            $(".goCart span").text(c + d)
        }
    });



    $(".cancel").click(function () {
        $(".mask").hide();
        $(".tipDel").hide()
    })
});