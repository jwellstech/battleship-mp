    
    $("#openSpectator").click(function() {
        spectatorWindow = window.open("spectator.html");
    })
    
    var subIcon
    
    var key = {
        up:     false,
        down:   false,
        left:   false,
        right:  false
    }
    
    var settings = {
        turnSpeed: 0.0002,
        moveSpeed: 0.0005,
        mineCount: 30,
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
    
    mines = [];
    
    for(var i=0; i<settings.mineCount; i++) {
        /*Places mines at a random distance away and at a random angle from 0,0*/
        var distance = (Math.random()*2500)+800;
        var angle = Math.random()*360;
        
        var mineX = Math.cos((angle-90)*Math.PI/180) * distance;
        var mineY = Math.sin((angle-90)*Math.PI/180) * distance;
        
        mines.push({x: mineX, y:mineY});        
    }
    
    function illuminateSector(sectorID) {
        for(var i=0;i<2;i++) {
            $("#sector" + sectorID).fadeIn(Math.random()*50).delay(Math.random()*100).fadeOut(Math.random()*50).delay(Math.random()*100);
        }
        $("#sector" + sectorID).fadeIn(Math.random()*50).delay(2000).fadeOut(5000);
    }
    
    function distanceBetween(pointA,pointB) {
        return Math.sqrt( Math.pow((pointA.x-pointB.x),2) + Math.pow((pointA.y-pointB.y),2) );
    }
    
    minesInRange = [];
    
    $("#testButton1").click(function() {
        minesInRange = [];
        for(var i=0;i<mines.length;i++) {
            if( distanceBetween(player,mines[i]) <= 1250 ) {
                minesInRange.push(i);
            }
        }
        spectatorWindow.animatePing();
    })
    
    function showSector(angle) {
        
    }

    var canvas = new createjs.Stage("mainCanvas");

    var subIcon = new createjs.Bitmap("../img/sub.png");
        subIcon.regX =  64;
        subIcon.regY =  64;
        subIcon.x =     1280/2;
        subIcon.y =     720/2;
    
    var dots = new createjs.Bitmap("../img/dots.png");
        dots.regX = 500;
        dots.regY = 500;
        dots.x =    1280/2;
        dots.y =    720/2;

    var circles = new createjs.Bitmap("../img/main.png");
        circles.alpha = 0.35;

    var vignette = new createjs.Bitmap("../img/vignette.png");
    var overlay = new createjs.Bitmap("../img/grunge_overlay.png");

    canvas.addChild(dots);
    canvas.addChild(subIcon);
    canvas.addChild(vignette);
    canvas.addChild(circles);
    canvas.addChild(overlay);

    setInterval(update, 1)
    
    function update() {
        
        
        subIcon.rotation = player.direction;
        dots.x = 1280/2 + (player.x%100 * -1);
        dots.y = 720/2 + (player.y%100 * -1);
        canvas.update();
        
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
        
        //$("#subIcon").css("transform", "rotate(" + player.direction + "deg)");
        
        var newX = Math.cos((player.direction-90)*Math.PI/180) * player.moveMomentum*settings.moveSpeed + player.x;
        var newY = Math.sin((player.direction-90)*Math.PI/180) * player.moveMomentum*settings.moveSpeed + player.y;
        
        player.x = newX;
        player.y = newY;
        
        //$("#dotMatrix").css(    "background-position", (player.x*-1) + "px " + (player.y*-1) + "px");
        //$("#dotMatrixRed").css( "background-position", (player.x*-1) + "px " + (player.y*-1) + "px");
        //$("#dotMatrixBlue").css("background-position", (player.x*-1) + "px " + (player.y*-1) + "px");
        
        /*UPDATE DEBUG INFO*/
        $("#playerX").text(     "player.x            = " + player.x);
        $("#playerY").text(     "player.y            = " + player.y);
        $("#playerDir").text(   "player.direction    = " + player.direction);
        $("#playerMM").text(    "player.moveMomentum = " + player.moveMomentum);
        $("#playerTM").text(    "player.turnMomentum = " + player.turnMomentum);
    }
    
    
    
