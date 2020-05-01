document.getElementById("test").onmousedown = function(event) {
    if (event.which == 3) {
        alert("right clicked!");
    }
}

function testAlert(){
	alert("testing complete");
}

function gameOver(){
	alert("Game over.");
	parent.location.href='www.google.com.au';
}

