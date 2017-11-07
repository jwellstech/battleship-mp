var mines           = window.opener.mines;
var minesInRange    = window.opener.minesInRange;
var player          = window.opener.player;

function mineHTML(id,x,y) {
    return "<div id='" + id + "' class='mineIconWrap' style='transform: translateX(" + x + "px) translateY(" + y + "px)'><div class='mineIcon'></div></div>";
}

for(var i=0;i<mines.length;i++) {
    $("#mainWrap").append(mineHTML("mineIndex" + i, (mines[i].x*0.1), (mines[i].y*0.1)));
}

function pingHTML(id,x,y) {
    return "<div class='pingRadius' id='pingIndex" + id + "' style='transform: translateX(" + x + "px) translateY(" + y + "px)'></div>";
}

function distanceBetween(pointA,pointB) {
    return Math.sqrt( Math.pow((pointA.x-pointB.x),2) + Math.pow((pointA.y-pointB.y),2) );
}

function animatePing() {
    minesInRange    = window.opener.minesInRange;
    
    console.log(minesInRange);
    
    $("#mainWrap").append(pingHTML("Player", player.x*0.1, player.y*0.1));
    $("#pingIndexPlayer").delay(1000).fadeOut(1000, function() { $(this).remove() });
    
    function pingRebound(i,delay) {
        setTimeout(function() {
            $("#mainWrap").append(pingHTML(i, mines[minesInRange[i]].x*0.1, mines[minesInRange[i]].y*0.1));
            $("#pingIndex" + i).delay(1000).fadeOut(1000, function() { $(this).remove() });
        }, delay);
    }
        
    for(var i=0; i<minesInRange.length; i++) {
        var delay = (distanceBetween(player, mines[minesInRange[i]])/1250)*1000;
        pingRebound(i, delay);
    }
}




setInterval(update, 1);

function update() {
    player  = window.opener.player;
    
    $("#playerIconWrap").css("transform", "translateX(" + (player.x*0.1) + "px) translateY(" + (player.y*0.1) + "px) rotate(" + (player.direction) + "deg)");
}
