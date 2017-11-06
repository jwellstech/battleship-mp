$(function() {
    
    var mines   = window.opener.mines;
    
    function mineHTML(id,x,y) {
        return "<div id='" + id + "' class='mineIconWrap' style='transform: translateX(" + x + "px) translateY(" + y + "px)'><div class='mineIcon'></div></div>";
    }
    
    for(var i=0;i<mines.length;i++) {
        $("#mainWrap").append(mineHTML("mineIndex" + i, (mines[i].x*0.1), (mines[i].y*0.1)));
    }
    
    setInterval(update, 1);
    
    function update() {
        
        var player  = window.opener.player;
        
        $("#playerIconWrap").css("transform", "translateX(" + (player.x*0.1) + "px) translateY(" + (player.y*0.1) + "px) rotate(" + (player.direction) + "deg)");
        
        
    }
    
})