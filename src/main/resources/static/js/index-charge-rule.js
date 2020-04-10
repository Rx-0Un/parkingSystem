$(function () {
    $.ajax({
        type: "get",
        url: "/index-charge-rule/selectBasicRule",
        dataType: "html",
        success: function (data) {
            $(".basic").html(data);
        },
        error: function (date) {

        }
    })
});
$('#test').bind('click', function () {
    console.log("!!!")
    let r=0;
    const rule1 = $('input:radio[id="customControlValidation1"]:checked').val()
    const rule2 = $('input:radio[id="customControlValidation2"]:checked').val()
    const rule3 = $('input:radio[id="customControlValidation3"]:checked').val()
    if (rule1 == 'on')
        r=1
    if (rule2 == 'on')
        r=2
    if (rule3 == 'on')
        r=3
    const interim_rule = $('#customControlAutosizing1').is(':checked')
    const plate_type = $('#plate_type ').val()
    const car_type = $('#car_type   ').val()
    const date = $('#Date       ').val()
    const time_type = $('#time_type  ').val()
    const searchNum = $('#searchNum  ').val()
    // alert(r);
    // alert(interim_rule)
    // alert(plate_type)
    // alert(car_type)
    // alert(Date)
    // alert(time_type)
    // alert(searchNum)
    const Data = {
        "rule":r,
        "interim_rule": interim_rule,
        "plate_type": plate_type,
        "car_type": car_type,
        "Date": date,
        "time_type": time_type,
        "searchNum": searchNum,
        "random": new Date()
    }
    $.ajax({
        type: "post",
        url: "/index-charge-rule/parkingTest",
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(Data),
        dataType: "text",
        success: function (data) {
            $(".TestResult").html(data);
        },
        error: function (date) {
            alert("失败")
        }
    })
});
