
$(function(){
	var data = new Array();
	data.push({
		data:d1,
        bars: {
            show: true,
            barWidth: 0.4,
            order: 1,
        }
    });
	
	
    //Display graph
    var bar = $.plot($(".bars"), data, {
		legend: true,
        color: "#2b2b2b"
	});
	
});
	

maruti = {
		// === Tooltip for flot charts === //
		flot_tooltip: function(x, y, contents) {
			
			$('<div id="tooltip">' + contents + '</div>').css( {
				top: y + 5,
				left: x + 5
			}).appendTo("body").fadeIn(200);
		}
}
