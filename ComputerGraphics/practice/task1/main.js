(function() {
    render1();
})();

function render1(){
    function Complex(r, i) {
            this.r = r;
            this.i = i;
        }
        Complex.prototype.add = function(other) {
            return new Complex(this.r + other.r, this.i + other.i);
        }
        Complex.prototype.sub = function(other) {
            return new Complex(this.r - other.r, this.i - other.i);
        }
        Complex.prototype.mul = function(other) {
            return new Complex(this.r * other.r - this.i * other.i,
                           this.i * other.r + this.r * other.i);
        }
        Complex.prototype.div = function(other) {
            var denominator = other.r * other.r + other.i * other.i;
            return new Complex((this.r * other.r + this.i * other.i) / denominator,
                           (this.i * other.r - this.r * other.i) / denominator);
        }

        function setFillStyle(ctx, r, g, b, a) {
            ctx.fillStyle = "rgba(" + r + ", " + g + ", " + b + ", " + a + ")";
        }

        window.onload = function() {
            var planeCanvas = document.getElementById("plane");
            planeCanvas.width = window.innerWidth;
            planeCanvas.height = window.innerHeight;
            var ctx = planeCanvas.getContext("2d");

            var one = new Complex(1, 0);
            var three = new Complex(3, 0);
            var f = function(z) { return z.mul(z).mul(z).sub(one); };
            var fPrime = function(z) { return three.mul(z).mul(z); };
            var N = function(z) { return z.sub(f(z).div(fPrime(z))); };

            var bottom = Math.floor(planeCanvas.height / 2);       // 406
            var top = -bottom;                                     // -406
            var right = Math.floor(planeCanvas.width / 2);         // 555
            var left = -right;                                     // -555
            console.log("initial bottom: " + bottom)
            console.log("initial top: " + top)
            console.log("initial right: " + right)
            console.log("initial left: " + left)

            setFillStyle(ctx, 255, 255, 255, 1);
            ctx.fillRect(0, 0, planeCanvas.width, planeCanvas.height);  // fill white

            for (var x = left - 10; x < right + 10; x++) { // x=-555-10=-565; x < 565; x++
                // console.log("x: " + x)
                for (var y = top - 10; y < bottom + 10; y++) { // y=-406-10=-416; y < 416; y++
                    // console.log("y: " + y)
                    var yo = new Complex(x/500, y/500);
                    var ayy = N(yo);
                    var ayy2 = N(ayy);
                    var n = 0;
                    while (Math.abs(ayy2.r - ayy.r) > 0.000001) {
                        ayy = ayy2;
                        ayy2 = N(ayy2);
                        n++;
                    }
                    if (Math.abs(ayy.r - 1) < 0.001) {
                        setFillStyle(ctx, 0, 198, 255, n/20);
                    } else if(Math.abs(ayy.r + 0.5) < 0.001) {
                        setFillStyle(ctx, 55, 55, 55, n/20);
                    } else {
                        setFillStyle(ctx, 255, 255, 255, 1);
                    }
                    ctx.fillRect(x - left, y - top, 1, 1);
                }
            }
        }
}

function function2(){
    const param = prompt("Write param: ");
};


function changeSize(){
    console.log("changeSize");
   const fractal=document.getElementById("plane");
   fractal.style.transform="scale(5)";
};
