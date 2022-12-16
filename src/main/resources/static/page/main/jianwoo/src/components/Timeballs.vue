<template>
    <div class="timeball right-side">
        <canvas id="timeball" ref="myCanvas" width="310" height="200"></canvas>
    </div>
</template>

<script>

export default {
    name: "Timeballs",
    data() {
        return {
            digit: [[[1, 1, 1, 1], [1, 0, 0, 1], [1, 0, 0, 1], [1, 0, 0, 1], [1, 0, 0, 1], [1, 0, 0, 1], [1, 1, 1, 1]],
                [[0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1]],
                [[1, 1, 1, 1], [0, 0, 0, 1], [0, 0, 0, 1], [1, 1, 1, 1], [1, 0, 0, 0], [1, 0, 0, 0], [1, 1, 1, 1]],
                [[1, 1, 1, 1], [0, 0, 0, 1], [0, 0, 0, 1], [1, 1, 1, 1], [0, 0, 0, 1], [0, 0, 0, 1], [1, 1, 1, 1]],
                [[1, 0, 0, 1], [1, 0, 0, 1], [1, 0, 0, 1], [1, 1, 1, 1], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1]],
                [[1, 1, 1, 1], [1, 0, 0, 0], [1, 0, 0, 0], [1, 1, 1, 1], [0, 0, 0, 1], [0, 0, 0, 1], [1, 1, 1, 1]],
                [[1, 1, 1, 1], [1, 0, 0, 0], [1, 0, 0, 0], [1, 1, 1, 1], [1, 0, 0, 1], [1, 0, 0, 1], [1, 1, 1, 1]],
                [[1, 1, 1, 1], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1], [0, 0, 0, 1]],
                [[1, 1, 1, 1], [1, 0, 0, 1], [1, 0, 0, 1], [1, 1, 1, 1], [1, 0, 0, 1], [1, 0, 0, 1], [1, 1, 1, 1]],
                [[1, 1, 1, 1], [1, 0, 0, 1], [1, 0, 0, 1], [1, 1, 1, 1], [0, 0, 0, 1], [0, 0, 0, 1], [1, 1, 1, 1]],
                [[0], [0], [1], [0], [1], [0], [0]],
                [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]],
            ],
            date: [],
            balls: [],
            color: '#B40BB7',
            canvas: null,
            ctx: null,
            H: null,
            W: null,
            R: null,
            inter: null,
        }
    },
    mounted() {
        this.canvas = document.getElementById("timeball");
        this.ctx = this.canvas.getContext('2d');
        if (this.ctx) {
            this.ctx.translate(5, 55);
            this.W = 300;
            this.H = 100;
            this.R = 3.5;
            this.showTime();
            clearInterval(this.inter);
            var that = this;
            this.inter = setInterval(function () {
                that.render();
                that.updateBalls();
                that.showTime();
            }, 16)
        }
    },
    created() {
        const styles = getComputedStyle(document.documentElement)
        const value = String(styles.getPropertyValue('--theme_color')).trim()
        this.color = value;
    },
    methods: {
        render: function () {
            this.ctx.clearRect(0, -this.H, this.W, this.H * 2 + 20);
            for (var i = 0; i < this.balls.length; i++) {
                this.ctx.beginPath();
                this.ctx.arc(this.balls[i].x, this.balls[i].y, this.R, 0, Math.PI * 2);
                this.ctx.fillStyle = this.color;
                this.ctx.closePath();
                this.ctx.fill()
            }
        },
        showTime: function () {
            var newdate = [];
            var d = new Date();
            var h = d.getHours();
            var m = d.getMinutes();
            var s = d.getSeconds();
            newdate.push(Math.floor(h / 10), h % 10, 10, Math.floor(m / 10), m % 10, 10, Math.floor(s / 10), s % 10);
            this.showDigitGroup(newdate, 0, 0)
        },
        showDigitGroup: function (d, x, y) {
            var xx = x;
            var changedate = [];
            for (var i = 0; i < d.length; i++) {
                var dd = d[i];
                if (dd === 0 && i === 0) {
                    dd = 11
                }
                this.showDigitCtx(dd, xx, y, '#ddd', this.color);
                if (d[i] === 10) {
                    xx += 15
                } else {
                    xx += 45
                }
            }
            for (var k = 0; k < d.length; k++) {
                if (d[k] !== this.date[k] && d[k] !== undefined && this.date[k] !== undefined) {
                    changedate.push(k + '#' + this.date[k])
                }
            }
            this.changeDates(changedate);
            this.date = [];
            this.date = d.concat()
        },
        changeDates: function (changedate) {
            var t = [];
            for (var i = 0; i < changedate.length; i++) {
                t = changedate[i].split('#');
                this.doBalls(t[0], t[1], 0, 0)
            }
        },
        doBalls: function (index, num, x, y) {
            var xx = x, yy = y;
            switch (Number(index)) {
                case 0:
                    xx = x;
                    break;
                case 1:
                    xx = x + 45;
                    break;
                case 2:
                    xx = x + 90;
                    break;
                case 3:
                    xx = x + 105;
                    break;
                case 4:
                    xx = x + 150;
                    break;
                case 5:
                    xx = x + 195;
                    break;
                case 6:
                    xx = x + 210;
                    break;
                case 7:
                    xx = x + 255;
                    break
            }
            var ramNumY = [1, 2, 3, 4];
            var ramNumX = [1, 2, 3, 4];
            var t = [-1, 1];
            for (var i = 2; i < 12; i++) {
                ramNumX.push(i * -1)
            }
            for (var i2 = 0; i2 < 7; i2++) {
                for (var j = 0; j < 4; j++) {
                    if (this.digit[num][i2][j] === 1) {
                        var tt = t[Math.floor(Math.random() * t.length)];
                        var ball = {
                            x: xx + j * 9,
                            y: yy + i2 * 9,
                            stepX: 0.8 * (ramNumX[Math.floor(Math.random() * ramNumX.length)]),
                            stepY: -4 * (ramNumY[Math.floor(Math.random() * ramNumY.length)]),
                            k: 0.6,
                            lastY: tt === 1 ? Math.random() * (this.H - 30) + 30 : Math.random() * (yy + i2 * 9 - this.R - 50) + this.R - 50,
                            t: tt
                        };
                        this.balls.push(ball)
                    }
                }
            }
        },
        updateBalls: function () {
            for (var i = 0; i < this.balls.length; i++) {
                this.balls[i].x = this.balls[i].x + this.balls[i].stepX;
                this.balls[i].y = this.balls[i].y + this.balls[i].stepY;
                if (this.balls[i].t === 1) {
                    this.balls[i].stepY = this.balls[i].stepY * this.balls[i].k + 2
                } else if (this.balls[i].t === -1) {
                    this.balls[i].stepY = this.balls[i].stepY * this.balls[i].k - 2
                }
                if (this.balls[i].y > this.H - this.R) {
                    this.balls[i].y = this.H - this.R;
                    this.balls[i].stepY = -this.balls[i].stepY * this.balls[i].k;
                    this.balls[i].t = -1
                } else if (this.balls[i].y < this.balls[i].lastY) {
                    this.balls[i].y = this.balls[i].lastY;
                    this.balls[i].stepY = -this.balls[i].stepY * this.balls[i].k;
                    this.balls[i].t = 1
                }
                if (Math.abs(this.balls[i].x) > this.W - this.R || Math.abs(this.balls[i].x) < this.R * 1.5) {
                    this.balls.splice(i, 1);
                    i--
                }
            }
        },
        showDigitCtx: function (num, posx = 0, poxy = 0, bc = '#ddd', color = 'green') {
            for (var i = 0; i < 7; i++) {
                for (var j = 0; j < (num === 10 ? 1 : 4); j++) {
                    this.ctx.beginPath();
                    this.ctx.arc(posx + j * 9, poxy + i * 9, this.R, 0, Math.PI * 2, true);
                    this.ctx.fillStyle = this.digit[num][i][j] === 1 ? color : bc;
                    this.ctx.closePath();
                    this.ctx.fill()
                }
            }
        },
    },
    watch: {}
}
</script>

<style scoped>

</style>