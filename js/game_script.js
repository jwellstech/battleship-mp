$(function() {
    
    $("#openSpectator").click(function() {
        spectatorWindow = window.open("spectator.html");
    })
    
    var key = {
        up:     false,
        down:   false,
        left:   false,
        right:  false
    }
    
    var settings = {
        turnSpeed: 0.0002,
        moveSpeed: 0.0005
    }
    
    var keyListener = new window.keypress.Listener();
    
    keyListener.register_combo({
        keys:       "up",
        on_keydown: function() {key.up = true},
        on_keyup:   function() {key.up = false}
    })
    
    keyListener.register_combo({
        keys:       "left",
        on_keydown: function() {key.left = true},
        on_keyup:   function() {key.left = false}
    })
    
    keyListener.register_combo({
        keys:       "right",
        on_keydown: function() {key.right = true},
        on_keyup:   function() {key.right = false}
    })
    
    player = {
        x:              0,
        y:              0,
        direction:      0,
        moveMomentum:   0,
        turnMomentum:   0
    }

    setInterval(update, 1)
    
    function update() {
        
        /*KEY PRESS EVENTS*/
        if(key.right) {
            player.turnMomentum++;
        } else if(!key.right) {
            player.turnMomentum--;
        }

        if(key.left) {
            player.turnMomentum--;
        } else if(!key.right) {
            player.turnMomentum++;
        }
        
        if(!key.right && !key.left || key.right && key.left) {
            if(player.turnMomentum <= -1) {
                player.turnMomentum++;
            } else if(player.turnMomentum >= 1) {
                player.turnMomentum--;
            }
        }
        
        if(key.up) {
            player.moveMomentum++;
        } else if(!key.up) {
            player.moveMomentum = player.moveMomentum - 0.5;
        }
        
        /*DATA CLAMPING*/
        if(player.direction > 360) {
            player.direction = 0;
        } else if(player.direction < 0) {
            player.direction = 360;
        }
        
        if(player.turnMomentum > 500) {
            player.turnMomentum = 500;
        } else if(player.turnMomentum < -500) {
            player.turnMomentum = -500;
        }
        
        if(player.moveMomentum < 0) {
            player.moveMomentum = 0;
        } else if(player.moveMomentum > 500) {
            player.moveMomentum = 500;
        }
        
        /*VISUAL UI UPDATE and GENERAL LOGIC*/
        player.direction = player.direction + player.turnMomentum*settings.turnSpeed;
        
        $("#subHeading").css("transform", "rotate(" + player.direction + "deg)");
        
        var newX = Math.cos((player.direction-90)*Math.PI/180) * player.moveMomentum*settings.moveSpeed + player.x;
        var newY = Math.sin((player.direction-90)*Math.PI/180) * player.moveMomentum*settings.moveSpeed + player.y;
        
        player.x = newX;
        player.y = newY;
        
        $("#dotMatrix").css("background-position", (player.x*-1) + "px " + (player.y*-1) + "px");
        $("#dotMatrixRed").css("background-position", (player.x*-1) + "px " + (player.y*-1) + "px");
        $("#dotMatrixBlue").css("background-position", (player.x*-1) + "px " + (player.y*-1) + "px");
        
        /*UPDATE DEBUG INFO*/
        $("#playerX").text(     "player.x            = " + player.x);
        $("#playerY").text(     "player.y            = " + player.y);
        $("#playerDir").text(   "player.direction    = " + player.direction);
        $("#playerMM").text(    "player.moveMomentum = " + player.moveMomentum);
        $("#playerTM").text(    "player.turnMomentum = " + player.turnMomentum);
    }
    
    
    
})