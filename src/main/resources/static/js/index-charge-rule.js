$('#test').bind('click', function () {
    const rule = $("input[name='radio-stacked']").filter(':checked').val();
    const interim_rule = $('#customControlAutosizing1').is(':checked')
    const fix_rule = $('#customControlAutosizing2').is(':checked')
    const plate_type = $('#plate_type ').val()
    const car_type = $('#car_type').val()
    const date = $('#Date').val()
    const time_type = $('#time_type').val()
    const searchNum = $('#searchNum').val()
    const zone_type = $('#zone_type').val()
    const Data = {
        "rule": rule,
        "interim_rule": interim_rule,
        "fix_rule":fix_rule,
        "plate_type": plate_type,
        "car_type": car_type,
        "Date": date,
        "time_type": time_type,
        "searchNum": searchNum,
        "zone_type": zone_type,
        "page":1,
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

