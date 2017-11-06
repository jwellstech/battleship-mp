$(function() {
    
    setInterval(update, 1);
    
    function update() {
        var player = window.opener.player;
        $("#playerIconWrap").css("transform", "translateX(" + (player.x*0.1) + "px) translateY(" + (player.y*0.1) + "px) rotate(" + (player.direction) + "deg)");
    }
    
})