$(function() {
    
    setTimeout(function() {
        $("#line1").fadeIn(2000);
    }, 1000);
    setTimeout(function() {
        $("#line2").fadeIn(2000);
    }, 4000);
    setTimeout(function() {
        $("#line3").fadeIn(2000);
    }, 7000);
    
    setTimeout(function() {
        $("#logo3").fadeIn(100);
    }, 8000);
    setTimeout(function() {
        $("#logo3").fadeOut(1);
    }, 8050);
    setTimeout(function() {
        $("#logo3").fadeIn(10);
    }, 8090);
    setTimeout(function() {
        $("#logo3").fadeOut(1);
    }, 8130);
    setTimeout(function() {
        $("#logo3").fadeIn(1);
    }, 8180);
    setTimeout(function() {
        $("#logo3").fadeOut(10);
    }, 8250);
    setTimeout(function() {
        $("#logo3").fadeIn(1);
    }, 8260);
    
    setTimeout(function() {
        $("#logo1").fadeIn(2000);
    }, 10000);
    setTimeout(function() {
        $("#logo2").fadeIn(5000);
    }, 11000);
    
    setTimeout(function() {
        $("#buttons").fadeIn(1000);
    }, 12000);
    
    setTimeout(function() {
        setInterval(update, 1);
    }, 8550);
    
    function update() {
        $("#logo3").css("opacity", ((Math.random() * 100) + 70)/100);
    }
    
})